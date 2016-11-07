package com.example.andrea.oiggassem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ReciveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive);

        TextView txt = (TextView) findViewById(R.id.txt);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {

            txt.setText(reverse(extras.getString(Intent.EXTRA_TEXT)));
            Toast.makeText(this, extras.getString(Intent.EXTRA_TEXT) , Toast.LENGTH_LONG).show();
        }
        else
            txt.setText("Eh niente");
    }

    private String reverse(String string) {
        return new StringBuffer(string).reverse().toString();
    }
}
