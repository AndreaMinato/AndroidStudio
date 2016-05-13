package com.andrea.minato.fragmentsupport;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Fragmentselector.IOnButtonSelected, FirstDialog.IOnDialogSelected {

    private static final String FRAGMENT_TAG = "current frag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragmentselector fragmentselector = (Fragmentselector) fragmentManager.findFragmentByTag(FRAGMENT_TAG);

        if (fragmentselector == null) {
            /*android.support.v4.app.FragmentTransaction vTr = getSupportFragmentManager().beginTransaction();
            vTr.add(R.id.container,Fragmentselector.getInstance(),FRAGMENT_TAG);
            vTr.commit();*/
            fragmentselector = Fragmentselector.getInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragmentselector, FRAGMENT_TAG)
                    .commit();


        }
    }

    @Override
    public void onUpdateValue(String aValue) {
        Log.i("TEST", aValue);
/*
        android.support.v4.app.FragmentTransaction vTr = getSupportFragmentManager().beginTransaction();
        vTr.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        //vTr.replace(R.id.container, FragmentA.getInstance());
        vTr.replace(R.id.container,Fragmentselector.getInstance(aValue));
        vTr.addToBackStack("NomeTransazione");
        vTr.commit();*/

        FirstDialog firstDialog = FirstDialog.getInstance();
        firstDialog.show(getSupportFragmentManager(),"Dialog");
    }

    @Override
    public void OnButtonSelected(String str) {
        getSupportActionBar().setSubtitle(str);
    }
}
