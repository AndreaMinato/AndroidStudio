package com.example.andreaits.dungeonsdragonscharactermanager.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andreaits.dungeonsdragonscharactermanager.R;
import com.example.andreaits.dungeonsdragonscharactermanager.Models.Character;


import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by andreaits on 08/02/17.
 */

public class CharactersAdapters extends RealmRecyclerViewAdapter<Character, RecyclerView.ViewHolder> {
    private static final String TAG = "CharactersAdapters";

    private static final int LIST_TYPE = 1;
    private static final int ADD_TYPE = 2;

    public interface AddCharacterInterface {
        void addCharacter();
    }

    private AddCharacterInterface listener = new AddCharacterInterface() {
        @Override
        public void addCharacter() {

        }
    };

    public CharactersAdapters(@NonNull Context context, @Nullable OrderedRealmCollection<Character> data, boolean autoUpdate) {
        super(data, autoUpdate);

        if (context instanceof AddCharacterInterface) {
            listener = (AddCharacterInterface) context;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;

        if (viewType == LIST_TYPE) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_character, parent, false);
            return new CharacterViewHolder(v);
        } else //(viewType == ADD_TYPE)
        {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_add_character, parent, false);
            return new AddCharacterViewHolder(v);
        }


    }

    @Override
    public int getItemCount() {
        return getData().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == getData().size()) {
            type = ADD_TYPE;
        } else {
            type = LIST_TYPE;
        }

        return type;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterViewHolder) {
            CharacterViewHolder characterViewHolder = (CharacterViewHolder) holder;
            Character character = getItem(position);
            characterViewHolder.txtName.setText(character.getName());
            characterViewHolder.txtRace.setText(character.getRace().getName());
            characterViewHolder.txtClass.setText(character.getCharacterClass());
            characterViewHolder.txtLevel.setText("Lev: " + character.getLevel());
            characterViewHolder.txtExp.setText("Exp: " + character.getExp());
        }
        if (holder instanceof AddCharacterViewHolder) {
            AddCharacterViewHolder addCharacterViewHolder = (AddCharacterViewHolder) holder;
            addCharacterViewHolder.btnAddCharacter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.addCharacter();
                }
            });
        }
    }

}
