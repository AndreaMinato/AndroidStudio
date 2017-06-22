package com.andrea.workouttimer.database;

import android.provider.BaseColumns;


public class WorkoutHelper implements BaseColumns {
    public static final String TABLE_NAME = "workouts";
    public static final String PLACE = "place";
    public static final String WORKOUT_DATE = "workout_date";
    public static final String DURATION = "duration";


    public static final String CREATE_QUERY =
            "CREATE TABLE " + TABLE_NAME
                    + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PLACE + " TEXT NOT NULL, "
                    + WORKOUT_DATE + " TIMESTAMPS NOT NULL, "
                    + DURATION + " INTEGER NOT NULL "
                    + " );";
}
