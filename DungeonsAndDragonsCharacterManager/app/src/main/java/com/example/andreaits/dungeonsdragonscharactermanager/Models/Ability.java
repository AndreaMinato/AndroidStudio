package com.example.andreaits.dungeonsdragonscharactermanager.Models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by andreaits on 08/02/17.
 */

public class Ability extends RealmObject implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.type);
    }

    protected Ability(Parcel in) {
        this.name = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Ability> CREATOR = new Parcelable.Creator<Ability>() {
        @Override
        public Ability createFromParcel(Parcel source) {
            return new Ability(source);
        }

        @Override
        public Ability[] newArray(int size) {
            return new Ability[size];
        }
    };
}
