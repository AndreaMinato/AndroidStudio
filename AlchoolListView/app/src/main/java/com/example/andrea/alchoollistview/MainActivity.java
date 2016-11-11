package com.example.andrea.alchoollistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Alchool> alchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inizializzazione del DB non nell'onCreate.
        initDB();

        AlchoolAdapter alchoolAdapter = new AlchoolAdapter(this, alchools);
        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(alchoolAdapter);

    }

    private void initDB() {
        alchools = new ArrayList<Alchool>();
        for (int i = 0; i < 1000; i++) {
            Alchool alchool = new Alchool(i);
            alchools.add(alchool);
        }
    }
}
