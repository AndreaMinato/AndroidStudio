package com.example.andrea.sqliteapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SQLiteApp";

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        insertData();
        readData();

    }

    private void readData() {
        //bisogna prendere il DB
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        //region dimenticare rawQuery
        //Cursor cursor = database.rawQuery("SELECT * FROM " + ItemsHelper.TABLE_NAME, null);
        //endregion

        //utilizzare maggiormente questa
        Cursor cursor = database.query(ItemsHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ItemsHelper._ID));
            String name = cursor.getString(cursor.getColumnIndex(ItemsHelper.NAME));
            int quantity = cursor.getInt(cursor.getColumnIndex(ItemsHelper.QUANTITY));

            Log.d(TAG + " DB", "" + id + " - " + name + " " + quantity);
        }
        cursor.close();
        database.close();
    }

    private void insertData() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        for (int i = 0; i < 100; i++) {
            ContentValues values = new ContentValues();
            values.put(ItemsHelper.NAME, "item " + i);
            values.put(ItemsHelper.QUANTITY, i * 10);

            //nel secondo valore lasciare praticamente sempre a null, neanche merlino ha mai messo diverso
            database.insert(ItemsHelper.TABLE_NAME, null, values);
        }
        database.close();

    }
}
