package com.anotherdev.photos500.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.api.FiveHundredPxApi;
import com.anotherdev.photos500.api.dto.Photo;
import com.anotherdev.photos500.intent.ViewPhotoInCategoryIntent;
import com.anotherdev.photos500.model.Category;
import com.anotherdev.photos500.presenter.CategoryPresenter;
import com.anotherdev.photos500.presenter.PresenterComponent;
import com.anotherdev.photos500.renderer.CategoryRenderer;
import com.google.common.collect.Lists;
import com.karumi.rosie.view.Presenter;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import timber.log.Timber;

public class CategoryActivity extends P5Activity implements CategoryPresenter.View {

    @Inject @Presenter CategoryPresenter categoryPresenter;

    @BindView(R.id.recyclerview) RecyclerView categoryView;

    RVRendererAdapter<Category> categoryAdapter;
    AdapteeCollection<Category> categoryCollection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCategoryView();

        AppComponent appComponent = getApp().getAppComponent();
        FiveHundredPxApi api = appComponent.fiveHundredPxApi();

        api.photos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Photo>() {
                    @Override
                    public void call(Photo s) {
                        Timber.e("photos: %s", s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable t) {
                        Timber.e(t, t.getMessage());
                    }
                });

        showCategories(Lists.newArrayList(Category.values()));
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected void onInjectComponent(@NonNull PresenterComponent pc) {
        pc.inject(this);
    }

    private void initCategoryView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        categoryView.setHasFixedSize(true);
        categoryView.setLayoutManager(layoutManager);

        Renderer<Category> renderer = new CategoryRenderer(categoryPresenter);
        RendererBuilder<Category> rendererBuilder = new RendererBuilder<>(renderer);
        categoryCollection = new ListAdapteeCollection<>();
        categoryAdapter = new RVRendererAdapter<>(rendererBuilder, categoryCollection);
        categoryView.setAdapter(categoryAdapter);
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoryAdapter.addAll(categories);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewPhotoInCategory(Category category) {
        ViewPhotoInCategoryIntent intent = new ViewPhotoInCategoryIntent(this, category);
        startActivity(intent);
    }
}
