package com.example.andreaits.cityprovider.data;

import java.util.Date;

/**
 * Created by andreaits on 13/01/17.
 */

public class Detection {

    private long id;
    private long city_id;
    private Date date;
    private double degrees;

    public Detection(long city_id, Date date, double degrees) {
        this.city_id = city_id;
        this.date = date;
        this.degrees = degrees;
    }

    public Detection() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }
}

