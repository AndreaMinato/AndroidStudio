package com.esame.MinatoAndrea.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andreaits on 24/02/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private final static String NAME = "reservationsDB";
    private final static int VERSION = 1;

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(ReservationHelper.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}