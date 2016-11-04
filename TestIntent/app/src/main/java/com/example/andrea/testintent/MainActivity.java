package com.example.andrea.testintent;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static final int PICK_CONTACT_CODE = 919;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGui();
    }

    private void setupGui() {
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickWeb();
            }
        });

        Button btnSms = (Button) findViewById(R.id.btnSms);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSms();
            }
        });

        Button btnPick = (Button) findViewById(R.id.btnPick);
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPick();
            }
        });
    }


    private void clickPick() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent,PICK_CONTACT_CODE);
    }

    private void clickSms() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setData(Uri.parse("smsto:"));
        intent.putExtra(Intent.EXTRA_TEXT, "lol");
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);

    }

    private void clickWeb() {
        Uri uri = Uri.parse("https://www.google.com");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        Intent chooser = Intent.createChooser(intent, "Dime ti cos che te vol far");
        //oppure
        //intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(chooser);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_CODE && resultCode == RESULT_OK) {
            int i = 0;
            i++;
        }
    }
}
