package com.example.weatherdemo.activity;

import android.app.Application;

import com.example.weatherdemo.net.MyCore;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class WeatherApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        MyCore.initialize(this);
    }
}
