package com.example.andrea.dialogproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by andrea on 06/05/16.
 */
public class Dialog2 extends DialogFragment {
    private static final String TITLE = "title";
    private static final String TEXT = "text";
    private static final String DIALOG = "dialog1";

    public interface IOnItemSelected {
        void OnItemSelected(String str);
    }

    IOnItemSelected listener = new IOnItemSelected() {
        @Override
        public void OnItemSelected(String str) {

        }
    };

    @Override
    public void onAttach(Context context) {
        if (getActivity() instanceof IOnItemSelected) {
            listener = (IOnItemSelected) getActivity();
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getArguments().getString(TITLE))
        .setItems(getArguments().getCharSequenceArray(TEXT), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.OnItemSelected("Premuto n" + which);
            }
        });

        return builder.create();
    }


    public static Dialog2 getInstance() {
        return getInstance("Title", new String[]{"Text1", "Text2"});
    }

    public static Dialog2 getInstance(String title, CharSequence[] text) {

        Dialog2 dialog = new Dialog2();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putCharSequenceArray(TEXT, text);
        dialog.setArguments(bundle);
        return dialog;
    }

}
