package com.anotherdev.photos500.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;

public class PhotoPresenter extends P5Presenter<PhotoPresenter.View> {

    public PhotoPresenter(UseCaseHandler handler) {
        super(handler);
    }

    public void onPhotoClicked() {
        getView().viewPhotoInFullscreen();
    }


    public interface View extends P5Presenter.View {

        void showPhotos();

        void viewPhotoInFullscreen();
    }
}
