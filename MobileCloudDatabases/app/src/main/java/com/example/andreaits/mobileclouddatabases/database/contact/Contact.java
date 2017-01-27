package com.example.andreaits.mobileclouddatabases.database.contact;

import java.util.Date;
import java.util.Random;

/**
 * Created by andreaits on 19/01/17.
 */

public class Contact {


    private long id;
    private String name;
    private String surname;
    private Date birth;
    private String mail;
    private String cell;
    private String address;
    private String addressnumber;
    private String cap;
    private String city;
    private String province;
    private long latitude;
    private long longitude;

    public Contact(String name, String surname, Date birth, String mail, String cell, String address, String addressnumber, String cap, String city, String province, long latitude, long longitude) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.mail = mail;
        this.cell = cell;
        this.address = address;
        this.addressnumber = addressnumber;
        this.cap = cap;
        this.city = city;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Contact(long id, String name, String surname, Date birth, String mail, String cell, String address, String addressnumber, String cap, String city, String province, long latitude, long longitude) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.mail = mail;
        this.cell = cell;
        this.address = address;
        this.addressnumber = addressnumber;
        this.cap = cap;
        this.city = city;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Contact() {
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressnumber() {
        return addressnumber;
    }

    public void setAddressnumber(String addressnumber) {
        this.addressnumber = addressnumber;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public static Contact createRandomContact() {
        Random random = new Random();
        int x = random.nextInt(50);
        String name = "nome " + x;
        String surname = "cognome " + x;
        Date birth = new Date();
        String mail = "mail " + x;
        String cell = "call " + x;
        String address = "address " + x;
        String addressnumber = "" + x;
        String cap = "" + x;
        String city = "city " + x;
        String province = "province " + x;
        long latitude = x + 1;
        long longitude = x + 10;


        return new Contact(name,
                surname,
                birth,
                mail,
                cell,
                address,
                addressnumber,
                cap,
                city,
                province,
                latitude,
                longitude);
    }

}
