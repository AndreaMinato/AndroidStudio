package com.example.andrea.datasource.data;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by andrea on 12/2/16.
 */


//Singleton
public class DataSet {

    private static DataSet instance = null;

    public static DataSet Get(Context context) {
        if (instance == null)
            instance = new DataSet(context);

        return instance;
    }

    ArrayList<Contact> contacts;

    DbHelper dbHelper;

    private DataSet(Context context) {
        contacts = new ArrayList<>();

        dbHelper = new DbHelper(context);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public long addContact(Contact contact) {
        contact.setId(contacts.size() + 1);
        contacts.add(contact);
        return contact.getId();
    }

    public boolean removeContact(Contact contact) {
        if (contacts.contains(contact)) {
            contacts.remove(contact);
            return true;
        }
        return false;
    }

}
