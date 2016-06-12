package com.anotherdev.photos500.presenter;

import com.anotherdev.photos500.api.dto.PhotoPage;
import com.anotherdev.photos500.domain.GetPhotosInCategory;
import com.anotherdev.photos500.model.Category;
import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;

import java.util.Collection;

public class PhotoPresenter extends P5Presenter<PhotoPresenter.View> {

    private static final int NUMBER_OF_PHOTOS_PER_PAGE = 20;

    private final GetPhotosInCategory getPhotosInCategoryUseCase;

    private Category category = Category.UNCATEGORIZED;
    private int offset = 1;


    public PhotoPresenter(UseCaseHandler handler, GetPhotosInCategory getPhotosUseCase) {
        super(handler);
        getPhotosInCategoryUseCase = getPhotosUseCase;
    }

    @Override
    public void update() {
        super.update();
        loadPhotos();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void onPhotoClicked() {
        getView().viewPhotoInFullscreen();
    }

    public void onLoadMore() {
        loadPhotos();
    }

    private void loadPhotos() {
        createUseCaseCall(getPhotosInCategoryUseCase)
                .args(category, Page.withOffsetAndLimit(offset, NUMBER_OF_PHOTOS_PER_PAGE))
                .onSuccess(new OnSuccessCallback() {
                    @Success public void onPhotosLoaded(PaginatedCollection<PhotoPage> photoPages) {
                        showPhotos(photoPages);
                        offset++;
                    }
                })
                .execute();
    }

    private void showPhotos(PaginatedCollection<PhotoPage> photoPages) {
        getView().showPhotos(photoPages.getItems());
        getView().showHasMore(photoPages.hasMore());
    }


    public interface View extends P5Presenter.View {

        void showPhotos(Collection<PhotoPage> photoPages);

        void showHasMore(boolean hasMore);

        void viewPhotoInFullscreen();
    }
}
