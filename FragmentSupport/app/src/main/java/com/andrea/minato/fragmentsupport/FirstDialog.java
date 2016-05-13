package com.andrea.minato.fragmentsupport;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

/**
 * Created by andrea on 22/04/16.
 */
public class FirstDialog extends DialogFragment{

    public interface IOnDialogSelected{
        public void OnButtonSelected(String str);
    }

    public static FirstDialog getInstance(){
        return new FirstDialog();
    }

    IOnDialogSelected listener = new IOnDialogSelected() {
        @Override
        public void OnButtonSelected(String str) {

        }
    };

    @Override
    public void onAttach(Context context) {
        if (getActivity() instanceof IOnDialogSelected) {
            listener = (IOnDialogSelected) getActivity();
        }
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setMessage("YoYoYo Meeeen");

        builder.setPositiveButton("Yo a te vecio", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("DIALOG", " positivo ");
                listener.OnButtonSelected("Chissene");
            }
        });
        builder.setNegativeButton("Yo un gazo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("DIALOG", " negativo ");
                listener.OnButtonSelected("Fanculo");
            }
        });
        builder.setNeutralButton("Lol", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("DIALOG", " neutrale ");
                listener.OnButtonSelected("lol"));
            }
        });

        return builder.create();
    }
}
