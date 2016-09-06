package minato.com.exampleapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN";

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txtResult);

        MyRunnable runnable = new MyRunnable(this);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void updateText(final int aValue) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt.setText("" + aValue);
            }
        });
    }

    private static class MyRunnable implements Runnable {
        private int conta;
        // MainActivity ref;
        WeakReference<MainActivity> reference;
        boolean isRunning;

        public MyRunnable(MainActivity ref) {
            reference = new WeakReference<MainActivity>(ref);
            isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning) {
                if (reference.get() != null) {
                    Log.d(TAG, "CONTATORE: " + conta + " " + reference.get());
                    reference.get().updateText(conta);
                } else {
                    isRunning = false;
                }
                Log.d(TAG, "Timer Running: " + this);

                conta++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: " + this);
        super.onDestroy();
    }

    @Override
    protected void finalize() throws Throwable {
        Log.d(TAG, "finalize: " + this);
        super.finalize();
    }
}
