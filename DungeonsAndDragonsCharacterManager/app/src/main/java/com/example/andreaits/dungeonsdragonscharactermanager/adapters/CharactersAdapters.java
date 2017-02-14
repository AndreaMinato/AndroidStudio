package com.example.andreaits.dungeonsdragonscharactermanager.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.andreaits.dungeonsdragonscharactermanager.R;
import com.example.andreaits.dungeonsdragonscharactermanager.database.Character;

import java.util.ArrayList;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by andreaits on 08/02/17.
 */

public class CharactersAdapters extends RealmRecyclerViewAdapter<Character, RecyclerView.ViewHolder> {
    private static final String TAG = "CharactersAdapters";

    private static final int LIST_TYPE = 1;
    private static final int ADD_TYPE = 2;

    public CharactersAdapters(@NonNull Context context, @Nullable OrderedRealmCollection<Character> data, boolean autoUpdate) {
        super(context, data, autoUpdate);


    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;

        if (viewType == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_cell, parent, false);
        }
        if (viewType == 2) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_character_cell, parent, false);
        }

        return new CharacterViewHolder(v);


    }

    @Override
    public int getItemCount() {
        return getData().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getData().size() - 1) {
            return ADD_TYPE;
        }

        return LIST_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterViewHolder) {
            CharacterViewHolder characterViewHolder = (CharacterViewHolder) holder;
            Character character = getItem(position);
            characterViewHolder.txtName.setText(character.getName());
            characterViewHolder.txtRace.setText("" + character.getRace().getName());
            //holder.txtClass.setText("" + character.getClass_id());
            characterViewHolder.txtLevel.setText("" + character.getLevel());
            characterViewHolder.txtExp.setText("" + character.getExp());
        }
        if (holder instanceof AddCharacterViewHolder) {
            AddCharacterViewHolder addCharacterViewHolder = (AddCharacterViewHolder) holder;
            addCharacterViewHolder.btnAddCharacter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Toaaaaaast", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
