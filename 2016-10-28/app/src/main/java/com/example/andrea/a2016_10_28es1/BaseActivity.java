package com.example.andrea.a2016_10_28es1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public abstract class BaseActivity extends AppCompatActivity {

    private TextView txt1;
    private TextView txt2;
    private String create;
    private static final String DATE = "date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.getActionBar().setTitle(this.getName());


        if (savedInstanceState == null) {
            Date date = new Date();
            create = date.toString();
        } else {
            create = savedInstanceState.getString(DATE);
        }
        setupGUI();
    }

    private void setupGUI() {
        Button btnA = (Button) findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchA();
            }
        });

        Button btnB = (Button) findViewById(R.id.btnB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchB();
            }
        });

        Button btnC = (Button) findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchC();
            }
        });

        Button btnD = (Button) findViewById(R.id.btnD);
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchD();
            }
        });

        Button btnMain = (Button) findViewById(R.id.btnMain);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMain();
            }
        });

        Button btnNotifica = (Button) findViewById(R.id.btnNotifica);
        btnNotifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNotifica();
            }
        });

        txt1 = (TextView)findViewById(R.id.txt1);
        txt1.setText(create);

        txt2 = (TextView)findViewById(R.id.txt2);
        txt2.setText(this.getName());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected abstract String getName();

    protected void launchA() {
        Intent intent = new Intent(this, ActivityA.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    protected void launchB() {
        Intent intent = new Intent(this, ActivityB.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void launchC() {
        Intent intent = new Intent(this, ActivityC.class);

        startActivity(intent);
    }

    protected void launchD() {
        Intent intent = new Intent(this, ActivityD.class);

        startActivity(intent);
    }

    protected void launchMain() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    protected void launchNotifica() {

    }
}

