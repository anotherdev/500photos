package com.anotherdev.photos500.presenter;

import com.anotherdev.photos500.domain.GetPhotosInCategory;
import com.karumi.rosie.domain.usecase.UseCaseHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides CategoryPresenter provideCategoryPresenter(UseCaseHandler handler) {
        return new CategoryPresenter(handler);
    }

    @Provides PhotoPresenter providePhotoPresenter(UseCaseHandler handler, GetPhotosInCategory useCase) {
        return new PhotoPresenter(handler, useCase);
    }
}
