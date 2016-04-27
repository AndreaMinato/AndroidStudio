package com.andrea.minato.fragmentpro;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    public interface IUpdateText {
        public void updateText(int x);
    }

    IUpdateText mUpdate;
    int mCount = 0;
    TextView txt;
    Button btn;
    private final static String TAG = "Fragment: ";
    private final static String CONTATORE = "contami";
    private final static String INI_VAL = "inizio da:";


    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View vView = inflater.inflate(R.layout.counter_layout, container, false);
        txt = (TextView) (vView.findViewById(R.id.textView));
        if (savedInstanceState != null)
            mCount = savedInstanceState.getInt(CONTATORE);

        btn = (Button) vView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lhafattoo();
            }
        });

        txt.setText("" + mCount);
        return vView;
    }

    private void lhafattoo() {
        if (mUpdate != null) {
            mUpdate.updateText(mCount);
        }

    }

    public static MyFragment getInstance (int x){
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
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
    public void onAttach(Context ctx) {
        super.onAttach(ctx);

        Activity hostActivity = getActivity();
        if (hostActivity instanceof IUpdateText) {
            mUpdate = (IUpdateText) hostActivity;
        }

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
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
    }

    public void inc() {
        mCount++;
        txt.setText("" + mCount);
    }

    public void dec() {

        mCount--;
        txt.setText("" + mCount);
    }

}
