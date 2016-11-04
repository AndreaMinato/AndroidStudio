package com.example.andrea.testintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        TextView txt = (TextView) findViewById(R.id.txt);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null)
            txt.setText(extras.getString(Intent.EXTRA_TEXT));
        else
            txt.setText("Eh niente");
    }
}
