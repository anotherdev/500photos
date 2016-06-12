package com.anotherdev.photos500.domain;

import com.anotherdev.photos500.api.dto.PhotoPage;
import com.anotherdev.photos500.model.Category;
import com.anotherdev.photos500.repository.PhotoRepository;
import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;

import javax.inject.Inject;

import timber.log.Timber;

public class GetPhotosInCategory extends RosieUseCase {

    private final PhotoRepository photoRepository;


    @Inject public GetPhotosInCategory(PhotoRepository repository) {
        photoRepository = repository;
    }

    @UseCase public void getPhotos(Category category, Page page) throws Exception {
        Timber.e("GetPhotosInCategory.getPhotos() category: %s page: %s", category, page.getOffset());
        photoRepository.setCategory(category);
        PaginatedCollection<PhotoPage> photoPage = photoRepository.getPage(page);
        notifySuccess(photoPage);
    }
}
