package com.andrea.firebaseintegration.model;

/**
 * Created by Andrea on 20/06/17.
 */

public class User {
    private String userName;
    private String photoUrl;
    private String emailAddress;

    public User() {
    }

    public User(String aUserName, String aPhotoUrl, String aEmailAddress) {
        userName = aUserName;
        photoUrl = aPhotoUrl;
        emailAddress = aEmailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String aUserName) {
        userName = aUserName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String aPhotoUrl) {
        photoUrl = aPhotoUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String aEmailAddress) {
        emailAddress = aEmailAddress;
    }
}
