package com.example.hotelapplication.helper;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;


public class MyApp extends Application {
    private static WeakReference<Context> context;

    public static Context getContext() {
        return context.get();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = new WeakReference<>(getApplicationContext());
    }
}
