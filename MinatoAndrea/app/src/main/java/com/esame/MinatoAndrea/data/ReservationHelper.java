package com.esame.MinatoAndrea.data;

import android.provider.BaseColumns;

/**
 * Created by andreaits on 24/02/17.
 */

public class ReservationHelper implements BaseColumns {

    public static final String TABLE_NAME = "reservations";
    public static final String NAME = "name";
    public static final String CELL_PHONE = "cellphone";
    public static final String NUMBER = "number";


    public static final String CREATE_QUERY =
            "CREATE TABLE " + TABLE_NAME
                    + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT NOT NULL, "
                    + CELL_PHONE + " TEXT NOT NULL, "
                    + NUMBER + " INTEGER NOT NULL "
                    + " );";
}