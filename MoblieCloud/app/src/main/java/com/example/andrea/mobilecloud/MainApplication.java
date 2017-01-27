package com.example.andrea.mobilecloud;

import android.app.Application;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by andreaits on 22/12/16.
 */

public class MainApplication extends Application {

    private static final String TAG = "MainApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        Log.d(TAG, "TOKEN: " + FirebaseInstanceId.getInstance().getToken());
    }



}
