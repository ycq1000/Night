package com.example.ycq.night;

import android.app.Application;

/**
 * Created by ycg on 2016-03-17.
 */
public class MainApplication extends Application {
    private static MainApplication singleton;
    public static MainApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.singleton = this;
        android.util.Log.e("ycq", "mainApplication:oncreate");
    }

    public void say() {
        android.util.Log.e("ycq", "hello android");
    }
}
