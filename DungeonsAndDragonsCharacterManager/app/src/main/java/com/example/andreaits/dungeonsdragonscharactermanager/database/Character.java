package com.example.andreaits.dungeonsdragonscharactermanager.database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andreaits on 08/02/17.
 */

public class Character extends RealmObject {

//    @PrimaryKey
//    private long id;

    private String name;
    private int age;
    private long height;
    private long weight;
    private int exp;
    private int level;
    private int baseInizitiative;
    private int armorClass;
    private String alignement;

    private int strenght;
    private int dexterity;
    private int constitution;
    private int wisdom;
    private int charisma;
    private int intelligence;

    private RealmList<Ability> proficiencies;

    private RealmList<Skill> skills;

    private Race race;

    public Character() {
    }

    public Character(String name, int age, long height, long weight, int exp, int level, String alignement, int baseInizitiative, int armorClass, Race race) {

        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.exp = exp;
        this.level = level;
        this.alignement = alignement;
        this.baseInizitiative = baseInizitiative;
        this.armorClass = armorClass;
        this.race = race;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAlignement() {
        return alignement;
    }

    public void setAlignement(String alignement) {
        this.alignement = alignement;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
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

    public int getBaseInizitiative() {
        return baseInizitiative;
    }

    public void setBaseInizitiative(int baseInizitiative) {
        this.baseInizitiative = baseInizitiative;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public RealmList<Ability> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(RealmList<Ability> proficiencies) {
        this.proficiencies = proficiencies;
    }


    public RealmList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(RealmList<Skill> skills) {
        this.skills = skills;
    }
}
