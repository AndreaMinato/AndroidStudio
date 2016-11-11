package com.example.andrea.listwithprogressandrating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ProgressRating> progressRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitDB();

        CustomAdapter customAdapter= new CustomAdapter(progressRatings,this);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customAdapter);


    }

    private void InitDB() {
        progressRatings = new ArrayList<ProgressRating>();
        for (int i = 0; i < 100; i++) {
            ProgressRating progressRating = new ProgressRating(i);
            progressRatings.add(progressRating);
        }
    }
}
