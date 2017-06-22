package com.example.andreaits.dungeonsdragonscharactermanager.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by andreaits on 08/02/17.
 */

public class Character extends RealmObject implements Parcelable {

//    @PrimaryKey
//    private long id;

    private String name;
    private int age;
    private double height;
    private double weight;
    private int exp;
    private int level;
    private int proficiencyBonus;
    private int baseInizitiative;
    private int armorClass;
    private String alignement;
    private String characterClass;

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

    public Character(String name, int age, double height, double weight, int exp, String alignement) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.exp = exp;
        this.alignement = alignement;
    }

    public Character(String name, int age, double height, double weight, int exp, int level, String alignement, int baseInizitiative, int armorClass, Race race) {

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

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public double getWeight() {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeDouble(this.height);
        dest.writeDouble(this.weight);
        dest.writeInt(this.exp);
        dest.writeInt(this.level);
        dest.writeInt(this.proficiencyBonus);
        dest.writeInt(this.baseInizitiative);
        dest.writeInt(this.armorClass);
        dest.writeString(this.alignement);
        dest.writeString(this.characterClass);
        dest.writeInt(this.strenght);
        dest.writeInt(this.dexterity);
        dest.writeInt(this.constitution);
        dest.writeInt(this.wisdom);
        dest.writeInt(this.charisma);
        dest.writeInt(this.intelligence);
        dest.writeList(this.proficiencies);
        dest.writeList(this.skills);
        dest.writeParcelable(this.race, flags);
    }

    protected Character(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.height = in.readDouble();
        this.weight = in.readDouble();
        this.exp = in.readInt();
        this.level = in.readInt();
        this.proficiencyBonus = in.readInt();
        this.baseInizitiative = in.readInt();
        this.armorClass = in.readInt();
        this.alignement = in.readString();
        this.characterClass = in.readString();
        this.strenght = in.readInt();
        this.dexterity = in.readInt();
        this.constitution = in.readInt();
        this.wisdom = in.readInt();
        this.charisma = in.readInt();
        this.intelligence = in.readInt();
        this.proficiencies = new RealmList<>();
        in.readList(this.proficiencies, Ability.class.getClassLoader());
        this.skills = new RealmList<>();
        in.readList(this.skills, Skill.class.getClassLoader());
        this.race = in.readParcelable(Race.class.getClassLoader());
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
