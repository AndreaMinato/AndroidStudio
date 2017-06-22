package com.example.andreaits.dungeonsdragonscharactermanager;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.andreaits.dungeonsdragonscharactermanager.Adapters.CharactersAdapters;
import com.example.andreaits.dungeonsdragonscharactermanager.Dialogs.DialogAddCharacter;
import com.example.andreaits.dungeonsdragonscharactermanager.Dialogs.DialogAddRace;
import com.example.andreaits.dungeonsdragonscharactermanager.Models.Character;
import com.example.andreaits.dungeonsdragonscharactermanager.Models.Race;
import com.example.andreaits.dungeonsdragonscharactermanager.Utils.Utils;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by andreaits on 03/03/17.
 */

public class MainActivity extends RealmActivity implements CharactersAdapters.AddCharacterInterface, DialogAddRace.SaveCharacterInterface {

    private static final String TAG = "MainActivity";

    private CharactersAdapters charactersAdapters;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;

    private ArrayList<Character> characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.charactersList);


        RealmResults<Character> characters = realm.where(Character.class)
                .findAll();


        charactersAdapters = new CharactersAdapters(this, characters, true);


        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(charactersAdapters);
    }

    private void create() {
        Race race = new Race("Hobbit");

        Character character = new Character();
        character.setName("First");
        character.setRace(race);
        character.setExp(0);
        character.setLevel(1);

        realm.beginTransaction();

        realm.copyToRealm(race);
        realm.copyToRealm(character);

        realm.commitTransaction();
    }

    @Override
    public void addCharacter() {
        DialogAddCharacter dialogAddCharacter = DialogAddCharacter.getInstance(getString(R.string.newCharacter), getString(R.string.setCharacterInfo));
        dialogAddCharacter.show(getSupportFragmentManager(), DialogAddCharacter.TAG);

    }

    @Override
    public void onSaveCharacter(Character character) {
        if (character != null) {
            character.setLevel(Utils.calculateLevel(character.getExp()));
            character.setProficiencyBonus(Utils.calculateProficiencyBonus(character.getLevel()));
            realm.beginTransaction();
            realm.copyToRealm(character);
            realm.commitTransaction();
        }
    }
}
