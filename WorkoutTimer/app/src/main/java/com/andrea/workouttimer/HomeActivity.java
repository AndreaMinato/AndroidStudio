package com.andrea.workouttimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private TextView mTextViewWorkoutNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mTextViewWorkoutNumber = (TextView)findViewById(R.id.txtWorkoutNumber);

        mTextViewWorkoutNumber.setText("0");

        Button btnNewWorkout = (Button)findViewById(R.id.btnNewWorkout);
        btnNewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startWotkout();

            }
        });


        Button btnWorkoutList = (Button)findViewById(R.id.btnWorkoutList);
        btnWorkoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listWorkout();

            }
        });

    }

    private void listWorkout() {
        Intent vIntent = new Intent(this, WorkoutListActivity.class);

        startActivity(vIntent);
    }

    private void startWotkout() {
        Intent vIntent = new Intent(this, StartWorkoutActivity.class);

        startActivity(vIntent);
    }
}
