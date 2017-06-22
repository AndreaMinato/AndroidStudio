package com.esame.MinatoAndrea;

import android.app.Activity;
import android.app.LoaderManager;
//import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.esame.MinatoAndrea.data.ReservationContentProvider;
import com.esame.MinatoAndrea.data.ReservationCursorAdapter;
//import com.esame.MinatoAndrea.data.ReservationHelper;
//import java.util.Random;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>, DialogDelete.DialogDeleteInterface {

    private static final String TAG = "MainActivity";

    public static final String EXTRA_NAME = "reservationName";
    public static final String EXTRA_PHONE = "reservationPhone";
    public static final String EXTRA_NUMBER = "reservationNumber";

    ReservationCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listView);


        adapter = new ReservationCursorAdapter(this, null);
        listView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //deleteContact(l);
                DialogDelete dialogDelete = DialogDelete.getInstance(l);
                dialogDelete.show(getFragmentManager(), DialogDelete.LABEL);

                return true;
            }
        });


        Button btnAdd = (Button) findViewById(R.id.btnAddReservation);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReservation();
            }
        });
    }

    private void addReservation() {

        if (!BuildConfig.DEMO) {
            Intent intent = new Intent(this, InsertNameActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Speravi di avere qualcosa che funzionasse? E invece devi pagare!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteContact(long itemId) {
        Uri uriToDelete = Uri.parse(ReservationContentProvider.RESERVATIONS_URI + "/" + itemId);
        getContentResolver().delete(uriToDelete, null, null);
    }


    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this, ReservationContentProvider.RESERVATIONS_URI, null, null, null, null);

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

    @Override
    public void OnDeleteSelected(long l) {
        if (l >= 0)
            deleteContact(l);
    }
}
