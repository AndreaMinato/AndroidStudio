package com.example.andrea.datasource.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(ContactsHelper.TABLE_NAME, null, null, null, null, null, null);

        contacts.clear();
        while (cursor.moveToNext()) {
            Contact contact = new Contact();

            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactsHelper._ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactsHelper.NAME)));
            contact.setSurname(cursor.getString(cursor.getColumnIndex(ContactsHelper.SURNAME)));

            contacts.add(contact);
        }
        cursor.close();
        database.close();

        return contacts;
    }

    public Contact addContact(Contact contact) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(ContactsHelper.NAME, contact.getName());
        value.put(ContactsHelper.SURNAME, contact.getSurname());
        //Visto che la insert ci ritorna proprio l'id del contatto inserito
        long insertedID = database.insert(ContactsHelper.TABLE_NAME, null, value);

        contact.setId(insertedID);

        database.close();


        //contact.setId(contacts.size() + 1);
        contacts.add(contact);
        return contact;

    }


    public Contact updateContact(Contact contact) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(ContactsHelper.NAME, contact.getName());
        value.put(ContactsHelper.SURNAME, contact.getSurname());

        database.update(ContactsHelper.TABLE_NAME, value, ContactsHelper._ID + '=' + contact.getId(), null);

        contacts.set(contacts.indexOf(contact), contact);

        database.close();

        return contact;

    }

    public Contact getContact(int pos) {
        return contacts.get(pos);

    }

    public boolean removeContact(long id) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        int rows = database.delete(ContactsHelper.TABLE_NAME, ContactsHelper._ID + " = " + id, null);

        int removePosition = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == id) {
                removePosition = i;
                break;
            }
        }
        if (removePosition >= 0)
            contacts.remove(removePosition);

        return (rows > 0);
    }

}
