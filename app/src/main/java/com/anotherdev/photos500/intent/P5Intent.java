package com.anotherdev.photos500.intent;

import android.content.Context;
import android.content.Intent;

import com.anotherdev.photos500.BuildConfig;

public abstract class P5Intent extends Intent {

    public static final String ACTION = BuildConfig.APPLICATION_ID + ".intent.action.";
    public static final String EXTRA = BuildConfig.APPLICATION_ID + ".intent.extra.";


    P5Intent(String action) {
        super(action);
    }

    P5Intent(Context context, Class<?> cls) {
        super(context, cls);
    }
}
