package com.andrea.workouttimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WorkoutDetailActivity extends AppCompatActivity {


    private TextView mTextViewDuration;
    private TextView mTextViewLapsNumber;
    private TextView mTextViewPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        mTextViewDuration = (TextView) findViewById(R.id.txtDetailDuration);
        mTextViewLapsNumber = (TextView) findViewById(R.id.txtDetailLapsNumber);
        mTextViewPlace = (TextView) findViewById(R.id.txtDetailPlace);
    }
}
