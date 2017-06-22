package com.andrea.workouttimer.models;

import java.util.Date;

/**
 * Created by Andrea on 13/06/17.
 */

public class Workout {

    private long id;
    private String place;
    private Date date;
    private int duration;


    public Workout() {
    }

    public Workout(String aPlace, int aDuration, Date aDate) {
        place = aPlace;
        duration = aDuration;
        date = aDate;
    }

    public Workout(long aId, String aPlace, int aDuration, Date aDate) {
        id = aId;
        place = aPlace;
        duration = aDuration;
        date = aDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date aDate) {
        date = aDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long aId) {
        id = aId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String aPlace) {
        place = aPlace;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int aDuration) {
        duration = aDuration;
    }
}
