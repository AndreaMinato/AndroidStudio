package com.andrea.minato.inrcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MY_APP";


    private TextView res;
    private EditText kg;
    private NumberPicker at;
    private NumberPicker vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = (TextView) findViewById(R.id.result);

        kg = (EditText) findViewById(R.id.nbrKG);

        vv = (NumberPicker) findViewById(R.id.npVV);
        vv.setMinValue(0);
        vv.setMaxValue(120);

        at = (NumberPicker) findViewById(R.id.npAT);
        at.setMinValue(0);
        at.setMaxValue(120);
        at.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                vv.setMinValue(newVal);
            }
        });


        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcola();
            }
        });


    }


    public void calcola() {
        Log.d(TAG, "calcola:" + vv.getValue());
        if (!kg.getText().toString().equals( "")) {
            int peso = Math.round(Integer.parseInt(kg.getText().toString()));
            int perc = vv.getValue() - (at.getValue());
            int tot = peso * perc;
            res.setText(Integer.toString(tot) + " Unità");
        } else {
            res.setText("Inserisci il peso del paziente");
        }
    }
}
