package com.esame.MinatoAndrea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertNameActivity extends Activity {

    private static final String TAG = "InsertNameActivity";
    public static final String SAVE_NAME = "name";

    EditText editTextName;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String saveName = editTextName.getText().toString();
        outState.putString(SAVE_NAME, saveName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_name);

        editTextName = (EditText) findViewById(R.id.editTextName);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editTextName.setText(extras.getString(MainActivity.EXTRA_NAME));
        }

        if (savedInstanceState != null) {
            editTextName.setText(savedInstanceState.getString(SAVE_NAME));
        }

        Button btnNext = (Button) findViewById(R.id.btnToCellPhone);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPhone();
            }
        });


        Button btnCancel = (Button) findViewById(R.id.btnCancelName);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }

    private void cancel() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void goToPhone() {
        if (!editTextName.getText().toString().trim().equals("")) {
            Intent intent = new Intent(this, InsertPhoneActivity.class);
            String name = editTextName.getText().toString();
            intent.putExtra(MainActivity.EXTRA_NAME, name);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Insert your name, please", Toast.LENGTH_SHORT).show();
        }
    }
}
