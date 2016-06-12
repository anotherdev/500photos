package com.anotherdev.photos500.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.intent.ViewPhotoInCategoryIntent;
import com.anotherdev.photos500.model.Category;
import com.anotherdev.photos500.presenter.PhotoPresenter;
import com.anotherdev.photos500.presenter.PresenterComponent;
import com.karumi.rosie.view.Presenter;

import javax.inject.Inject;

import butterknife.BindView;

public class PhotoListActivity extends P5Activity implements PhotoPresenter.View {

    @Inject @Presenter PhotoPresenter photoPresenter;

    @BindView(R.id.recyclerview) RecyclerView photoView;

    private Category category;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPhotoListView();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_photo_list;
    }

    @Override
    protected void onInjectComponent(@NonNull PresenterComponent pc) {
        pc.inject(this);
    }

    @Override
    protected void onPreparePresenter() {
        super.onPreparePresenter();
        category = ViewPhotoInCategoryIntent.getCategory(getIntent()).or(Category.UNCATEGORIZED);
        photoPresenter.setCategory(category);
        setTitle(category.apiKey());
    }

    private void initPhotoListView() {
    }

    @Override
    public void showPhotos() {
    }

    @Override
    public void viewPhotoInFullscreen() {
    }
}
