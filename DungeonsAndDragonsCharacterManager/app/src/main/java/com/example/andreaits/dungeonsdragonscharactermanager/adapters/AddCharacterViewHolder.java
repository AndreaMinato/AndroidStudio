package com.example.andreaits.dungeonsdragonscharactermanager.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.andreaits.dungeonsdragonscharactermanager.R;

/**
 * Created by andreaits on 08/02/17.
 */

public class AddCharacterViewHolder extends RecyclerView.ViewHolder {
    public Button btnAddCharacter;


    public AddCharacterViewHolder(View itemView) {
        super(itemView);
        btnAddCharacter = (Button) itemView.findViewById(R.id.btnAddCharacter);
    }
}
