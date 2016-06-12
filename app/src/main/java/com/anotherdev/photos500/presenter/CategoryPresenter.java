package com.anotherdev.photos500.presenter;

import com.anotherdev.photos500.model.Category;
import com.karumi.rosie.domain.usecase.UseCaseHandler;

import java.util.List;

public class CategoryPresenter extends P5Presenter<CategoryPresenter.View> {

    public CategoryPresenter(UseCaseHandler handler) {
        super(handler);
    }


    public interface View extends P5Presenter.View {

        void showCategories(List<Category> categories);
    }
}
