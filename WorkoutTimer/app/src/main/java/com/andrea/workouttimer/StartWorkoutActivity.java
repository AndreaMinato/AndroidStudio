package com.andrea.workouttimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartWorkoutActivity extends AppCompatActivity {

    private static final String TAG = "StartWorkoutActivity";

    private EditText mEditTextPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        mEditTextPlace = (EditText)findViewById(R.id.editPlace);

        Button btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startWorkout();
            }
        });
    }

    private void startWorkout() {
        Intent vIntent = new Intent(this, WorkoutActivity.class);

        startActivity(vIntent);
    }
}
