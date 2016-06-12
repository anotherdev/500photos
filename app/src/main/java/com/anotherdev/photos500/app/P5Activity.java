package com.anotherdev.photos500.app;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.anotherdev.photos500.R;
import com.karumi.rosie.view.PresenterLifeCycleLinker;
import com.karumi.rosie.view.RosiePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class P5Activity extends AppCompatActivity implements RosiePresenter.View {

    private final PresenterLifeCycleLinker presenterLifeCycleLinker = new PresenterLifeCycleLinker();

    @Nullable @BindView(R.id.toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int activityLayout = getActivityLayout();
        if (activityLayout > 0) {
            setContentView(activityLayout);
            ButterKnife.bind(this);

            if (toolbar != null) {
                setSupportActionBar(toolbar);
                final ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    setupSupportActionBar(actionBar);
                }
            }
        }

        presenterLifeCycleLinker.initializeLifeCycle(this, this);
    }

    @LayoutRes
    protected abstract int getActivityLayout();

    protected void setupSupportActionBar(@NonNull ActionBar actionBar) {}

    @NonNull
    protected P5App getApp() {
        return (P5App) getApplication();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterLifeCycleLinker.updatePresenters(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenterLifeCycleLinker.pausePresenters();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterLifeCycleLinker.destroyPresenters();
    }
}
