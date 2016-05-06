package com.example.andrea.dialogproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by andrea on 06/05/16.
 */
public class Dialog3 extends DialogFragment{
    private static final String TITLE = "title";
    private static final String TEXT = "text";
    private static final String CHECK = "check";
    private static final String DIALOG = "dialog1";

    public interface IOnItemChecked {
        void OnItemChecked(boolean[] checked ,String str);
    }

    IOnItemChecked listener = new IOnItemChecked() {
        @Override
        public void OnItemChecked(boolean[] checked, String str) {

        }
    };

    @Override
    public void onAttach(Context context) {
        if (getActivity() instanceof IOnItemChecked) {
            listener = (IOnItemChecked) getActivity();
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

        final Bundle bundle = getArguments();

        builder.setTitle(bundle.getString(TITLE))
                .setMultiChoiceItems(bundle.getStringArray(TEXT), bundle.getBooleanArray(CHECK), new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(getActivity(), bundle.getStringArray(TEXT)[which], Toast.LENGTH_SHORT).show();
                        bundle.getBooleanArray(CHECK)[which] = isChecked;
                    }
                })
                .setNegativeButton("CANCELLA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.OnItemChecked( bundle.getBooleanArray(CHECK),"Annullato");
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = "";
                        boolean[] checked = bundle.getBooleanArray(CHECK);
                        String[] text = bundle.getStringArray(TEXT);

                        for (int i = 0; i < checked.length; i++) {
                            if (checked[i])
                                s += text[i] + " ";
                        }

                        listener.OnItemChecked(checked, s);
                    }
                });

        return builder.create();
    }


    public static Dialog3 getInstance() {
        return getInstance("Title", new String[]{"Text1", "Text2", "Text3"}, new boolean[]{false, false,false});
    }

    public static Dialog3 getInstance(String title, String[] text, boolean[] check) {

        Dialog3 dialog = new Dialog3();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putStringArray(TEXT, text);
        bundle.putBooleanArray(CHECK, check);
        dialog.setArguments(bundle);
        return dialog;
    }

}
