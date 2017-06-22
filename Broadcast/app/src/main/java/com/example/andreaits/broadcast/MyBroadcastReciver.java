package com.example.andreaits.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by andreaits on 20/01/17.
 */

public class MyBroadcastReciver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReciver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String log = "Action: " + intent.getAction() + "\n" + "URI: " + intent.toUri(Intent.URI_INTENT_SCHEME) + "\n";

        Bundle extras = intent.getExtras();

        KeyEvent keyEvent = null;
        if (extras != null) {
            keyEvent = extras.getParcelable(Intent.EXTRA_KEY_EVENT);
        }

        if (keyEvent != null) {
            log += keyEvent.toString();
        }


        Toast.makeText(context, log, Toast.LENGTH_SHORT).show();
    }

}