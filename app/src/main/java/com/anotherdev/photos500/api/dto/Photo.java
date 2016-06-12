package com.anotherdev.photos500.api.dto;

import com.google.gson.annotations.SerializedName;

public class Photo extends P5Object {

    @SerializedName("name") String name;
    @SerializedName("width") int width;
    @SerializedName("height") int height;
    @SerializedName("image_url") String imageUrl;
    @SerializedName("user") User user;


    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public User getUser() {
        return user;
    }
}
