package com.anotherdev.photos500.api;

import com.anotherdev.photos500.api.dto.PhotoPage;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FiveHundredPxApi {

    String BASE_URL = "https://api.500px.com/v1/";


    @GET("photos?feature=fresh_week")
    Observable<PhotoPage> photos(@Query("only") String category, @Query("page") long page);
}
