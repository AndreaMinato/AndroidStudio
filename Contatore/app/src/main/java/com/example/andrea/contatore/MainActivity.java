package com.example.andrea.contatore;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mEtichetta;
    private int mCounter;


    private static final String TAG = "TEST_ACTIVITY";
    private static final String MIO_CONTATORE = "Mio_Contatore_Figo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "M: onCreate: ");
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            mCounter = savedInstanceState.getInt(MIO_CONTATORE);
        mEtichetta = (TextView) findViewById(R.id.etichetta);

        Button vpiu = (Button) findViewById(R.id.btn1);
        vpiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;
                updateGUI();
            }
        });

        Button vmeno = (Button) findViewById(R.id.btn2);
        vmeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter--;
                updateGUI();
            }
        });

        Button vnext = (Button) findViewById(R.id.btnnext);
        vnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();
            }
        });


        updateGUI();
    }

    private void launchActivity() {
        Intent daie = new Intent(this, DetailActivity.class);
        Bundle vBundle = new Bundle();
        vBundle.putInt(DetailActivity.VALORE,mCounter);
        daie.putExtras(vBundle);
        startActivity(daie);
    }


    private void updateGUI() {
        mEtichetta.setText("" + mCounter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "M: onStart: ");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "M: onRestart: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "M: onResume: ");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MIO_CONTATORE, mCounter);
        Log.d(TAG, "M: onSaveInstanceState: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "M: onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "M: onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "M: onDestroy: ");

    }
}
