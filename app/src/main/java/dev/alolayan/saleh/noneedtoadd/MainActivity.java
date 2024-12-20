package dev.alolayan.saleh.noneedtoadd;

/*
* Developer name: Saleh Alolayan
* Email: Saleh.Alolayan.dev@hotmail.com
*
* Disclaimer: I shall not be held responsible,
* if any of my code, apps, scripts, ETC…, caused you or any one a thermonuclear war,
* world war, zombie attack or even you getting fired because the alarm failed you.
* You will always us it on your own responsible/risk.
*
* Note: The code is open sourced,
* for that you are allowed to use it in any but you shouldn't add ads or
* ask for money, it was free and it will always will be :)
*
* I SAW YOU SEE ME SAW YOU
* */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import com.hbb20.CountryCodePicker;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //variables
    CountryCodePicker ccp;
    EditText editTextCarrierNumber;
    ListView listView;
    TextView linkTextView;
    Button chatButton;
    Button historyButton;
    Button cancelButton;
    Button linkButton;

    //WhatsApp click
    public void WhatsAppClick(View view){
            ccp = findViewById(R.id.ccp);
            ccp.registerCarrierNumberEditText(editTextCarrierNumber);
            String num = ccp.getFullNumber();
            Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+num));
            startActivity(web);
            editTextCarrierNumber.getText().clear();
    }


    //History click
    public void HistoryClick(View view){
        //permission

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_CALL_LOG)
                    == PackageManager.PERMISSION_GRANTED) {

                // making buttons invisible/visible
                chatButton.setVisibility(View.INVISIBLE);
                historyButton.setVisibility(View.INVISIBLE);
                cancelButton.setVisibility(View.VISIBLE);

                //variables
                final ArrayList<String> NumbersArray = new ArrayList<>();
                listView.setVisibility(View.VISIBLE);
                String phNumber = null;
                int number;

                // get number from call log + add the last call number
                Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                        null, null,CallLog.Calls.DATE + " ASC");
                managedCursor.moveToLast();
                number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
                NumbersArray.add(managedCursor.getString(number));

                //writing numbers from the log into the arraylist
                    while (managedCursor.moveToPrevious()) {
                        phNumber = managedCursor.getString(number);
                        NumbersArray.add(phNumber);
                    }

                //to reduce the number of items in the array
                if(NumbersArray.size()>10)
                    for(int i = NumbersArray.size()-1 ; i > 9 ; i--)
                        NumbersArray.remove(i);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NumbersArray);
                listView.setAdapter(arrayAdapter);

                //ListChat click
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        editTextCarrierNumber.setText(NumbersArray.get(position));
                        listView.setVisibility(View.INVISIBLE);
                        NumbersArray.clear();

                        // buttons back to Visible/invisible
                        chatButton.setVisibility(View.VISIBLE);
                        historyButton.setVisibility(View.VISIBLE);
                        cancelButton.setVisibility(View.INVISIBLE);

                    }
                })  ;
            }
            else {

                Toast.makeText(this,"Please allow the permission so you can access the call history",Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_CALL_LOG}, 1);

            }
        }
    }

    public void CancelClick(View view){
        chatButton.setVisibility(View.VISIBLE);
        historyButton.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding Views
        listView = findViewById(R.id.listView);
        chatButton = findViewById(R.id.chatButton);
        historyButton = findViewById(R.id.historyButton);
        cancelButton = findViewById(R.id.cancelButton);
        editTextCarrierNumber =  findViewById(R.id.editText_carrierNumber);

        Toast.makeText(this,"Made with love from Riyadh, Saudi Arabia",Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // About Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.about){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);

            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}

