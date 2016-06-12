package com.anotherdev.photos500.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.intent.ViewPhotoInCategoryIntent;
import com.anotherdev.photos500.model.Category;
import com.anotherdev.photos500.presenter.PresenterComponent;

public class PhotoListActivity extends P5Activity {

    private Category category;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = ViewPhotoInCategoryIntent.getCategory(getIntent()).or(Category.UNCATEGORIZED);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected void onInjectComponent(@NonNull PresenterComponent pc) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(category.apiKey());
    }
}
