package com.example.andrea.contatore;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends Activity {
    int mCount=0;
    TextView mtxt;
    static final String VALORE = "ghjflvlm";
    private static final String Contatore="tante_cose_fighe";
    private static final String TAG="TEXT_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_detail);


        Bundle vBundle = getIntent().getExtras();
        if(vBundle!=null) {
            mCount = vBundle.getInt(VALORE);
        }

        if (savedInstanceState != null) {
            mCount=savedInstanceState.getInt(Contatore);
        }

        mtxt = (TextView)findViewById(R.id.txtDetail);
        mtxt.setText("" + mCount);

        Button vbtnsx = (Button)findViewById(R.id.btnsx);
        vbtnsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vIntent = new Intent(DetailActivity.this, DetailActivity.class);
                Bundle vBundle = new Bundle();
                vBundle.putInt(DetailActivity.VALORE, mCount);
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
            }
        });

        Button vbtndx = (Button)findViewById(R.id.btndx);
        vbtndx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vIntent = new Intent(DetailActivity.this, MyActivity.class);
                Bundle vBundle = new Bundle();
                vBundle.putInt(DetailActivity.VALORE, mCount);
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
            }
        });


        Button vbtn = (Button)findViewById(R.id.btndouble);
        vbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount*=2;
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
        outState.putInt(Contatore,mCount);

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
