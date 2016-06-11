package com.anotherdev.photos500.api;

import com.anotherdev.photos500.api.dto.Photo;

import retrofit2.http.GET;
import rx.Observable;

public interface FiveHundredPxApi {

    String BASE_URL = "https://api.500px.com/v1/";


    @GET("photos?feature=fresh_today")
    Observable<Photo> photos();
}
