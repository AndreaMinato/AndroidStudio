package com.example.andrea.sqliteapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SQLiteApp";

    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DbHelper(this);
        //insertData();

        //bisogna prendere il DB
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM" + ItemsHelper.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ItemsHelper._ID));
            String name = cursor.getString(cursor.getColumnIndex(ItemsHelper.NAME));
            int quantity = cursor.getInt(cursor.getColumnIndex(ItemsHelper.QUANTITY));

            Log.d(TAG + " DB", "" + id + " - " + name + " " + quantity);
        }
        cursor.close();
    }

    private void insertData() {
    }
}
