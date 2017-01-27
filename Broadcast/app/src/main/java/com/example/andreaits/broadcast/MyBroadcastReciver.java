package com.example.andreaits.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by andreaits on 20/01/17.
 */

public class MyBroadcastReciver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReciver";

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Action: " + intent.getAction() + "\n");
        stringBuilder.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = stringBuilder.toString();
        Log.d(TAG, log);

        Toast.makeText(context, log, Toast.LENGTH_SHORT).show();
    }


}
