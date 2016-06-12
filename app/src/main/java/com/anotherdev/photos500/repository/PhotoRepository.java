package com.anotherdev.photos500.repository;

import com.anotherdev.photos500.api.dto.PhotoPage;
import com.anotherdev.photos500.model.Category;
import com.karumi.rosie.repository.PaginatedRosieRepository;

public class PhotoRepository extends PaginatedRosieRepository<Long,PhotoPage> {

    private final PhotoApiDataSource dataSource;


    public PhotoRepository(PhotoApiDataSource dataSource) {
        this.dataSource = dataSource;
        addPaginatedReadableDataSources(dataSource);
    }

    public void setCategory(Category category) {
        dataSource.setCategory(category);
    }
}
