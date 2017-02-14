package com.example.andreaits.dungeonsdragonscharactermanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.andreaits.dungeonsdragonscharactermanager.adapters.CharactersAdapters;
import com.example.andreaits.dungeonsdragonscharactermanager.database.Character;
import com.example.andreaits.dungeonsdragonscharactermanager.database.Race;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CharactersAdapters charactersAdapters;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private Realm realm;

    private ArrayList<Character> characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name("db.realm")
                .build());
        realm = Realm.getDefaultInstance();


        recyclerView = (RecyclerView) findViewById(R.id.charactersList);


        RealmResults<Character> characters = realm.where(Character.class)
                .findAll();


        charactersAdapters = new CharactersAdapters(this, characters, true);


        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(charactersAdapters);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
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
}
