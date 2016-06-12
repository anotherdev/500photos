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
import com.anotherdev.photos500.intent.ViewFullscreenPhoto;
import com.anotherdev.photos500.intent.ViewPhotoInCategoryIntent;
import com.anotherdev.photos500.model.Category;
import com.anotherdev.photos500.presenter.PhotoPresenter;
import com.anotherdev.photos500.presenter.PresenterComponent;
import com.anotherdev.photos500.renderer.PhotoListAdapteeCollection;
import com.anotherdev.photos500.renderer.PhotoRenderer;
import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
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
    PhotoListAdapteeCollection photoCollection;
    ScrollToBottomListener loadMoreListener;


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
        photoCollection = new PhotoListAdapteeCollection();
        photoAdapter = new RVRendererAdapter<>(rendererBuilder, photoCollection);
        photoView.setAdapter(photoAdapter);

        loadMoreListener = new ScrollToBottomListener(layoutManager, new ScrollToBottomListener.Listener() {
            @Override
            public void onScrolledToBottom() {
                photoPresenter.onLoadMore();
                Timber.e("onScrolledToBottom()");
            }
        });
        photoView.addOnScrollListener(loadMoreListener);
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
    public void showHasMore(final boolean hasMore) {
        photoCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void viewPhotoInFullscreen(Photo photo) {
        Timber.e("viewPhotoInFullscreen: %s", photo.getImageUrl());
        ViewFullscreenPhoto intent = new ViewFullscreenPhoto(this, photo);
        startActivity(intent);
    }
}
