package com.example.andreaits.dungeonsdragonscharactermanager.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andreaits on 08/02/17.
 */

public class Skill extends RealmObject {

//    @PrimaryKey
//    private long id;

    private String name;
    private String descricption;

    public Skill(String name, String descricption) {
        this.name = name;
        this.descricption = descricption;
    }

    public Skill() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricption() {
        return descricption;
    }

    public void setDescricption(String descricption) {
        this.descricption = descricption;
    }
}
