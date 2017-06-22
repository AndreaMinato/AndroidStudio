package com.andrea.workouttimer.database;

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
import android.util.Log;

public class WorkoutContentProvider extends ContentProvider {
    public static final String WORKOUT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/workouts";
    public static final String WORKOUT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/workout";
    public static final String LAP_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/laps";
    public static final String LAP_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/lap";

    private static final String AUTHORITY = "com.minatoandrea_WorkoutContentProvider";
    private static final String WORKOUT_BASE_PATH = "workouts";
    private static final String LAP_BASE_PATH = "laps";

    public static final Uri WORKOUT_URI = Uri.parse("content://" + AUTHORITY + "/" + WORKOUT_BASE_PATH);
    public static final Uri LAP_URI = Uri.parse("content://" + AUTHORITY + "/" + LAP_BASE_PATH);

    private static final int WORKOUT = 10;
    private static final int WORKOUT_ID = 11;
    private static final int LAP = 20;
    private static final int LAP_ID = 21;
    private static final int LAP_FOR_WORKOUT = 22;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, WORKOUT_BASE_PATH, WORKOUT);
        sURIMatcher.addURI(AUTHORITY, WORKOUT_BASE_PATH + "/#", WORKOUT_ID);
        sURIMatcher.addURI(AUTHORITY, LAP_BASE_PATH, LAP);
        sURIMatcher.addURI(AUTHORITY, LAP_BASE_PATH + "/#", LAP_ID);
//        sURIMatcher.addURI(AUTHORITY, TEMPERATURE_BASE_PATH + "/" + TEMP_BY_CITY_PATH + "/#", TEMPERATURE_BY_CITY);
    }

    DbHelper mDBHelper;

    @Override
    public boolean onCreate() {
        mDBHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder vQueryBuilder = new SQLiteQueryBuilder();

        int vUriType = sURIMatcher.match(uri);
        switch (vUriType){
            case WORKOUT:
                vQueryBuilder.setTables(WorkoutHelper.TABLE_NAME);

                break;

            case WORKOUT_ID:
                vQueryBuilder.setTables(WorkoutHelper.TABLE_NAME);
                vQueryBuilder.appendWhere(WorkoutHelper._ID + "=" + uri.getLastPathSegment());

                break;

            case LAP:
                vQueryBuilder.setTables(LapHelper.TABLE_NAME);
                if (!TextUtils.isEmpty(selection)){
                    vQueryBuilder.appendWhere(selection);
                }
                break;

            case LAP_ID:
                vQueryBuilder.setTables(LapHelper.TABLE_NAME);
                vQueryBuilder.appendWhere(LapHelper._ID + "=" + uri.getLastPathSegment());
                break;

            /*case TEMPERATURE_BY_CITY:
                vQueryBuilder.setTables(LapHelper.TABLE_NAME);
                vQueryBuilder.appendWhere(LapHelper.WORKOUT_ID + "=" + uri.getLastPathSegment());
                break;*/
            default:
                throw new IllegalArgumentException();
        }


        SQLiteDatabase vDB = mDBHelper.getReadableDatabase();
        Cursor vCursor = vQueryBuilder.query(vDB, projection, selection, selectionArgs, null, null, sortOrder);
        vCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return vCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int vUriType = sURIMatcher.match(uri);
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();

        long vID = 0;
        Uri vUri;

        Log.d("Im in", "im in");

        switch (vUriType){
            case WORKOUT:
                vID = vDB.insert(WorkoutHelper.TABLE_NAME, null, values);
                vUri = Uri.parse("content://" + AUTHORITY + "/" + WORKOUT_BASE_PATH + "/" + vID);
                break;

            case LAP:
                vID = vDB.insert(LapHelper.TABLE_NAME, null, values);
                vUri = Uri.parse("content://" + AUTHORITY + "/" + LAP_BASE_PATH + "/" + vID);
                break;

            default:
                throw new IllegalArgumentException();
        }

        getContext().getContentResolver().notifyChange(uri, null);
        vDB.close();
        if (vID > 0) {
            return vUri;
        } else {
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int vUriType = sURIMatcher.match(uri);
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();

        int vRowsDeleted = 0;

        switch (vUriType){
            case WORKOUT:
                vRowsDeleted = vDB.delete(WorkoutHelper.TABLE_NAME, selection, selectionArgs);
                break;

            case WORKOUT_ID:
                String vCID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsDeleted = vDB.delete(WorkoutHelper.TABLE_NAME, WorkoutHelper._ID + "=" + vCID, null);
                } else {
                    vRowsDeleted = vDB.delete(WorkoutHelper.TABLE_NAME, WorkoutHelper._ID + "=" + vCID + " and " + selection, selectionArgs);
                }
                break;

            case LAP:
                vRowsDeleted = vDB.delete(LapHelper.TABLE_NAME, selection, selectionArgs);
                break;

            case LAP_ID:
                String vTID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsDeleted = vDB.delete(LapHelper.TABLE_NAME, LapHelper._ID + "=" + vTID, null);
                } else {
                    vRowsDeleted = vDB.delete(LapHelper.TABLE_NAME, LapHelper._ID + "=" + vTID + " and " + selection, selectionArgs);
                }
                break;

            default:
                throw  new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return vRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int vUriType = sURIMatcher.match(uri);
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();
        int vRowsUpdated = 0;

        switch (vUriType){
            case WORKOUT:
                vRowsUpdated = vDB.update(WorkoutHelper.TABLE_NAME, values, selection, selectionArgs);
                break;

            case WORKOUT_ID:
                String vCID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsUpdated = vDB.update(WorkoutHelper.TABLE_NAME, values, WorkoutHelper._ID + "=" + vCID, null);
                } else {
                    vRowsUpdated = vDB.update(WorkoutHelper.TABLE_NAME, values, WorkoutHelper._ID + "=" + vCID + " and " + selection, selectionArgs);
                }
                break;

            case LAP:
                vRowsUpdated = vDB.update(LapHelper.TABLE_NAME, values, selection, selectionArgs);
                break;

            case LAP_ID:
                String vTID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsUpdated = vDB.update(LapHelper.TABLE_NAME, values, LapHelper._ID + "=" + vTID, null);
                } else {
                    vRowsUpdated = vDB.update(LapHelper.TABLE_NAME, values, LapHelper._ID + "=" + vTID + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);

        }

        if (vRowsUpdated > 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return vRowsUpdated;
    }

}
