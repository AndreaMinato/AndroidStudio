package com.example.andrea.dialogproject;

/**
 * Created by andrea on 06/05/16.
 */
public class FirstSingleton {

    private final static FirstSingleton FIRST_SINGLETON = new FirstSingleton();

    private FirstSingleton() {

    }

    public String getValue() {
        return "Ciao";
    }

    public static FirstSingleton get() {
        return FIRST_SINGLETON;
    }

}
