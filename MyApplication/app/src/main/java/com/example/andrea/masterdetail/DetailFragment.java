package com.example.andrea.masterdetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final String BUNDLE="valore";

    public static DetailFragment getInstance(String aValue){
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE, aValue);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView txt = (TextView) view.findViewById(R.id.txtMy);

        Bundle bundle = getArguments();
        String str = bundle.getString(BUNDLE);
        txt.setText(str);

        return view;
    }

}
