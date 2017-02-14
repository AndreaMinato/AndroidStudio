package com.example.andreaits.thread;

import android.os.AsyncTask;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView txtShish;
    private MyAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtShish = (TextView) findViewById(R.id.txtShish);

        Button btnGo = (Button) findViewById(R.id.btnGo);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    asyncTask = new MyAsyncTask();
                    asyncTask.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button btnStop = (Button) findViewById(R.id.btnStop);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    asyncTask.cancel(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void timeConsumingOperation() {
        try {
            for (int i = 0; i < 100000; i++) {
                Log.d(TAG, "Counter: " + i);
                final int count = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtShish.setText("" + count);
                    }
                });
                Thread.sleep(100);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtShish.setText("FINIO");
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute() {
            txtShish.setText("Vediamo se so contare");
        }

        @Override
        protected void onPostExecute(String s) {
            txtShish.setText(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            txtShish.setText("" + values[0]);
        }

        @Override
        protected void onCancelled() {
            txtShish.setText("Ho fallito");
        }

        @Override
        protected String doInBackground(Void... params) {
            int i = 0;
            while (!isCancelled()) {
                //for (int i = 0; i < 10; i++) {
                i++;
                Log.d(TAG, "Counter: " + i);
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "So contare";
        }
    }

}
