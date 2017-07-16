package com.andrea.myapplication.utils;


public final class Errors {

    // --- singleton instance --- //
    private static Errors instance = null;

    public static synchronized Errors getInstance() {
        if (instance == null)
            instance = new Errors();
        return instance;
    }
    // --- //

    private Errors() {

    }

    public Throwable emptyNoteId() {
        return new Throwable("Empty note USER_ID.");
    }
}
