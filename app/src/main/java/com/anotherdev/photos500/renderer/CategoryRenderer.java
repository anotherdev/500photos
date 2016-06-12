package com.anotherdev.photos500.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anotherdev.photos500.model.Category;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class CategoryRenderer extends P5Renderer<Category> {

    @BindView(android.R.id.text1) TextView textView;


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
        Category category = getContent();
        Timber.e("onCategoryClicked: %s", category.apiKey());
    }
}
