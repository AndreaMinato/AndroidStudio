package com.andrea.minato.myfragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by andrea on 15/04/16.
 */
public class MyFragment extends Fragment{
    private final static String TAG = "Fragment: ";
    private final static String CONTATORE = "contami";
    updater update;
    private int conta;

    public interface updater {
        public void updateText(int x);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity hostActivity = getActivity();
        if (hostActivity instanceof updater) {
            update = (updater) hostActivity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.content_layout, container, false);

        Button piu = (Button)vView.findViewById(R.id.piu);
        piu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conta++;
                aggiorna();
            }
        });

        Button meno = (Button)vView.findViewById(R.id.meno);
        meno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conta--;
                aggiorna();
            }
        });

        return vView;
    }

    private void aggiorna(){
        if (update != null) {
            update.updateText(conta);
        }
    }

}
