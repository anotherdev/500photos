package com.anotherdev.photos500.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
public class ApiModule {

    @Provides @Singleton Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new P5AuthInterceptor())
                .build();
    }

    @Provides Retrofit.Builder provideRetrofitBuilder(OkHttpClient okhttp, Gson gson) {
        return new Retrofit.Builder()
                .client(okhttp)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides @Singleton FiveHundredPxApi provideFiveHundredPxApi(Retrofit.Builder builder) {
        return builder.baseUrl(FiveHundredPxApi.BASE_URL).build().create(FiveHundredPxApi.class);
    }
}
