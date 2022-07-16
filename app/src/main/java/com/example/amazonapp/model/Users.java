package com.example.amazonapp.model;

public class Users {

    String uid;
    String name;
    String email;
    String imgUri;

    public Users(String uid,String name,String email,String imgUri)
    {
        this.uid=uid;
        this.name=name;
        this.email=email;
        this.imgUri=imgUri;

    }

    public String getUid()
    {
        return uid;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getImgUri()
    {
        return imgUri;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
