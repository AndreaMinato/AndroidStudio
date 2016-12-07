package com.example.andrea.datasource.data;

import android.provider.BaseColumns;

/**
 * Created by andrea on 12/2/16.
 */

public class ContactsHelper implements BaseColumns {

    public static final String TABLE_NAME = "contacts";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";


    public static final String CREATE_QUERY =
            "CREATE TABLE " + TABLE_NAME
                    + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT NOT NULL, "
                    + SURNAME + " TEXT NOT NULL "
                    + " );";
}
