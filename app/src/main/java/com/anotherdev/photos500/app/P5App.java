package com.anotherdev.photos500.app;

import android.app.Application;

import com.anotherdev.photos500.BuildConfig;
import com.anotherdev.photos500.analytics.CrashlyticsKey;
import com.anotherdev.photos500.analytics.CrashlyticsTree;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class P5App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
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
}
