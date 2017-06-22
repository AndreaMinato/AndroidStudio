package com.andrea.workouttimer.models;

/**
 * Created by Andrea on 13/06/17.
 */

public class Lap {

    private long id;
    private long workout_id;
    private int duration;

    public Lap() {
    }

    public Lap(long aWorkout_id, int aDuration) {
        workout_id = aWorkout_id;
        duration = aDuration;
    }

    public Lap(long aId, long aWorkout_id, int aDuration) {
        id = aId;
        workout_id = aWorkout_id;
        duration = aDuration;
    }

    public long getId() {
        return id;
    }

    public void setId(long aId) {
        id = aId;
    }

    public long getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(long aWorkout_id) {
        workout_id = aWorkout_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int aDuration) {
        duration = aDuration;
    }
}
