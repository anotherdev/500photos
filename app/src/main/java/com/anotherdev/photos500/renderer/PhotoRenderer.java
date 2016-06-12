package com.anotherdev.photos500.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anotherdev.photos500.api.dto.Photo;
import com.anotherdev.photos500.presenter.PhotoPresenter;

import butterknife.BindView;

public class PhotoRenderer extends P5Renderer<Photo> {

    private final PhotoPresenter presenter;

    @BindView(android.R.id.text1) TextView textView;


    public PhotoRenderer(PhotoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
    }

    @Override
    public void render() {
        super.render();
        Photo photo = getContent();
        textView.setText(photo.getName());
    }
}
