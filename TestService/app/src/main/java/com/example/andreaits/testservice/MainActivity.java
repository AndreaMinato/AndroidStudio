package com.example.andreaits.testservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.andreaits.testservice.services.TestIntentService;
import com.example.andreaits.testservice.services.TestService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TEST SERVICE - Main";
    public static final String KEY = "KEY";
    public static final String INTENT = "START_LONG";
    public static final String END = "END";


    TextView txtLabel;
    int counter;
    boolean bound;
    Intent startServiceIntent;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            txtLabel.setText("Fine!");
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "ServiceConnection onServiceConnected: ");

            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "ServiceConnection onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLabel = (TextView) findViewById(R.id.txtLabel);

        Button btnCount = (Button) findViewById(R.id.btnCount);
        Button btnSend = (Button) findViewById(R.id.btnSend);


        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCountClick();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSendClick();
            }
        });
    }

    private void btnSendClick() {

        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(new Intent(INTENT));

    }

    private void btnCountClick() {

        txtLabel.setText("click " + counter++);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(MainActivity.END);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);

//        bindService(new Intent(this, TestService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        startServiceIntent = new Intent(this, TestIntentService.class);

        Bundle bundle = new Bundle();

        bundle.putString(KEY, "Fammi un servizietto");

        startServiceIntent.putExtras(bundle);

        startService(startServiceIntent);

        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);


        stopService(startServiceIntent);
//        if (bound) {
//            unbindService(serviceConnection);
//            bound = false;
//        }
        Log.d(TAG, "onStop: ");
    }
}
