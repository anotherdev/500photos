package com.anotherdev.photos500.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final P5App application;


    public AppModule(P5App app) {
        application = app;
    }

    @Provides @Singleton P5App provideApp() {
        return application;
    }
}
