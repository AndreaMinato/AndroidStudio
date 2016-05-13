package com.andrea.minato.myfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements MyFragment.updater{

    private static final String TAG = "Pro: ";
    private static final String FRAGMENT = "primoFragment: ";
    MyFragment mCounterFragment;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        mCounterFragment = (MyFragment) fragmentManager.findFragmentByTag(FRAGMENT);
        if (mCounterFragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mCounterFragment = new MyFragment();
            fragmentTransaction.add(R.id.frame, mCounterFragment, FRAGMENT);
            fragmentTransaction.commit();
        }

        text = (TextView) findViewById(R.id.txt);
    }

    @Override
    public void updateText(int x) {
        text.setText(""+x);
    }
}
