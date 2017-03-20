package com.example.robertoluizveigajunior.cartoys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.robertoluizveigajunior.cartoys.BuildConfig;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView txtNomeDev = (TextView) findViewById(R.id.txt_nome_dev);
        txtNomeDev.setText(getString(R.string.dev_name));
        TextView txtPhrase = (TextView) findViewById(R.id.txt_phrase);
        txtPhrase.setText(BuildConfig.VERSION_NAME);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
