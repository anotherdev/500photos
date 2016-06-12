package com.anotherdev.photos500.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anotherdev.photos500.model.Category;
import com.anotherdev.photos500.presenter.CategoryPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class CategoryRenderer extends P5Renderer<Category> {

    private final CategoryPresenter presenter;

    @BindView(android.R.id.text1) TextView textView;


    public CategoryRenderer(CategoryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
    }

    @Override
    public void render() {
        super.render();
        Category category = getContent();
        textView.setText(category.apiKey());
    }

    @OnClick(android.R.id.text1)
    public void onCategoryClicked() {
        presenter.onCategoryClicked(getContent());
    }
}
