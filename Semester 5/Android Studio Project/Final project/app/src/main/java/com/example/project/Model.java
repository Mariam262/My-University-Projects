package com.example.project;


import android.graphics.Bitmap;

public class Model
{
    //private String name, email , phonenum;
    private Bitmap ProfileImage;

    public Model(Bitmap profileImage) {
        ProfileImage = profileImage;
    }

    public Bitmap getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        ProfileImage = profileImage;
    }
}
