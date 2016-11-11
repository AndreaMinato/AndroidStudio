package com.example.andrea.listwithprogressandrating;

import java.util.Random;

/**
 * Created by andrea on 11/11/16.
 */

public class ProgressRating {
    private int id;
    private String name;
    private int progress;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ProgressRating(int id) {
        this.id = id;
        this.name = "Cosetta n°" + id;
        Random random = new Random();
        this.progress = random.nextInt(100);
        this.rating = random.nextInt(5);

    }
}
