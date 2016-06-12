package com.anotherdev.photos500.app;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.anotherdev.photos500.R;
import com.anotherdev.photos500.presenter.PresenterComponent;
import com.karumi.rosie.view.PresenterLifeCycleLinker;
import com.karumi.rosie.view.loading.RosiePresenterWithLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class P5Activity extends AppCompatActivity implements RosiePresenterWithLoading.View {

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

        onInjectComponent(getApp().getPresenterComponent());

        onPreparePresenter();
        presenterLifeCycleLinker.initializeLifeCycle(this, this);
    }

    @LayoutRes
    protected abstract int getActivityLayout();

    protected void setupSupportActionBar(@NonNull ActionBar actionBar) {}

    protected void onInjectComponent(@NonNull PresenterComponent pc) {}

    protected void onPreparePresenter() {}

    @NonNull
    protected P5App getApp() {
        return (P5App) getApplication();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (android.R.id.home == itemId) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterLifeCycleLinker.updatePresenters(this);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
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
