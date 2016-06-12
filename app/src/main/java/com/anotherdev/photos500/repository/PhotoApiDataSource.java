package com.anotherdev.photos500.repository;

import com.anotherdev.photos500.api.FiveHundredPxApi;
import com.anotherdev.photos500.api.dto.Photo;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;

import javax.inject.Inject;

public class PhotoApiDataSource extends EmptyPaginatedReadableDataSource<String,Photo> {

    private final FiveHundredPxApi api;


    @Inject public PhotoApiDataSource(FiveHundredPxApi api) {
        this.api = api;
    }

    @Override
    public Photo getByKey(String key) throws Exception {
        return super.getByKey(key);
    }

    @Override
    public PaginatedCollection<Photo> getPage(Page page) throws Exception {
        return super.getPage(page);
    }
}
