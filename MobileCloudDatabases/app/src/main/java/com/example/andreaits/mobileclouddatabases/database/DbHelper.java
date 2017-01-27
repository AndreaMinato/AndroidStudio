package com.example.andreaits.mobileclouddatabases.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andreaits.mobileclouddatabases.database.contact.ContactsHelper;

/**
 * Created by andreaits on 19/01/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private final static String NAME = "contactsDB";
    private final static int VERSION = 1;

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(ContactsHelper.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
