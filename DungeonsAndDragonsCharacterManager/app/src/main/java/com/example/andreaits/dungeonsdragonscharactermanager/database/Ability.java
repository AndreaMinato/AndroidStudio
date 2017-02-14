package com.example.andreaits.dungeonsdragonscharactermanager.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andreaits on 08/02/17.
 */

public class Ability extends RealmObject {

//    @PrimaryKey
//    private long id;

    private String name;
    private String type;

    public Ability(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Ability() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
