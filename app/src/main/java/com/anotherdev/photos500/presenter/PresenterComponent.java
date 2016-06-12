package com.anotherdev.photos500.presenter;

import com.anotherdev.photos500.app.AppModule;
import com.anotherdev.photos500.app.CategoryActivity;
import com.anotherdev.photos500.domain.RosieAndroidModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        PresenterModule.class,
        RosieAndroidModule.class
})
public interface PresenterComponent {

    void inject(CategoryActivity activity);
}
