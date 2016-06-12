package com.anotherdev.photos500.renderer;

import android.view.View;

import com.pedrogomez.renderers.Renderer;

import butterknife.ButterKnife;

public abstract class P5Renderer<T> extends Renderer<T> {

    @Override
    public void render() {
        ButterKnife.bind(this, getRootView());
    }

    @Override
    protected void setUpView(View rootView) {
    }

    @Override
    protected void hookListeners(View rootView) {
    }
}
