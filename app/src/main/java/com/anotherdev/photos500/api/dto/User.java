package com.anotherdev.photos500.api.dto;

import com.google.gson.annotations.SerializedName;

public class User extends P5Object {

    @SerializedName("fullname") String fullname;


    public String getFullname() {
        return fullname;
    }
}
