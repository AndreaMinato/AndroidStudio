package com.example.andreaits.dungeonsdragonscharactermanager.Models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by andreaits on 08/02/17.
 */

public class Skill extends RealmObject implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.descricption);
    }

    protected Skill(Parcel in) {
        this.name = in.readString();
        this.descricption = in.readString();
    }

    public static final Parcelable.Creator<Skill> CREATOR = new Parcelable.Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel source) {
            return new Skill(source);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };
}
