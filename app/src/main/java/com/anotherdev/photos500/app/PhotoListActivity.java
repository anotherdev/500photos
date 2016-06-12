package com.anotherdev.photos500.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.intent.ViewPhotoInCategoryIntent;
import com.anotherdev.photos500.model.Category;
import com.anotherdev.photos500.presenter.PresenterComponent;

import butterknife.BindView;

public class PhotoListActivity extends P5Activity {

    @BindView(R.id.recyclerview) RecyclerView categoryView;

    private Category category;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = ViewPhotoInCategoryIntent.getCategory(getIntent()).or(Category.UNCATEGORIZED);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_photo_list;
    }

    @Override
    protected void onInjectComponent(@NonNull PresenterComponent pc) {
    }

    private void initPhotoListView() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(category.apiKey());
    }
}
