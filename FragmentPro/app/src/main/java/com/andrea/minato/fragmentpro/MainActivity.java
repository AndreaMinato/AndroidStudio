package com.andrea.minato.fragmentpro;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements MyFragment.IUpdateText {

    private static final String TAG = "Pro: ";
    private static final String FRAGMENT = "primoFragment: ";
    MyFragment mCounterFragment;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        FragmentManager fragmentManager = getFragmentManager();
        mCounterFragment = (MyFragment) fragmentManager.findFragmentByTag(FRAGMENT);
        if (mCounterFragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //mCounterFragment =MyFragment.getInstance(123)

            mCounterFragment = new MyFragment();
            fragmentTransaction.add(R.id.container, mCounterFragment, FRAGMENT);
            fragmentTransaction.commit();
        }


        Button btnPiu = (Button) findViewById(R.id.piu);
        btnPiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounterFragment.inc();
            }
        });

        Button btnMeno = (Button) findViewById(R.id.meno);
        btnMeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounterFragment.dec();
            }
        });

        text = (TextView) findViewById(R.id.myTxt);

    }



    @Override
    public void updateText(int x) {
        text.setText(""+x);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
