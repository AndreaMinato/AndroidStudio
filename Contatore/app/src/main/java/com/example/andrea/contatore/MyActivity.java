package com.example.andrea.contatore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity {

    int mCount=0;
    int fCount=0;
    TextView mtxt;
    TextView morigin;
    static final String VAL = "ghjflvlm";
    private static final String Contatore="fisso";
    private static final String Nuovo_Valore="variabile";
    private static final String TAG="TEXT_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_my);


        Bundle vBundle = getIntent().getExtras();
        if(vBundle!=null) {
            fCount = vBundle.getInt(VAL);
            mCount=fCount;
        }

        if (savedInstanceState != null) {
            mCount=savedInstanceState.getInt(Nuovo_Valore);
            fCount=savedInstanceState.getInt(Contatore);
        }

        morigin = (TextView)findViewById(R.id.origin);
        morigin.setText("" + fCount);

        mtxt = (TextView)findViewById(R.id.edited);
        mtxt.setText("" + mCount);



        Button btn3 = (Button)findViewById(R.id.x3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount*=3;
                mtxt.setText("" + mCount);
            }
        });


        Button btn4 = (Button)findViewById(R.id.x4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount*=4;
                mtxt.setText("" + mCount);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "D: onStart:");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "D: onRestart: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "D: onResume: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "D: onPause: ");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "M: onSaveInstanceState: ");
        outState.putInt(Nuovo_Valore,mCount);
        outState.putInt(Contatore,fCount);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "D: onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "D: onDestroy: ");

    }
}
