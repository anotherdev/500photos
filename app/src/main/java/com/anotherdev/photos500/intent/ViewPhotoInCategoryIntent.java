package com.anotherdev.photos500.intent;

import android.content.Context;
import android.content.Intent;

import com.anotherdev.photos500.app.PhotoListActivity;
import com.anotherdev.photos500.model.Category;
import com.google.common.base.Optional;

public class ViewPhotoInCategoryIntent extends P5Intent {

    private static final String EXTRA_CATEGORY = EXTRA + "category.id";


    public ViewPhotoInCategoryIntent(Context context, final Category category) {
        super(context, PhotoListActivity.class);
        putExtra(EXTRA_CATEGORY, category);
    }


    public static Optional<Category> getCategory(Intent intent) {
        return Optional.fromNullable((Category) intent.getSerializableExtra(EXTRA_CATEGORY));
    }
}
