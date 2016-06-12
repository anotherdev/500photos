package com.anotherdev.photos500.app;

import com.anotherdev.photos500.api.ApiComponent;
import com.anotherdev.photos500.api.ApiModule;
import com.anotherdev.photos500.domain.RosieAndroidModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class,
        AppModule.class,
        RosieAndroidModule.class
})
public interface AppComponent extends ApiComponent {
}
