package com.anotherdev.photos500.repository;

import com.anotherdev.photos500.api.FiveHundredPxApi;
import com.anotherdev.photos500.api.dto.PhotoPage;
import com.anotherdev.photos500.model.Category;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;

import java.util.Collections;

import javax.inject.Inject;

public class PhotoApiDataSource extends EmptyPaginatedReadableDataSource<Long,PhotoPage> {

    private final FiveHundredPxApi api;
    private Category category = Category.UNCATEGORIZED;


    @Inject public PhotoApiDataSource(FiveHundredPxApi api) {
        this.api = api;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public PhotoPage getByKey(Long key) throws Exception {
        return super.getByKey(key);
    }

    @Override
    public PaginatedCollection<PhotoPage> getPage(Page page) throws Exception {
        final long offset = page.getOffset();
        PhotoPage photoPage = api.photos(category.apiKey(), offset).toBlocking().first();
        PaginatedCollection<PhotoPage> photoPages = new PaginatedCollection<>(Collections.singleton(photoPage));
        photoPages.setPage(page);
        photoPages.setHasMore(photoPage.getCurrentPage() <= photoPage.getTotalPages());
        return photoPages;
    }
}
