package com.andrea.myapplication.utils;


public class Constants {

    public static final int PROFILE = 1000;
    public static final int NOTES = 1010;
    public static final int CATEGORIES = 1020;
    public static final int SETTINGS = 1030;
    public static final int LOGOUT = 1040;
    public static final int DELETE = 1050;

    public static final int MAX_NOTE_CONTENT_LENGTH = 100;

    public static final String NOTE_ID_KEY_FOR_BUNDLE = "noteIdBundled";
    public static final String CATEGORY_ID_KEY_FOR_BUNDLE = "categoryIdBundled";
    public static final String CATEGORY_NAME_KEY_FOR_BUNDLE = "categoryNameBundled";

    public static final String NOTES_TREE = "note";
    public static final String CATEGORIES_TREE = "categories";

    public static final int PORTRAIT_NOTE_LIST_COLUMNS_COUNT = 2;
    public static final int LANDSCAPE_NOTE_LIST_COLUMNS_COUNT = 3;


    public static final String STORAGE_FIREBASE = "gs://notesonfire-c18ef.appspot.com/";
    public static final String TYPE_AUDIO = "AUDIO";
    public static final String TYPE_IMAGE = "IMAGE";
    public static final String TYPE_SKETCH = "SKETCH";
    public static final String ATTACHMENTS_FOLDER = "AttachmentFolder";
    public static final String MIME_TYPE_SKETCH_EXT = ".jpg";
}
