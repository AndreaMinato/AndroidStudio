package com.example.andreaits.mobileclouddatabases.database.contact;

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

import com.example.andreaits.mobileclouddatabases.database.DbHelper;

/**
 * Created by andreaits on 19/01/17.
 */

public class ContactContentProvider extends ContentProvider {

    private static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/contacts";
    private static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/contacts";

    private static final String CONTENT = "content://";
    private static final String AUTHORITY = "com.example.andrea_ContactcontentProvider";
    private static final String BASE_PATH_CONTACTS = "contacts";

    public static final Uri CONTACTS_URI = Uri.parse(CONTENT + AUTHORITY + "/" + BASE_PATH_CONTACTS);

    private static final int CONTACTS = 10;
    private static final int CONTACTS_ID = 20;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_CONTACTS, CONTACTS);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_CONTACTS + "/#", CONTACTS_ID);
    }

    private DbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();


        int uryType = URI_MATCHER.match(uri);

        switch (uryType) {
            case CONTACTS:
                queryBuilder.setTables(ContactsHelper.TABLE_NAME);
                break;
            case CONTACTS_ID:
                queryBuilder.setTables(ContactsHelper.TABLE_NAME);
                queryBuilder.appendWhere(ContactsHelper._ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        //Non serve chiudere il db perché lo chiuderà il ContentProvider
        //database.close();
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {


        int uryType = URI_MATCHER.match(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long id = 0;
        switch (uryType) {
            case CONTACTS:
                id = database.insert(ContactsHelper.TABLE_NAME, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        //database.close();
        getContext().getContentResolver().notifyChange(uri, null);
        if (id >= 0)
            return Uri.parse(CONTENT + AUTHORITY + "/" + BASE_PATH_CONTACTS + "/" + id);
        else
            return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uryType = URI_MATCHER.match(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uryType) {
            case CONTACTS:
                rowsDeleted = database.delete(ContactsHelper.TABLE_NAME, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = database.delete(ContactsHelper.TABLE_NAME, ContactsHelper._ID + "=" + id, null);
                } else {
                    rowsDeleted = database.delete(ContactsHelper.TABLE_NAME, ContactsHelper._ID + "=" + id + " and " + selection, selectionArgs);

                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        //database.close();
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        int uryType = URI_MATCHER.match(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uryType) {
            case CONTACTS:
                rowsUpdated = database.update(ContactsHelper.TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = database.update(ContactsHelper.TABLE_NAME, contentValues, ContactsHelper._ID + "=" + id, null);
                } else {
                    rowsUpdated = database.update(ContactsHelper.TABLE_NAME, contentValues, ContactsHelper._ID + "=" + id + " and " + selection, selectionArgs);

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
