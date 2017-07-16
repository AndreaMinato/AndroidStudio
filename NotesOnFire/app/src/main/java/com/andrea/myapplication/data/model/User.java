package com.andrea.myapplication.data.model;


import com.google.firebase.auth.FirebaseUser;

public class User {
    private String userId;
    private String userName;
    private String photoUrl;
    private String emailAddress;

    public static User anonymous() {
        User user = new User();
        user.userName = "Anonymous";
        user.photoUrl = "";
        user.emailAddress = "";
        return user;
    }

    public User() { }

    public User(FirebaseUser aFirebaseUser){
        userId = aFirebaseUser.getUid();
        userName = aFirebaseUser.getDisplayName();
        photoUrl = "";//aFirebaseUser.getPhotoUrl().toString();
        emailAddress = aFirebaseUser.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
