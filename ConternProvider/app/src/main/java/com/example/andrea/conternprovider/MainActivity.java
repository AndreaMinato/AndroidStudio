package com.example.andrea.conternprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.andrea.conternprovider.data.ContactContentProvider;
import com.example.andrea.conternprovider.data.ContactCursorAdapter;
import com.example.andrea.conternprovider.data.ContactsHelper;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ContactCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listView);


        adapter = new ContactCursorAdapter(this, null);
        listView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(0, null, this);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteContact(l);
                return true;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateContact(l);
            }
        });

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });

    }

    private void updateContact(long itemId) {
        Uri uriToUpdate = Uri.parse(ContactContentProvider.CONTACTS_URI + "/" + itemId);

        Random random = new Random();
        ContentValues values = new ContentValues();
        int rand = random.nextInt(100) + 1;
        values.put(ContactsHelper.NAME, "nome " + rand);
        values.put(ContactsHelper.SURNAME, "cognome " + rand);

        getContentResolver().update(uriToUpdate,values,null,null);
    }

    private void addContact() {
        Random random = new Random();

        ContentValues values = new ContentValues();

        int rand = random.nextInt(100) + 1;

        values.put(ContactsHelper.NAME, "nome " + rand);
        values.put(ContactsHelper.SURNAME, "cognome " + rand);

        getContentResolver().insert(ContactContentProvider.CONTACTS_URI, values);
    }

    private void deleteContact(long itemId) {
        Uri uriToDelete = Uri.parse(ContactContentProvider.CONTACTS_URI + "/" + itemId);
        getContentResolver().delete(uriToDelete, null, null);
    }


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
}
