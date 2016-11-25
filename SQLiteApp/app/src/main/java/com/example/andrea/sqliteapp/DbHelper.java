package com.example.andrea.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andrea on 11/25/16.
 */

public class DbHelper extends SQLiteOpenHelper {

    //Molto importante l'utilizzo delle costanti
    private static final String DB_NAME = "DbName";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        //per ora ci fa ignorare il cursor factory e il valore di default è null
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Il metodo onCreate viene chiamato solo se il file non esiste
    // si scrivono le create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //region Modo brutto e che porta a errori
        /*
        //formatatta così per leggibilità prevaentemente
        String vQuery = "CREATE TABLE items " +
                "(" +
                "_id Integer PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "quantity INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(vQuery);
        */

        //endregion

        sqLiteDatabase.execSQL(ItemsHelper.CREATE_QUERY);
    }

    //Se invece cambio la versione de DB viene chiamato il metodo onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
