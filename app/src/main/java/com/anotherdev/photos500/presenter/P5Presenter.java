package com.anotherdev.photos500.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.view.loading.RosiePresenterWithLoading;

public class P5Presenter<T extends P5Presenter.View> extends RosiePresenterWithLoading<T> {

    public P5Presenter(UseCaseHandler handler) {
        super(handler);
    }


    public interface View extends RosiePresenterWithLoading.View {

    }
}
