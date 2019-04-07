package dev.alolayan.saleh.noneedtoadd;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText editTextCarrierNumber;

    public void click(View view){
        ccp = findViewById(R.id.ccp);
        editTextCarrierNumber =  findViewById(R.id.editText_carrierNumber);
        ccp.registerCarrierNumberEditText(editTextCarrierNumber);
        String num = ccp.getFullNumber();
        Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+num));
        startActivity(web);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
