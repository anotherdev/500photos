package com.anotherdev.photos500.api.dto;

import com.google.gson.annotations.SerializedName;
import com.karumi.rosie.repository.datasource.Identifiable;

public class P5Object implements Identifiable<Long> {

    @SerializedName("id") Long id;


    @Override
    public Long getKey() {
        return id;
    }
}
