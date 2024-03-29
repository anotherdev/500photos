package com.anotherdev.photos500.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.api.dto.Photo;
import com.anotherdev.photos500.presenter.PhotoPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;

public class PhotoRenderer extends P5Renderer<Photo> {

    private final PhotoPresenter presenter;

    @BindView(R.id.progress) ProgressBar progress;
    @BindView(R.id.photo_preview_cardview) View card;
    @BindView(R.id.photo_preview_imageview) ImageView imageView;
    @BindView(R.id.photo_preview_user_name_textview) TextView name;
    @BindView(R.id.photo_preview_title_textview) TextView title;


    public PhotoRenderer(PhotoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.view_photo_preview, parent, false);
    }

    @Override
    public void render() {
        super.render();
        Photo photo = getContent();
        if (photo != null) {
            Glide.with(card.getContext())
                    .load(photo.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

            name.setText(photo.getUser().getFullname());
            title.setText(photo.getName());
            card.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
        } else {
            card.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);
        }

        card.setOnClickListener(onClick);
        card.setTag(photo);
    }


    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Object tag = v.getTag();
            if (tag instanceof Photo) {
                presenter.onPhotoClicked((Photo) tag);
            }
        }
    };
}
