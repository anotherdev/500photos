package com.anotherdev.photos500.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;

public class CategoryPresenter extends P5Presenter<CategoryPresenter.View> {

    public CategoryPresenter(UseCaseHandler handler) {
        super(handler);
    }


    public interface View extends P5Presenter.View {

    }
}
