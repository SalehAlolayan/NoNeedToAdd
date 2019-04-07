package dev.alolayan.saleh.noneedtoadd;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewActivity extends AppCompatActivity {

    public void clickD(View view){
        Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:saleh.alolayan.dev@hotmail.com"));
        startActivity(web);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }
}
