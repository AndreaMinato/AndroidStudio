package com.example.andreaits.dungeonsdragonscharactermanager.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andreaits on 08/02/17.
 */

public class Race extends RealmObject {

//    @PrimaryKey
//    private long id;

    private String name;

    private int strenght;
    private int dexterity;
    private int constitution;
    private int wisdom;
    private int charisma;
    private int intelligence;

    public Race(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public Race() {

    }

    public Race(String name, int strenght, int dexterity, int constitution, int wisdom, int charisma, int intelligence) {

        this.name = name;
        this.strenght = strenght;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.intelligence = intelligence;
    }
}
