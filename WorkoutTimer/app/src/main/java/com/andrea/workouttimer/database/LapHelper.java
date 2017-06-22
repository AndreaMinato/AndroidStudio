package com.andrea.workouttimer.database;

import android.provider.BaseColumns;

/**
 * Created by Andrea on 13/06/17.
 */

public class LapHelper implements BaseColumns {

    public static final String TABLE_NAME = "laps";
    public static final String WORKOUT_ID = "workout_id";
    public static final String DURATION = "duration";


    public static final String CREATE_QUERY =
            "CREATE TABLE " + TABLE_NAME
                    + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DURATION + " INTEGER NOT NULL "
                    + WORKOUT_ID + " INTEGER NOT NULL, "
                    + " );";
}
