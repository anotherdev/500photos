package com.anotherdev.photos500.app;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.api.FiveHundredPxApi;
import com.anotherdev.photos500.api.dto.Photo;
import com.anotherdev.photos500.presenter.CategoryPresenter;
import com.anotherdev.photos500.presenter.PresenterComponent;
import com.karumi.rosie.view.Presenter;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import timber.log.Timber;

public class CategoryActivity extends P5Activity implements CategoryPresenter.View {

    @Inject @Presenter CategoryPresenter categoryPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected void onInjectComponent(@NonNull PresenterComponent pc) {
        pc.inject(this);
    }
}
