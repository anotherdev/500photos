package com.anotherdev.photos500.api.dto;

import com.google.gson.annotations.SerializedName;
import com.karumi.rosie.repository.datasource.Identifiable;

import java.util.Collections;
import java.util.List;

public class PhotoPage implements Identifiable<Long> {

    @SerializedName("current_page") long currentPage;
    @SerializedName("total_pages") long totalPages;
    @SerializedName("total_items") long totalItems;
    @SerializedName("photos") List<Photo> photos = Collections.emptyList();


    @Override
    public Long getKey() {
        return getCurrentPage();
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}
