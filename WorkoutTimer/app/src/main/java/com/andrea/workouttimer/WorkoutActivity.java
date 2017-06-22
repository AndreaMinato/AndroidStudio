package com.andrea.workouttimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends AppCompatActivity {

    private static final String TAG = "WorkoutActivity";

    private TextView mTextViewPlace;
    private TextView mTextViewTotal;
    private TextView mTextViewTotalTime;
    private TextView mTextViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        mTextViewPlace = (TextView)findViewById(R.id.txtPlace);
        mTextViewTotal = (TextView)findViewById(R.id.txtTotalStatic);
        mTextViewTotalTime = (TextView)findViewById(R.id.txtTotalTime);
        mTextViewTimer = (TextView)findViewById(R.id.txtTimer);

        Button btnLap = (Button)findViewById(R.id.btnLap);
        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lapPressed();

            }
        });

        Button btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopPressed();

            }
        });

        Button btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancelWorkout();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Premere cancel per annullare", Toast.LENGTH_SHORT).show();
    }

    private void cancelWorkout() {
        Intent vIntent = new Intent(this, HomeActivity.class);

        vIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(vIntent);
    }

    private void stopPressed() {

    }

    private void lapPressed() {

    }
}
