package minato.com.threading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TimerFragment.IOnTimerUpdate {
    TextView txt;
    EditText num;
    private static final String TIMER_FRAGMENT = "Time";
    private static final String TAG = "MAIN";
    TimerFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);

        num = (EditText) findViewById(R.id.txtNum);



        Button btn = (Button) findViewById(R.id.btnStart);
        if (btn != null)
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = (TimerFragment) getSupportFragmentManager().findFragmentByTag(TIMER_FRAGMENT);
                    if (fragment == null) {
                        int value = Integer.parseInt(num.getText().toString());
                        Log.d(TAG, "onCreate: " + value);
                        if (value == 0) {
                            fragment = TimerFragment.getInstance();
                            getSupportFragmentManager().beginTransaction()
                                    .add(fragment, TIMER_FRAGMENT)
                                    .commit();
                        } else {
                            fragment = TimerFragment.getInstance(value);
                            getSupportFragmentManager().beginTransaction()
                                    .add(fragment, TIMER_FRAGMENT)
                                    .commit();
                        }
                    }
                    getSupportFragmentManager().beginTransaction()
                            .remove(fragment);
                    //fragment.start();
                }
            });
    }

    private void updateText(final int aValue) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                txt.setText("" + aValue);
            }
        });
    }

    @Override
    public void timerUpdate(int aValue) {
        updateText(aValue);
    }
}
