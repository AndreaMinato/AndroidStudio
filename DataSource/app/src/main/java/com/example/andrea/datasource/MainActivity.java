package com.example.andrea.datasource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.andrea.datasource.data.Contact;
import com.example.andrea.datasource.data.ContactsAdapter;
import com.example.andrea.datasource.data.DataSet;

public class MainActivity extends AppCompatActivity {

    DataSet dataSet;
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSet = DataSet.Get(this);

        ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new ContactsAdapter(this, dataSet.getContacts());

        listView.setAdapter(adapter);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSet.addContact(Contact.createRandomContact());
                //forzo il refresh della lisview
                adapter.notifyDataSetChanged();
            }
        });
    }
}
