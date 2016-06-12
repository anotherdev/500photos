package com.anotherdev.photos500.presenter;

import com.anotherdev.photos500.domain.GetPhotosInCategory;
import com.anotherdev.photos500.model.Category;
import com.karumi.rosie.domain.usecase.UseCaseHandler;

public class PhotoPresenter extends P5Presenter<PhotoPresenter.View> {

    private static final int NUMBER_OF_PHOTOS_PER_PAGE = 20;

    private final GetPhotosInCategory getPhotosInCategoryUseCase;

    private Category category = Category.UNCATEGORIZED;


    public PhotoPresenter(UseCaseHandler handler, GetPhotosInCategory getPhotosUseCase) {
        super(handler);
        getPhotosInCategoryUseCase = getPhotosUseCase;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void onPhotoClicked() {
        getView().viewPhotoInFullscreen();
    }


    public interface View extends P5Presenter.View {

        void showPhotos();

        void viewPhotoInFullscreen();
    }
}
