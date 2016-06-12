package com.anotherdev.photos500.app;

import android.app.Application;

import com.anotherdev.photos500.BuildConfig;
import com.anotherdev.photos500.analytics.CrashlyticsKey;
import com.anotherdev.photos500.analytics.CrashlyticsTree;
import com.anotherdev.photos500.presenter.DaggerPresenterComponent;
import com.anotherdev.photos500.presenter.PresenterComponent;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class P5App extends Application {

    private AppComponent appComponent;
    private PresenterComponent presenterComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
        initDagger();
    }

    private void initFabric() {
        Crashlytics crashlytics = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder()
                        .build())
                .build();

        Fabric fabric = new Fabric.Builder(this)
                .kits(crashlytics)
                .debuggable(true)
                .build();

        Fabric.with(fabric);

        Timber.plant(new CrashlyticsTree());

        Crashlytics.setString(CrashlyticsKey.GIT_SHA, BuildConfig.GIT_SHA);
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .build();
        presenterComponent = DaggerPresenterComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public PresenterComponent getPresenterComponent() {
        return presenterComponent;
    }
}
