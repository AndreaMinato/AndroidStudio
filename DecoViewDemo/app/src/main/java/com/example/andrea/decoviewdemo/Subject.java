package com.example.andrea.decoviewdemo;

import android.graphics.Color;

/**
 * Created by andrea on 11/7/16.
 */

public class Subject {
    private String name;
    private int color;
    private float avgmark;

    public Subject(String name, String color, float avgmark) {
        this.name = name;
        this.color = Color.parseColor(color);
        this.avgmark = avgmark;
    }

    public Subject() {
        this.name = "subject";
        this.color = Color.parseColor("#000000");
        this.avgmark = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Color.parseColor(color);
    }

    public float getAvgmark() {
        return avgmark;
    }

    public void setAvgmark(float avgmark) {
        this.avgmark = avgmark;
    }
}


