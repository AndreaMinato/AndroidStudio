package com.example.andreaits.cityprovider.data;

import android.provider.BaseColumns;

/**
 * Created by andreaits on 13/01/17.
 */

public class CityHelper implements BaseColumns {

    public static final String TABLE_NAME = "cities";
    public static final String NAME = "name";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME
                    + " ( "
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT NOT NULL "
                    + " );";
}
