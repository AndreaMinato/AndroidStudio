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
    Button btnResume;
    private static final String TIMER_FRAGMENT = "Time";
    private static final String TAG = "MAIN";
    TimerFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);

        num = (EditText) findViewById(R.id.txtNum);

        btnResume = (Button) findViewById(R.id.btnResume);
        if (btnResume != null)
            btnResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int value = Integer.parseInt(txt.getText().toString());
                    inflateAsyncFragment(value);
                }
            });

        Button btnStart = (Button) findViewById(R.id.btnStart);
        if (btnStart != null)
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int value = Integer.parseInt(num.getText().toString());
                    inflateAsyncFragment(value);
                }
            });


        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destroyAsynctFragment();

            }
        });
    }

    private void destroyAsynctFragment() {
        fragment = (TimerFragment) getSupportFragmentManager().findFragmentByTag(TIMER_FRAGMENT);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

    private void inflateAsyncFragment(int value) {
        fragment = (TimerFragment) getSupportFragmentManager().findFragmentByTag(TIMER_FRAGMENT);
        if (fragment == null) {

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
    }


    private void updateText(String aValue) {

        txt.setText(aValue);
    }

    @Override
    public void timerUpdate(int aValue) {
        if (aValue >= 0)
            updateText("" + aValue);
        if (aValue == 0)
            destroyAsynctFragment();
    }
}
