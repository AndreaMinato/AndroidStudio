package com.example.andreaits.cityprovider.data;

/**
 * Created by andreaits on 13/01/17.
 */

public class City {

    private long id;
    private String name;


    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
