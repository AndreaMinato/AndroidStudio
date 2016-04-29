package com.example.andrea.masterdetail;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ABCFragment.OnButtonClicked {
    private static final String FRAGMENT_TAG = "current frag";
    private boolean hasDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ABCFragment abcFragment = (ABCFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        hasDetail = findViewById(R.id.detailcontainer) != null;


        if (abcFragment == null) {
            abcFragment = ABCFragment.getInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.container, abcFragment, FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public void onSelectedValue(String aValue) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(hasDetail){
            fragmentTransaction.replace(R.id.detailcontainer, DetailFragment.getInstance(aValue));
        }
        else{
            fragmentTransaction.replace(R.id.container, DetailFragment.getInstance(aValue), FRAGMENT_TAG);
            fragmentTransaction.addToBackStack("yo");
        }

        fragmentTransaction.commit();
        Log.i("Clicked ", aValue);
    }
}
