package com.anotherdev.photos500.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.api.dto.Photo;
import com.anotherdev.photos500.api.dto.PhotoPage;
import com.anotherdev.photos500.intent.ViewPhotoInCategoryIntent;
import com.anotherdev.photos500.model.Category;
import com.anotherdev.photos500.presenter.PhotoPresenter;
import com.anotherdev.photos500.presenter.PresenterComponent;
import com.anotherdev.photos500.renderer.PhotoRenderer;
import com.karumi.rosie.view.Presenter;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class PhotoListActivity extends P5Activity implements PhotoPresenter.View {

    @Inject @Presenter PhotoPresenter photoPresenter;

    @BindView(R.id.recyclerview) RecyclerView photoView;

    Category category;
    RVRendererAdapter<Photo> photoAdapter;
    AdapteeCollection<Photo> photoCollection;


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
    protected void setupSupportActionBar(@NonNull ActionBar actionBar) {
        super.setupSupportActionBar(actionBar);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onInjectComponent(@NonNull PresenterComponent pc) {
        pc.inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (android.R.id.home == itemId) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPreparePresenter() {
        super.onPreparePresenter();
        category = ViewPhotoInCategoryIntent.getCategory(getIntent()).or(Category.UNCATEGORIZED);
        photoPresenter.setCategory(category);
        setTitle(category.apiKey());
    }

    private void initPhotoListView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        photoView.setHasFixedSize(true);
        photoView.setLayoutManager(layoutManager);

        PhotoRenderer renderer = new PhotoRenderer(photoPresenter);
        RendererBuilder<Photo> rendererBuilder = new RendererBuilder<>(renderer);
        photoCollection = new ListAdapteeCollection<>();
        photoAdapter = new RVRendererAdapter<>(rendererBuilder, photoCollection);
        photoView.setAdapter(photoAdapter);
    }

    @Override
    public void showPhotos(Collection<PhotoPage> photoPages) {
        for (PhotoPage page : photoPages) {
            Timber.e("PhotoPage %s/%s total items: %s", page.getCurrentPage(), page.getTotalPages(), page.getTotalItems());
            photoAdapter.addAll(page.getPhotos());
            photoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void viewPhotoInFullscreen() {
    }
}
