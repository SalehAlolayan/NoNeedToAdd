package dev.alolayan.saleh.noneedtoadd;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    public void emailClick(View view){
        Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:saleh.alolayan.dev@hotmail.com"));
        startActivity(web);
    }

    public void githubClick(View view){
        Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/salehalolayan"));
        startActivity(web);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
