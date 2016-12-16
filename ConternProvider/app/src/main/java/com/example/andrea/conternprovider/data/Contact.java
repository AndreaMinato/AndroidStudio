package com.example.andrea.conternprovider.data;

import java.util.Objects;
import java.util.Random;

/**
 * Created by andrea on 12/2/16.
 */

public class Contact {

    private long id;
    private String name;
    private String surname;

    public Contact() {
    }

    public Contact(String name, String surname) {
        this.name = name;

        this.surname = surname;
    }

    public Contact(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object contact) {
        if (contact instanceof Contact) {
            return this.getId() == ((Contact) contact).getId();
        } else
            return false;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static Contact createRandomContact() {
        Random random = new Random();

        String name = "nome " + random.nextInt();
        String surname = "cognome " + random.nextInt();

        return new Contact(name, surname);
    }
}
