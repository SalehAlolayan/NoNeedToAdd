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
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    //variables
    CountryCodePicker ccp;
    EditText editTextCarrierNumber;
    Button chatButton;


    /* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/



    //WhatsApp click
    public void click(View view){
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(editTextCarrierNumber);
        String num = ccp.getFullNumber();
        Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+num));
        startActivity(web);
        editTextCarrierNumber.getText().clear();
    }
    /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatButton = findViewById(R.id.chatButton);
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
}