package com.example.andrea.masterdetail;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ABCFragment extends Fragment {

    public interface OnButtonClicked{
        public void onSelectedValue(String aValue);
    }

    private OnButtonClicked listener = new OnButtonClicked() {
        @Override
        public void onSelectedValue(String aValue) {

        }
    };


    public static ABCFragment getInstance() {
        return new ABCFragment();
    }


    @Override
    public void onAttach(Context context) {

        if(getActivity() instanceof OnButtonClicked)
            listener = (OnButtonClicked)getActivity();
        super.onAttach(context);
    }

    public ABCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_abc, container, false);

        Button btnA = (Button) view.findViewById(R.id.btna);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyClick("A");
            }
        });

        Button btnB = (Button) view.findViewById(R.id.btnb);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyClick("B");
            }
        });

        Button btnC = (Button) view.findViewById(R.id.btnc);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyClick("C");
            }
        });

        return view;
    }

    private void notifyClick(String value){
        listener.onSelectedValue(value);
    }

}
