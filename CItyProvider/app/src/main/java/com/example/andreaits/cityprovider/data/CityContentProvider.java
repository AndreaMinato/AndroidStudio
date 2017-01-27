package com.example.andreaits.cityprovider.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by andreaits on 13/01/17.
 */

public class CityContentProvider extends ContentProvider {

    private static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/city";
    private static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/city";

    private static final String CONTENT = "content://";
    private static final String AUTHORITY = "com.example.andrea_cityContentProvider";
    private static final String BASE_PATH_CITY = "city";
    public static final Uri CONTACTS_URI = Uri.parse(CONTENT + AUTHORITY + "/" + BASE_PATH_CITY);

    private static final int CITIES = 10;
    private static final int CITY_ID = 20;
    private static final int CITY_DETECTIONS = 30;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_CITY, CITIES);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_CITY + "/#", CITY_ID);
    }


    private DbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();


        int uryType = URI_MATCHER.match(uri);

        switch (uryType) {
            case CITIES:
                queryBuilder.setTables(CityHelper.TABLE_NAME);
                break;
            case CITY_ID:
                queryBuilder.setTables(CityHelper.TABLE_NAME);
                queryBuilder.appendWhere(CityHelper._ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uryType = URI_MATCHER.match(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long id = 0;
        switch (uryType) {
            case CITIES:
                id = database.insert(CityHelper.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        if (id >= 0)
            return Uri.parse(CONTENT + AUTHORITY + "/" + BASE_PATH_CITY + "/" + id);
        else
            return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uryType = URI_MATCHER.match(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uryType) {
            case CITIES:
                rowsDeleted = database.delete(CityHelper.TABLE_NAME, selection, selectionArgs);
                break;
            case CITY_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = database.delete(CityHelper.TABLE_NAME, CityHelper._ID + "=" + id, null);
                } else {
                    rowsDeleted = database.delete(CityHelper.TABLE_NAME, CityHelper._ID + "=" + id + " and " + selection, selectionArgs);

                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int uryType = URI_MATCHER.match(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uryType) {
            case CITIES:
                rowsUpdated = database.update(CityHelper.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CITY_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = database.update(CityHelper.TABLE_NAME, values, CityHelper._ID + "=" + id, null);
                } else {
                    rowsUpdated = database.update(CityHelper.TABLE_NAME, values, CityHelper._ID + "=" + id + " and " + selection, selectionArgs);

                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        //database.close();
        getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }
}
