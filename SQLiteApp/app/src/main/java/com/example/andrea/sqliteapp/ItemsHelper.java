package com.example.andrea.sqliteapp;

import android.provider.BaseColumns;

/**
 * Created by andrea on 11/25/16.
 */

public class ItemsHelper implements BaseColumns {
    private static final String TABLE_NAME = "items";
    private static final String NAME = "name";
    private static final String QUANTITY = "quantity";

    public static final String CREATE_QUERY =
            "CREATE TABLE" + TABLE_NAME
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT NOT NULL, "
                    + QUANTITY + " INTEGER NOT NULL "
                    + ");";
}
