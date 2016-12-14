package com.example.andrea.datasource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andrea.datasource.data.Contact;
import com.example.andrea.datasource.data.ContactsAdapter;
import com.example.andrea.datasource.data.DataSet;

public class MainActivity extends AppCompatActivity implements DialogEdit.IOnDialogSelected, DialogDelete.IOnDialogSelected {

    DataSet dataSet;
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSet = DataSet.Get(this);

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogEdit dialogEdit = DialogEdit.getInstance("Contatto", "Modifica", dataSet.getContact(i));
                dialogEdit.show(getSupportFragmentManager(), "DIALOGEDIT");

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            //i = posizione in lista --- l = id dell'elemento (Chiama getItemId)
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                DialogDelete dialogDelete = DialogDelete.getInstance(l);
                dialogDelete.show(getFragmentManager(), "DIALOGDELETE");
                return true;
            }
        });

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


    @Override
    public void OnDeleteSelected(long l) {
        dataSet.removeContact(l);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnButtonSelected(Contact contact) {
        if (contact != null)
            dataSet.updateContact(contact);


        //Toast.makeText(MainActivity.this, contact.getSurname(), Toast.LENGTH_SHORT).show();
    }

}
