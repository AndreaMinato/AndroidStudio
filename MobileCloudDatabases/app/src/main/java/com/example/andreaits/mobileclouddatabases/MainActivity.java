package com.example.andreaits.mobileclouddatabases;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.andreaits.mobileclouddatabases.database.contact.Contact;
import com.example.andreaits.mobileclouddatabases.database.contact.ContactContentProvider;
import com.example.andreaits.mobileclouddatabases.database.contact.ContactCursorAdapter;
import com.example.andreaits.mobileclouddatabases.database.contact.ContactsHelper;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivity";

    private SearchView searchView;

    private ContactCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);


        ListView listView = (ListView) findViewById(R.id.listView);


        adapter = new ContactCursorAdapter(this, null);
        listView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(0, null, this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contactClicked(id);
            }
        });

    }


    //region UI Methods

    private void contactClicked(long id) {
        Contact contact = findContactByID(id);

        Log.d(TAG, "contactClicked: ");

    }

    private Contact findContactByID(long id) {

        String URI = ContactContentProvider.CONTACTS_URI.toString() + "/" + id;

        Cursor cursor = getContentResolver().query(Uri.parse(URI), null, null, null, null);

        if (cursor.moveToNext())
            return new Contact(
                    cursor.getInt(cursor.getColumnIndex(ContactsHelper._ID)),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.SURNAME)),
                    new Date(cursor.getLong(cursor.getColumnIndex(ContactsHelper.BIRTH))),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.MAIL)),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.CELL)),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.ADDRESS_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.CAP)),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.CITY)),
                    cursor.getString(cursor.getColumnIndex(ContactsHelper.PROVINCE)),
                    cursor.getLong(cursor.getColumnIndex(ContactsHelper.LAT)),
                    cursor.getLong(cursor.getColumnIndex(ContactsHelper.LONG))
            );
        else
            return null;
    }

    private void addClicked() {
        ContentValues values = new ContentValues();
        Contact contact = Contact.createRandomContact();

        values.put(ContactsHelper.NAME, contact.getName());
        values.put(ContactsHelper.SURNAME, contact.getSurname());
        values.put(ContactsHelper.BIRTH, contact.getBirth().getTime());
        values.put(ContactsHelper.MAIL, contact.getMail());
        values.put(ContactsHelper.CELL, contact.getCell());
        values.put(ContactsHelper.ADDRESS, contact.getAddress());
        values.put(ContactsHelper.ADDRESS_NUMBER, contact.getAddressnumber());
        values.put(ContactsHelper.CAP, contact.getCap());
        values.put(ContactsHelper.CITY, contact.getCity());
        values.put(ContactsHelper.PROVINCE, contact.getProvince());
        values.put(ContactsHelper.LAT, contact.getLatitude());
        values.put(ContactsHelper.LONG, contact.getLongitude());

        getContentResolver().insert(ContactContentProvider.CONTACTS_URI, values);
    }
    //endregion

    //region Menu Methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_menu, menu);

        searchView = (SearchView) menu.findItem(R.id.btnSearch).getActionView();


        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnAdd:
                addClicked();
                break;
            case R.id.btnSearch:
                searchView.onActionViewExpanded();
                break;

        }

        return true;
    }
    //endregion

    //region Loader Interface
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this, ContactContentProvider.CONTACTS_URI, null, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
    //endregion
}
