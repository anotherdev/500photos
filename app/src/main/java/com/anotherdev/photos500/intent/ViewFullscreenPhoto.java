package com.anotherdev.photos500.intent;

import android.content.Context;
import android.content.Intent;

import com.anotherdev.photos500.api.dto.Photo;
import com.anotherdev.photos500.app.FullscreenPhotoActivity;
import com.google.common.base.Optional;

public class ViewFullscreenPhoto extends P5Intent {

    private static final String EXTRA_IMAGE_URL = EXTRA + "image.url";
    private static final String EXTRA_NAME = EXTRA + "name";
    private static final String EXTRA_TITLE = EXTRA + "title";


    public ViewFullscreenPhoto(Context context, final Photo photo) {
        super(context, FullscreenPhotoActivity.class);
        putExtra(EXTRA_IMAGE_URL, photo.getImageUrl());
        putExtra(EXTRA_NAME, photo.getUser().getFullname());
        putExtra(EXTRA_TITLE, photo.getName());
    }


    public static Optional<String> getImageUrl(Intent intent) {
        return Optional.fromNullable(intent.getStringExtra(EXTRA_IMAGE_URL));
    }

    public static Optional<String> getName(Intent intent) {
        return Optional.fromNullable(intent.getStringExtra(EXTRA_NAME));
    }

    public static Optional<String> getTitle(Intent intent) {
        return Optional.fromNullable(intent.getStringExtra(EXTRA_TITLE));
    }
}
