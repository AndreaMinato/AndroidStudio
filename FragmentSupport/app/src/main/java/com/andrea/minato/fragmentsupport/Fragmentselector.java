package com.andrea.minato.fragmentsupport;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmentselector extends Fragment {

    public interface IOnButtonSelected {
        public void onUpdateValue(String aValue);
    }


    private static final String CURRENT_STRING = "Stringa Attuale:";
    private static final String START_STRING = "Stringa Iniziale:";

    private TextView txt;
    private String currentLabel;

    public IOnButtonSelected listener = new IOnButtonSelected() {
        @Override
        public void onUpdateValue(String aValue) {

        }
    };

    public static Fragmentselector getInstance() {
        return Fragmentselector.getInstance("--");
    }

    public static Fragmentselector getInstance(String startValue) {
        Fragmentselector fragmentselector = new Fragmentselector();

        Bundle bundle = new Bundle();
        bundle.putString(START_STRING, startValue);
        fragmentselector.setArguments(bundle);

        return fragmentselector;
    }

    public Fragmentselector() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_STRING, currentLabel);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof IOnButtonSelected) {
            listener = (IOnButtonSelected) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fViev = inflater.inflate(R.layout.fragmentselector, container, false);

        if (savedInstanceState != null) {
            currentLabel = savedInstanceState.getString(CURRENT_STRING);
        } else {
            currentLabel = getArguments().getString(START_STRING, "----");
        }

        txt = (TextView) fViev.findViewById(R.id.textView);

        Button btnA = (Button) fViev.findViewById(R.id.btna);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLabel = "A";
                    listener.onUpdateValue(currentLabel);
                updateGUI();
            }
        });

        Button btnB = (Button) fViev.findViewById(R.id.btnb);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLabel = "B";
                    listener.onUpdateValue(currentLabel);
                updateGUI();
            }
        });

        Button btnC = (Button) fViev.findViewById(R.id.btnc);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLabel = "C";
                    listener.onUpdateValue(currentLabel);
                updateGUI();
            }
        });

        updateGUI();
        return fViev;
    }


    private void updateGUI() {
        txt.setText(currentLabel);
    }

}
