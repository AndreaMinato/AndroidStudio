package com.example.andreaits.dungeonsdragonscharactermanager.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.andreaits.dungeonsdragonscharactermanager.Models.Character;
import com.example.andreaits.dungeonsdragonscharactermanager.Models.Race;
import com.example.andreaits.dungeonsdragonscharactermanager.R;
import com.travijuu.numberpicker.library.NumberPicker;

/**
 * Created by Andrea on 17/03/17.
 */

public class DialogAddRace extends DialogFragment {
    public static final String TAG = "DialogAddRace";
    private static final String TITLE = "TITLE";
    private static final String TEXT = "TEXT";
    private static final String CHARACTER = "CHARACTER";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_race, null);
        builder.setView(view);

        final EditText setRaceName = (EditText) view.findViewById(R.id.setRaceName);
        final NumberPicker setStr = (NumberPicker) view.findViewById(R.id.setStr);
        final NumberPicker setDex = (NumberPicker) view.findViewById(R.id.setDex);
        final NumberPicker setCha = (NumberPicker) view.findViewById(R.id.setCha);
        final NumberPicker setCon = (NumberPicker) view.findViewById(R.id.setCon);
        final NumberPicker setWis = (NumberPicker) view.findViewById(R.id.setWis);
        final NumberPicker setInt = (NumberPicker) view.findViewById(R.id.setInt);
        final EditText setClassName = (EditText) view.findViewById(R.id.setClassName);

        builder.setTitle(getArguments().getString(TITLE))
                .setMessage(getArguments().getString(TEXT))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String raceName, className;
                        int str, dex, con, cha, wis, inte;
                        raceName = setRaceName.getText().toString();
                        str = setStr.getValue();
                        dex = setDex.getValue();
                        con = setCon.getValue();
                        cha = setCha.getValue();
                        wis = setWis.getValue();
                        inte = setInt.getValue();
                        className = setClassName.getText().toString();

                        Race race = new Race(raceName, str, dex, con, cha, wis, inte);

                        Character character = getArguments().getParcelable(CHARACTER);

                        character.setRace(race);
                        character.setCharacterClass(className);

                        listener.onSaveCharacter(character);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onSaveCharacter(null);
                    }
                });

        return builder.create();
    }


    public interface SaveCharacterInterface {
        void onSaveCharacter(Character character);
    }

    SaveCharacterInterface listener = new SaveCharacterInterface() {
        @Override
        public void onSaveCharacter(Character character) {

        }
    };


    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(context);
        if (getActivity() instanceof SaveCharacterInterface) {
            listener = (SaveCharacterInterface) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static DialogAddRace getInstance() {
        return getInstance(TITLE, TEXT, new Character());
    }

    public static DialogAddRace getInstance(String title, String text, Parcelable character) {
        DialogAddRace dialog = new DialogAddRace();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(TEXT, text);
        bundle.putParcelable(CHARACTER, character);
        dialog.setArguments(bundle);
        return dialog;
    }
}