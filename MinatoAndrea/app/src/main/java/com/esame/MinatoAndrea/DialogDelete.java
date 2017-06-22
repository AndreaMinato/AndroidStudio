package com.esame.MinatoAndrea;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by andreaits on 24/02/17.
 */

public class DialogDelete extends DialogFragment {
    private static final String DIALOG = "dialog1";
    private static final String TITLE = "title";
    private static final String L = "l";
    private static final String TEXT = "text";

    public static final String LABEL = "DIALOGDELETE";

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

    public interface DialogDeleteInterface {
        void OnDeleteSelected(long l);
    }


    DialogDeleteInterface listener = new DialogDeleteInterface() {
        @Override
        public void OnDeleteSelected(long l) {

        }
    };

    @Override
    public void onAttach(Context context) {
        if (getActivity() instanceof DialogDeleteInterface) {
            listener = (DialogDeleteInterface) getActivity();
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static DialogDelete getInstance(long l) {
        return getInstance("WARNING", "Are you sure you want to delete the reservation?", l);
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
