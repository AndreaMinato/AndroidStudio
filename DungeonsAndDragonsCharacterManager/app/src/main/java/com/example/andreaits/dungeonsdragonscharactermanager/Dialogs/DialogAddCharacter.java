package com.example.andreaits.dungeonsdragonscharactermanager.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.andreaits.dungeonsdragonscharactermanager.Models.Character;
import com.example.andreaits.dungeonsdragonscharactermanager.R;

/**
 * Created by andreaits on 03/03/17.
 */

public class DialogAddCharacter extends DialogFragment {
    public static final String TAG = "DialogAddCharacter";
    private static final String TITLE = "TITLE";
    private static final String TEXT = "TEXT";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_character, null);
        builder.setView(view);

        final EditText editName = (EditText) view.findViewById(R.id.setName);
        final EditText editAge = (EditText) view.findViewById(R.id.setAge);
        final EditText editHeight = (EditText) view.findViewById(R.id.setHeight);
        final EditText editWeight = (EditText) view.findViewById(R.id.setWeight);
        final EditText editExp = (EditText) view.findViewById(R.id.setExp);
        final EditText editAlignement = (EditText) view.findViewById(R.id.setAlignement);

        builder.setTitle(getArguments().getString(TITLE))
                .setMessage(getArguments().getString(TEXT))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name, alignement;
                        double height, weight;
                        int age, exp;
                        name = editName.getText().toString();
                        age = Integer.parseInt(editAge.getText().toString());
                        height = Double.parseDouble(editHeight.getText().toString());
                        weight = Double.parseDouble(editWeight.getText().toString());
                        exp = Integer.parseInt(editExp.getText().toString());
                        alignement = editAlignement.getText().toString();

                        Character character = new Character(name, age, height, weight, exp, alignement);

                        DialogAddRace dialogAddRace = DialogAddRace.getInstance(getString(R.string.newRace), getString(R.string.setRaceStats), character);
                        dialogAddRace.show(getFragmentManager(), DialogAddRace.TAG);

//                        listener.onAddCharacterClicked(character);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        listener.onAddCharacterClicked(null);
                    }
                });

        return builder.create();
    }

//    public interface CreateCharacterInterface {
//        void onAddCharacterClicked(Character character);
//    }
//
//
//    CreateCharacterInterface listener = new CreateCharacterInterface() {
//        @Override
//        public void onAddCharacterClicked(Character character) {
//        }
//    };

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
//        if (getActivity() instanceof CreateCharacterInterface) {
//            listener = (CreateCharacterInterface) getActivity();
//        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        listener = null;
    }

    public static DialogAddCharacter getInstance() {
        return getInstance(TITLE, TEXT);
    }

    public static DialogAddCharacter getInstance(String title, String text) {
        DialogAddCharacter dialog = new DialogAddCharacter();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(TEXT, text);
        dialog.setArguments(bundle);
        return dialog;
    }
}