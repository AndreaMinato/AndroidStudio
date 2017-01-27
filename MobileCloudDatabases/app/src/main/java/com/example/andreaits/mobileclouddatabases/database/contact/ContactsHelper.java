package com.example.andreaits.mobileclouddatabases.database.contact;

import android.provider.BaseColumns;

/**
 * Created by andreaits on 19/01/17.
 */

public class ContactsHelper implements BaseColumns {

    public static final String TABLE_NAME = "contacts";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String BIRTH = "birth";
    public static final String MAIL = "mail";
    public static final String CELL = "cell";
    public static final String ADDRESS = "address";
    public static final String ADDRESS_NUMBER = "addressnumber";
    public static final String CAP = "cap";
    public static final String CITY = "city";
    public static final String PROVINCE = "province";
    public static final String LAT = "latitude";
    public static final String LONG = "longitude";


    public static final String CREATE_QUERY =
            "CREATE TABLE " + TABLE_NAME
                    + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT NOT NULL, "
                    + SURNAME + " TEXT NOT NULL, "
                    + BIRTH + " DATE NOT NULL, "
                    + MAIL + " TEXT NOT NULL, "
                    + CELL + " TEXT NOT NULL, "
                    + ADDRESS + " TEXT NOT NULL, "
                    + ADDRESS_NUMBER + " TEXT NOT NULL, "
                    + CAP + " TEXT NOT NULL, "
                    + CITY + " TEXT NOT NULL, "
                    + PROVINCE + " TEXT NOT NULL, "
                    + LAT + " DOUBLE NOT NULL, "
                    + LONG + " DOUBLE NOT NULL "
                    + " );";

}
