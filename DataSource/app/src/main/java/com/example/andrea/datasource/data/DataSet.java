package com.example.andrea.datasource.data;

import java.util.ArrayList;

/**
 * Created by andrea on 12/2/16.
 */


//Singleton
public class DataSet {

    private static DataSet instance = null;

    public static DataSet Get() {
        if (instance == null)
            instance = new DataSet();

        return instance;
    }

    ArrayList<Contact> contacts;

    private DataSet() {
        contacts = new ArrayList<>();
    }
}
