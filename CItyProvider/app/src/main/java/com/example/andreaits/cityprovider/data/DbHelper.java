package com.example.andreaits.cityprovider.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andreaits on 13/01/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private final static String NAME = "newDB";
    private final static int VERSION = 1;

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CityHelper.CREATE_TABLE);
        sqLiteDatabase.execSQL(DetectionHelper.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}