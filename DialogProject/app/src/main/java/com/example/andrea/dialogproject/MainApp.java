package com.example.andrea.dialogproject;

import android.app.Application;

/**
 * Created by andrea on 06/05/16.
 */
public class MainApp extends Application {

    private static MainApp instance;

    public static MainApp get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance=this;
    }
}
