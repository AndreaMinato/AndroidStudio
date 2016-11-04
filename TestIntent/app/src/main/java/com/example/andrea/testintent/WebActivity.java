package com.example.andrea.testintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        TextView txt = (TextView) findViewById(R.id.txt);

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if (uri != null)
            txt.setText(uri.toString());


    }
}
