package com.example.andreaits.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView txtLabel;
    int count = 0;

    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLabel = (TextView) findViewById(R.id.txtlabel);

        txtLabel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txtLabel.setText("");
                return false;
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                count++;
                String log = "Action: " + intent.getAction() + "\n" + "URI: " + intent.toUri(Intent.URI_INTENT_SCHEME) + "\n";
//
//            Bundle extras = intent.getExtras();
//
//            KeyEvent keyEvent = null;
//            if (extras != null) {
//                keyEvent = extras.getParcelable(Intent.EXTRA_KEY_EVENT);
//            }
//
//            if (keyEvent != null) {
//                log += keyEvent.toString();
//            }

                txtLabel.append(count + " - " + log + "\n");
            }
        };

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        intentFilter.setPriority(999);

        registerReceiver(receiver, intentFilter);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyDown() called with: keyCode = [" + keyCode + "], event = [" + event + "]");
        return super.onKeyDown(keyCode, event);
    }
}
