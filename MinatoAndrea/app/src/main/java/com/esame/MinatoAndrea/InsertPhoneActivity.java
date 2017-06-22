package com.esame.MinatoAndrea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertPhoneActivity extends Activity {

    private static final String TAG = "InsertPhoneActivity";
    public static final String SAVE_PHONE = "phone";
    EditText editTextPhone;

    String name;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String savePhone = editTextPhone.getText().toString();
        outState.putString(SAVE_PHONE, savePhone);
        outState.putString(InsertNameActivity.SAVE_NAME, name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_phone);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString(MainActivity.EXTRA_NAME);
            editTextPhone.setText(extras.getString(MainActivity.EXTRA_PHONE));
        }

        if (savedInstanceState != null) {
            editTextPhone.setText(savedInstanceState.getString(SAVE_PHONE));
            name = savedInstanceState.getString(InsertNameActivity.SAVE_NAME);
        }

        Button btnNext = (Button) findViewById(R.id.btnToNumber);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNumber();
            }
        });

        Button btnPrevious = (Button) findViewById(R.id.btnReturnName);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.btnCancelPhone);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }

    private void previous() {
        Intent intent = new Intent(this, InsertNameActivity.class);
        intent.putExtra(MainActivity.EXTRA_NAME, name);
        startActivity(intent);
    }

    private void cancel() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void goToNumber() {
        if (!editTextPhone.getText().toString().trim().equals("")) {
            Intent intent = new Intent(this, InsertNumberActivity.class);
            String phone = editTextPhone.getText().toString();
            intent.putExtra(MainActivity.EXTRA_PHONE, phone);
            intent.putExtra(MainActivity.EXTRA_NAME, name);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Insert your telephone number, please", Toast.LENGTH_SHORT).show();
        }
    }
}
