package com.anotherdev.photos500.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class P5AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final HttpUrl url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("consumer_key", "zooeGVlPWyfb2c2txzaQ8dZhYA8XhBp0O5NfAsVo")
                .build();

        final Request request = chain.request()
                .newBuilder()
                .url(url)
                .build();

        return chain.proceed(request);
    }
}
