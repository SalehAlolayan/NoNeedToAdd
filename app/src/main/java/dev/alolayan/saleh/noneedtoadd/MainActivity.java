package dev.alolayan.saleh.noneedtoadd;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.Menu;

import com.hbb20.CountryCodePicker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //variables
    CountryCodePicker ccp;
    EditText editTextCarrierNumber;
    ListView listView;
    private static final int PHONE_LOG = 1;
    Button chatButton;
    Button historyButton;



    //WhatsApp click
    public void click(View view){
            ccp = findViewById(R.id.ccp);
            ccp.registerCarrierNumberEditText(editTextCarrierNumber);
            String num = ccp.getFullNumber();
            Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+num));
            startActivity(web);
            editTextCarrierNumber.getText().clear();
    }

    //History click
    public void HistoryClick(View view){

        // making buttons invisable

        chatButton.setVisibility(View.INVISIBLE);
        historyButton.setVisibility(View.INVISIBLE);


        //variables
        listView.setVisibility(View.VISIBLE);
        String phNumber = null;
        int number;
        int i = 0 ;

        // get number from call log
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        final ArrayList<String> NumbersArray = new ArrayList<>();
        while(managedCursor.moveToNext()&& i<20){
            phNumber = managedCursor.getString(number);
            NumbersArray.add(phNumber);
            i++;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NumbersArray);
        listView.setAdapter(arrayAdapter);


        //List click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editTextCarrierNumber.setText(NumbersArray.get(position));
                listView.setVisibility(View.INVISIBLE);

                // buttons back to Visible
                chatButton.setVisibility(View.VISIBLE);
                historyButton.setVisibility(View.VISIBLE);
            }
        })  ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkCallLogPermission();
        listView = (ListView)findViewById(R.id.listView);
        chatButton = findViewById(R.id.chatButton);
        historyButton = findViewById(R.id.historyButton);
        editTextCarrierNumber =  findViewById(R.id.editText_carrierNumber);
        Toast.makeText(this,"Made with love from Riyadh, Saudi Arabia",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

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

    //Permission
    private void checkCallLogPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG},PHONE_LOG);

            return;


        }
    }


}
