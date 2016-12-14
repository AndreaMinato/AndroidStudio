package com.example.andrea.datasource;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by andrea on 12/14/16.
 */

public class DialogDelete extends DialogFragment {
    private static final String DIALOG = "dialog1";
    private static final String TITLE = "title";
    private static final String L = "l";
    private static final String TEXT = "text";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final long l = getArguments().getLong(L);

        builder.setTitle(getArguments().getString(TITLE))
                .setMessage(getArguments().getString(TEXT))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(DIALOG, " OK ");
                        listener.OnDeleteSelected(l);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(DIALOG, " CANCEL ");
                        listener.OnDeleteSelected(-1);
                    }
                });

        return builder.create();
    }

    public interface IOnDialogSelected {
        void OnDeleteSelected(long l);
    }


    IOnDialogSelected listener = new IOnDialogSelected() {
        @Override
        public void OnDeleteSelected(long l) {

        }
    };

    @Override
    public void onAttach(Context context) {
        if (getActivity() instanceof IOnDialogSelected) {
            listener = (IOnDialogSelected) getActivity();
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static DialogDelete getInstance(long l) {
        return getInstance("Eliminazione", "Vuoi ELiminare il contatto?", l);
    }

    public static DialogDelete getInstance(String title, String text, long l) {

        DialogDelete dialog = new DialogDelete();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(TEXT, text);
        bundle.putLong(L, l);
        dialog.setArguments(bundle);
        return dialog;
    }

}
