package minato.com.threading;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
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

    private static final int CANCELLATO = -1;

    private int count;
    private MyAsyncTask myAsyncTask;
    private Thread thread;

    private IOnTimerUpdate listener = new IOnTimerUpdate() {
        @Override
        public void timerUpdate(int aValue) {

        }
    };

    public static TimerFragment getInstance() {
        return getInstance(10);
    }

    public static TimerFragment getInstance(int aValue) {
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
        if (getArguments() != null)

            count = getArguments().getInt(CONTA);
        else
            count = 0;
        super.onCreate(savedInstanceState);

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();

//        thread = new Thread(timer);
//        thread.start();
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
        myAsyncTask.cancel(true);
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
        listener.timerUpdate(aValue);
    }


    private class MyAsyncTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute() {
            onTimerValue(count);
        }

        @Override
        protected void onPostExecute(String s) {
            onTimerValue(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            onTimerValue(values[0]);
        }

        @Override
        protected void onCancelled() {
        }

        @Override
        protected String doInBackground(Void... params) {
            while (!isCancelled() && count >= 0) {
                count--;
                Log.d(TAG, "Counter: " + count);
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "So contare";
        }
    }
}

