package com.anotherdev.photos500.api;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class
})
public interface ApiComponent {

    FiveHundredPxApi fiveHundredPxApi();
}
