package com.example.andreaits.testservice.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.andreaits.testservice.MainActivity;

/**
 * Created by andreaits on 27/01/17.
 */

public class TestIntentService extends IntentService {
    private static final String TAG = "TEST SERVICE - Intent";


    private void timerLog() {
        try {
            for (int i = 0; i < 500; i++) {
                Thread.sleep(100);
                Log.d(TAG, "TIMER: " + i);
                
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.END));
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent() called with: intent = [" + intent + "]");

        timerLog();
    }

    public TestIntentService(String name) {
        super(name);
    }

    public TestIntentService() {
        super("nome");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate() called");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }
}
