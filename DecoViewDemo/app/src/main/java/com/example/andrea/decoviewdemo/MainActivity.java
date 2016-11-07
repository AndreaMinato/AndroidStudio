package com.example.andrea.decoviewdemo;

import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecoView subjectsArcView = (DecoView) findViewById(R.id.subjectsArcView);

        Subject subjects[] = {
                new Subject("Math", "#0097A7", 6.7f),
                new Subject("English", "#00796B", 9.2f),
                new Subject("IT", "#E0796B", 8.4f),
                new Subject("History", "#12DF5B", 2f),
                new Subject("Chemestry", "#FFC107", 4.8f)
        };

        int[] seriesIndex = new int[100];
        float linewidth = 50f;

        // Create background track
        subjectsArcView.addSeries(new SeriesItem.Builder(Color.argb(255, 255, 255, 255))
                .setRange(0, 10, 10)
                .setInitialVisibility(false)
                .setLineWidth(linewidth * subjects.length)
                .build());

        //Create data series track
        float inset = -((linewidth * (subjects.length - 1)) / 2);
        for (int i = 0; i < subjects.length; i++) {
            SeriesItem seriesItem = new SeriesItem.Builder(subjects[i].getColor())
                    .setRange(0, 10, 0)
                    .setInset(new PointF(inset, inset))
                    .setInitialVisibility(false)
                    .setLineWidth(linewidth)
                    .build();

            seriesIndex[i] = subjectsArcView.addSeries(seriesItem);

            inset += linewidth;
        }

        subjectsArcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDuration(2000)
                .build());

        for (int i = 0; i < subjects.length; i++) {

            subjectsArcView.addEvent(new DecoEvent.Builder(subjects[i].getAvgmark())
                    .setIndex(seriesIndex[i])
                    .setDelay(2000)
                    .setInterpolator(new DecelerateInterpolator())
                    .build());
        }
    }
}
