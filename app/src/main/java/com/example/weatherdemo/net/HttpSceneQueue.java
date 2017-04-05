package com.example.weatherdemo.net;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.internal.Util;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class HttpSceneQueue {

    OkHttpClient mHttpClient;
    Scheduler mClientSchdulers;

    public HttpSceneQueue(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0,5,60, TimeUnit.SECONDS,new SynchronousQueue<Runnable>(),
                Util.threadFactory("OKHttp Dispatcher",false));
        mHttpClient = new OkHttpClient.Builder()
                .dispatcher(new Dispatcher(executor))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .build();
        mClientSchdulers = Schedulers.from(executor);
    }
    public Scheduler getOkClientScheduler() {
        return mClientSchdulers;
    }
}
