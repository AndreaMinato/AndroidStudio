package com.example.andreaits.dungeonsdragonscharactermanager.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.andreaits.dungeonsdragonscharactermanager.R;

/**
 * Created by andreaits on 08/02/17.
 */

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    public TextView txtName;
    public TextView txtRace;
    public TextView txtClass;
    public TextView txtLevel;
    public TextView txtExp;


    public CharacterViewHolder(View itemView) {
        super(itemView);
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtRace = (TextView) itemView.findViewById(R.id.txtRace);
        txtClass = (TextView) itemView.findViewById(R.id.txtClass);
        txtLevel = (TextView) itemView.findViewById(R.id.txtlevel);
        txtExp = (TextView) itemView.findViewById(R.id.txtExp);
    }
}
