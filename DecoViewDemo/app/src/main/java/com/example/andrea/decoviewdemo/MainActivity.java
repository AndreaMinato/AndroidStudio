package com.example.andrea.decoviewdemo;

import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.charts.SeriesLabel;
import com.hookedonplay.decoviewlib.events.DecoEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecoView subjectsArcView = (DecoView) findViewById(R.id.subjectsArcView);

        Subject subjects[] = {
                new Subject("Metematica", "#0097A7", 6.7f),
                new Subject("Inglese", "#00796B", 9.2f),
                new Subject("Informatica.", "#E0796B", 8.4f),
                new Subject("Italiano", "#12DF5B", 2f),
                new Subject("Educazione Fisica", "#3527FF", 5.3f),
                new Subject("Sistemi", "#FF9629", 8.2f),
                new Subject("Calcolo", "#009CFF", 7.1f),
                new Subject("Chimica", "#0C660C", 4.2f),
                new Subject("Sienze", "#666666", 6.2f),
                new Subject("Fisica", "#013F38", 10f),
                new Subject("Elettronica", "#FFC107", 4.8f)
        };
        float linewidth = 30f;

        setupArcView(subjectsArcView, subjects, linewidth);


    }

    private void setupArcView(DecoView subjectsArcView, Subject[] subjects, float linewidth) {
        int[] seriesIndex = new int[100];

        // Create background track
        subjectsArcView.addSeries(new SeriesItem.Builder(Color.parseColor("#FFFFFF"))
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

        int delay = 500;
        int duration = 500;

        for (int j = 0; j < 10; j++)
            for (int i = 0; i < subjects.length; i++) {

                subjectsArcView.addEvent(new DecoEvent.Builder(subjects[i].getAvgmark())
                        .setIndex(seriesIndex[i])
                        .setDelay(delay)
                        .setDuration(duration)
                        .setInterpolator(new AccelerateInterpolator())
                        .build());

                delay += duration;

                subjectsArcView.addEvent(new DecoEvent.Builder(10)
                        .setIndex(seriesIndex[i])
                        .setDelay(delay)
                        .setDuration(duration)
                        .setInterpolator(new DecelerateInterpolator())
                        .build());

                delay += duration;

                subjectsArcView.addEvent(new DecoEvent.Builder(subjects[i].getAvgmark())
                        .setIndex(seriesIndex[i])
                        .setDelay(delay)
                        .setDuration(duration)
                        .setDisplayText("coseeeee")
                        .setInterpolator(new AccelerateInterpolator())
                        .build());
            }
    }
}


