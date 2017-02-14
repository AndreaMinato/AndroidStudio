package com.example.andreaits.testservice.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.andreaits.testservice.MainActivity;


public class TestService extends Service {

    private static final String TAG = "TEST SERVICE - Service";

    private MyBinder binder;

    public TestService() {
        super();
        Log.d(TAG, "TestService: ");
    }

    private void timerLog() {
        try {
            for (int i = 0; i < 500; i++) {
                Thread.sleep(100);
                Log.d(TAG, "TIMER: " + i);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String s = bundle.getString(MainActivity.KEY);
            Log.d(TAG, s);
        }

        //return Service.START_STICKY le volte che viene lanciato dopo la prima passa intent nullo
        //return SERVICE.REDELIVER_INTENT passa sempre l'intent iniziale

        IntentFilter intentFilter = new IntentFilter(MainActivity.INTENT);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        binder = new MyBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        Log.d(TAG, "onDestroy: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);

    }

    public class MyBinder extends Binder {
        public TestService getService() {
            return TestService.this;
        }
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            timerLog();
            stopSelf();
        }
    };
}
