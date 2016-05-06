package com.example.andrea.dialogproject;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Dialog1.IOnDialogSelected, Dialog2.IOnItemSelected{
    private static final String BTN = "BTN Pressed";
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){

        }

        txtTitle = (TextView)findViewById(R.id.txtTitle);

        Button btn1 = (Button)findViewById(R.id.btn1);
        assert btn1 != null;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog1 dialog1 = Dialog1.getInstance();
                dialog1.show(getFragmentManager(),"DIALOG1");
            }
        });

        Button btn2 = (Button)findViewById(R.id.btn2);
        assert btn2 != null;
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(BTN, " 2");
                Dialog2 dialog2 = Dialog2.getInstance();
                dialog2.show(getFragmentManager(), "DIALOG1");
            }
        });

        Button btn3 = (Button)findViewById(R.id.btn3);
        assert btn3 != null;
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(BTN, " 3");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void OnButtonSelected(String str) {
        txtTitle.setText(str);
    }

    @Override
    public void OnItemSelected(String str) {
        txtTitle.setText(str);
    }
}
