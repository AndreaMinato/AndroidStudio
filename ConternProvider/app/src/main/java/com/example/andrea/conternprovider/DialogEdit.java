package com.example.andrea.conternprovider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.andrea.conternprovider.data.Contact;

/**
 * Created by andrea on 12/2/16.
 */

public class DialogEdit extends DialogFragment {
    private static final String DIALOG = "dialogEdit";
    private static final String TITLE = "TITLE";
    private static final String TEXT = "TEXT";
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String ID = "ID";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_dialog, null);
        builder.setView(view);

        final EditText editName = (EditText) view.findViewById(R.id.editName);
        final EditText editSurname = (EditText) view.findViewById(R.id.editSurname);

        //editName.setText(getArguments().getString(NAME));
        //editSurname.setText(getArguments().getString(SURNAME));

        builder.setTitle(getArguments().getString(TITLE) + " " + getArguments().getLong(ID))
                .setMessage(getArguments().getString(TEXT))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact contact = new Contact();
                        contact.setName(editName.getText().toString());
                        contact.setSurname(editSurname.getText().toString());
                        contact.setId(getArguments().getLong(ID));
                        listener.OnEditSelected(contact);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(DIALOG, " CANCEL ");
                        listener.OnEditSelected(null);
                    }
                });

        return builder.create();
    }

    public interface DialogEditInterface {
        void OnEditSelected(Contact contact);
    }


    DialogEditInterface listener = new DialogEditInterface() {
        @Override
        public void OnEditSelected(Contact contact) {

        }
    };

    @Override
    public void onAttach(Context context) {
        if (getActivity() instanceof DialogEditInterface) {
            listener = (DialogEditInterface) getActivity();
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static DialogEdit getInstance() {
        return getInstance(TITLE, TEXT);
    }

    public static DialogEdit getInstance(String title, String text) {

        DialogEdit dialog = new DialogEdit();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(TEXT, text);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static DialogEdit getInstance(String title, String text, long contactId) {

        DialogEdit dialog = new DialogEdit();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(TEXT, text);
        //bundle.putString(NAME, contact.getName());
        //bundle.putString(SURNAME, contact.getSurname());
        bundle.putLong(ID,contactId);
        dialog.setArguments(bundle);
        return dialog;
    }
}
