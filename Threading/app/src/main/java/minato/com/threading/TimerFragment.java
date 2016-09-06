package minato.com.threading;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by andrea on 01/06/16.
 */
public class TimerFragment extends Fragment {
    private static final String TAG = "TIMER";
    private static final String CONTA = "CONTA";

    Thread thread;
    private MyTimer timer;

    private IOnTimerUpdate listener = new IOnTimerUpdate() {
        @Override
        public void timerUpdate(int aValue) {

        }
    };

    public static TimerFragment getInstance() {
        return getInstance(10);
    }

    public static TimerFragment getInstance(int aValue){
        TimerFragment timerFragment = new TimerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CONTA, aValue);
        timerFragment.setArguments(bundle);
        return timerFragment;
    }

    public static interface IOnTimerUpdate {
        void timerUpdate(int aValue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate:");
        setRetainInstance(true);
        if(getArguments()!=null)
            timer = new MyTimer(this,getArguments().getInt(CONTA));
        else
            timer = new MyTimer(this);
        super.onCreate(savedInstanceState);
        thread = new Thread(timer);
        thread.start();
    }

    public void start(){
        thread.start();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return null;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        timer.stop();
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        Activity activity = getActivity();
        if (activity instanceof IOnTimerUpdate)
            listener = (IOnTimerUpdate) getActivity();
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    private void onTimerValue(int aValue) {
        Log.d(TAG, "onTimerValue: " + aValue);
        listener.timerUpdate(aValue);
    }

    private static class MyTimer implements Runnable {
        WeakReference<TimerFragment> reference;
        int counter;
        boolean isRunning;

        public MyTimer(TimerFragment ref) {
            reference = new WeakReference<TimerFragment>(ref);
            isRunning = true;
            counter = 10;
        }

        public MyTimer(TimerFragment ref, int x) {
            reference = new WeakReference<TimerFragment>(ref);
            isRunning = true;
            counter = x;
        }

        public void stop() {
            isRunning = false;
        }

        @Override
        public void run() {
            while (isRunning) {
                if (reference.get() != null) {
                    if (counter > 0) {
                        counter--;
                        reference.get().onTimerValue(counter);
                    } else
                        stop();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}

