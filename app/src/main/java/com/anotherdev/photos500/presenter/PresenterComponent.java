package com.anotherdev.photos500.presenter;

import com.anotherdev.photos500.api.ApiModule;
import com.anotherdev.photos500.app.AppModule;
import com.anotherdev.photos500.app.CategoryListActivity;
import com.anotherdev.photos500.app.PhotoListActivity;
import com.anotherdev.photos500.domain.RosieAndroidModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class,
        AppModule.class,
        PresenterModule.class,
        RosieAndroidModule.class
})
public interface PresenterComponent {

    void inject(CategoryListActivity activity);
    void inject(PhotoListActivity activity);
}
