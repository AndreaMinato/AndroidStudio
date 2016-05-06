package com.example.andrea.dialogproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by andrea on 06/05/16.
 */
public class Dialog1 extends DialogFragment {
    private static final String DIALOG = "dialog1";
    private static final String TITLE = "title";
    private static final String TEXT = "text";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getArguments().getString(TITLE))
                .setMessage(getArguments().getString(TEXT))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(DIALOG, " OK ");
                        listener.OnButtonSelected("OK");
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(DIALOG, " NO ");
                        listener.OnButtonSelected("NO");
                    }
                });

        return builder.create();
    }

    public interface IOnDialogSelected {
        void OnButtonSelected(String str);
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

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    public static Dialog1 getInstance() {
        return getInstance("Title", "Text");
    }

    public static Dialog1 getInstance(String title, String text) {

        Dialog1 dialog = new Dialog1();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(TEXT, text);
        dialog.setArguments(bundle);
        return dialog;
    }

}
