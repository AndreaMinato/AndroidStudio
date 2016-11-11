package com.example.andrea.alchoollistview;

import java.util.Random;

/**
 * Created by andrea on 11/11/16.
 */

public class Alchool {
    private int id;
    private String name;
    private int liter;

    public Alchool(int id, String name, int liter) {
        this.id = id;
        this.name = name;
        this.liter = liter;
    }

    public Alchool(int id) {
        this.id = id;
        this.name = "Birretta n°" + id;
        Random random = new Random();
        this.liter = random.nextInt(10) + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLiter() {
        return liter;
    }

    public void setLiter(int liter) {
        this.liter = liter;
    }
}
