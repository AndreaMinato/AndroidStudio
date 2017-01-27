package com.example.andreaits.cityprovider.data;

import android.provider.BaseColumns;

/**
 * Created by andreaits on 13/01/17.
 */

public class DetectionHelper implements BaseColumns {

    public static final String TABLE_NAME = "detections";
    public static final String CITY_ID = "city_id";
    public static final String DEGREES = "degrees";
    public static final String HOUR = "hours";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME
                    + " ( "
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CITY_ID + " INTEGER NOT NULL, "
                    + DEGREES + " DOUBLE NOT NULL"
                    + HOUR + " DATETIME NOT NULL"
                    + " );";


    public static final String INNER_JOIN_WITH_CITIES = String.format("%1s INNER JOIN %2s ON %1s.%3s = %2s.%4s",
            TABLE_NAME,
            CityHelper.TABLE_NAME,
            _ID,
            CityHelper._ID);
}
