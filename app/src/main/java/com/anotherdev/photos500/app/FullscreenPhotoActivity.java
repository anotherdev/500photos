package com.anotherdev.photos500.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.intent.ViewFullscreenPhoto;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class FullscreenPhotoActivity extends P5Activity {

    @BindView(R.id.fullscreen_photo_imageview) ImageView image;


    @Override
    protected int getActivityLayout() {
        return R.layout.activity_fullscreen_photo;
    }

    @Override
    protected void setupSupportActionBar(@NonNull ActionBar actionBar) {
        super.setupSupportActionBar(actionBar);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        final String name = ViewFullscreenPhoto.getName(intent).or("");
        final String title = ViewFullscreenPhoto.getTitle(intent).or("");
        actionBar.setTitle(name);
        actionBar.setSubtitle(title);
    }

    @Override
    protected void onPreparePresenter() {
        super.onPreparePresenter();
        final String imageUrl = ViewFullscreenPhoto.getImageUrl(getIntent()).or("");

        Glide.with(this)
                .load(imageUrl)
                .into(image);
    }
}
