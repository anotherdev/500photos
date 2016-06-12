package com.anotherdev.photos500.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides CategoryPresenter provideCategoryPresenter(UseCaseHandler handler) {
        return new CategoryPresenter(handler);
    }
}
