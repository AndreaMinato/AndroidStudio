package com.esame.MinatoAndrea.data;

/**
 * Created by andreaits on 24/02/17.
 */

public class Reservation {


    private long id;
    private String name;
    private String cellPhone;
    private int number;

    public Reservation(String name, String cellPhone, int number) {
        this.name = name;
        this.cellPhone = cellPhone;
        this.number = number;
    }

    public Reservation() {
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

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
