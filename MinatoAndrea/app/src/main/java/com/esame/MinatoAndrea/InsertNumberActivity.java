package com.esame.MinatoAndrea;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esame.MinatoAndrea.data.ReservationContentProvider;
import com.esame.MinatoAndrea.data.ReservationHelper;

public class InsertNumberActivity extends Activity {

    private static final String TAG = "InsertNumberActivity";
    public static final String SAVE_NUMBER = "number";

    EditText editTextNumber;

    String name;
    String phone;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!editTextNumber.getText().toString().trim().equals("")) {
            int saveNumber = Integer.parseInt(editTextNumber.getText().toString());
            outState.putInt(SAVE_NUMBER, saveNumber);
        }
        outState.putString(InsertNameActivity.SAVE_NAME, name);
        outState.putString(InsertPhoneActivity.SAVE_PHONE, phone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_number);

        editTextNumber = (EditText) findViewById(R.id.editTextNumber);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString(MainActivity.EXTRA_NAME);
            phone = extras.getString(MainActivity.EXTRA_PHONE);
        }

        if (savedInstanceState != null) {
            editTextNumber.setText(savedInstanceState.getString(SAVE_NUMBER));
            phone = savedInstanceState.getString(InsertPhoneActivity.SAVE_PHONE);
            name = savedInstanceState.getString(InsertNameActivity.SAVE_NAME);
        }

        Button btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        Button btnPrevious = (Button) findViewById(R.id.btnReturnPhone);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.btnCancelNumber);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (BuildConfig.PRO) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Non mi freghi :)", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_HOME) {
            if (BuildConfig.PRO) {
                return super.onKeyDown(keyCode, event);
            } else {
                Toast.makeText(this, "Non puoi Uscire :)", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);

    }

    private void previous() {
        if (BuildConfig.PRO) {
            Intent intent = new Intent(this, InsertPhoneActivity.class);
            intent.putExtra(MainActivity.EXTRA_NAME, name);
            intent.putExtra(MainActivity.EXTRA_PHONE, phone);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Ora non puoi ne scendere ne salire, puoi solo pagare!", Toast.LENGTH_SHORT).show();
        }
    }

    private void cancel() {
        if (BuildConfig.PRO) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(this, "E non ti lascio neanche cancellare l'azione", Toast.LENGTH_SHORT).show();
        }
    }

    private void save() {
        if (BuildConfig.PRO) {
            if (!editTextNumber.getText().toString().trim().equals("")) {
                ContentValues values = new ContentValues();
                int number = Integer.parseInt(editTextNumber.getText().toString());
                values.put(ReservationHelper.NAME, name);
                values.put(ReservationHelper.CELL_PHONE, phone);
                values.put(ReservationHelper.NUMBER, number);
                getContentResolver().insert(ReservationContentProvider.RESERVATIONS_URI, values);

                cancel();
            } else {
                Toast.makeText(this, "Insert a value on the sits, please", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Speravi di aver pagato a sufficienza? E invece dovrai pagare di nuovo :)", Toast.LENGTH_SHORT).show();
        }
    }
}
