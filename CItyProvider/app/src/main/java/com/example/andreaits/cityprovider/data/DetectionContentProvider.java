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

public class DetectionContentProvider extends ContentProvider {


    private static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/city";
    private static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/city";

    private static final String CONTENT = "content://";
    private static final String AUTHORITY = "com.example.andrea_detedtionContentProvider";
    private static final String BASE_PATH_DETECTION = "detection";
    public static final Uri CONTACTS_URI = Uri.parse(CONTENT + AUTHORITY + "/" + BASE_PATH_DETECTION);

    private static final int DETECTIONS = 100;
    private static final int DETECTION_ID = 200;
    private static final int DETECTIONS_FOR_A_CITY = 300;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_DETECTION, DETECTIONS);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_DETECTION + "/#", DETECTION_ID);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_DETECTION + "/city/#", DETECTIONS_FOR_A_CITY);
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
            case DETECTIONS:
                queryBuilder.setTables(DetectionHelper.TABLE_NAME);
                break;
            case DETECTION_ID:
                queryBuilder.setTables(DetectionHelper.TABLE_NAME);
                queryBuilder.appendWhere(DetectionHelper._ID + "=" + uri.getLastPathSegment());
                break;
            case DETECTIONS_FOR_A_CITY:
                queryBuilder.setTables(DetectionHelper.INNER_JOIN_WITH_CITIES);

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
            case DETECTIONS:
                id = database.insert(DetectionHelper.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        if (id >= 0)
            return Uri.parse(CONTENT + AUTHORITY + "/" + BASE_PATH_DETECTION + "/" + id);
        else
            return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uryType = URI_MATCHER.match(uri);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uryType) {
            case DETECTIONS:
                rowsDeleted = database.delete(DetectionHelper.TABLE_NAME, selection, selectionArgs);
                break;
            case DETECTION_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = database.delete(DetectionHelper.TABLE_NAME, DetectionHelper._ID + "=" + id, null);
                } else {
                    rowsDeleted = database.delete(DetectionHelper.TABLE_NAME, DetectionHelper._ID + "=" + id + " and " + selection, selectionArgs);

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
            case DETECTIONS:
                rowsUpdated = database.update(DetectionHelper.TABLE_NAME, values, selection, selectionArgs);
                break;
            case DETECTION_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = database.update(DetectionHelper.TABLE_NAME, values, DetectionHelper._ID + "=" + id, null);
                } else {
                    rowsUpdated = database.update(DetectionHelper.TABLE_NAME, values, DetectionHelper._ID + "=" + id + " and " + selection, selectionArgs);

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


