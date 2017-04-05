package com.example.weatherdemo.net;

import android.content.Context;

import junit.framework.Assert;

import okhttp3.OkHttpClient;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class MyCore {
    static MyCore theCore = null;
    Context context ;
    OkHttpClient mHttpClient;
    HttpSceneQueue mSceneQueue;


    public static void initialize(Context context) {
        theCore = new MyCore();
        theCore.init(context);
    }

    public static MyCore getCore() {
        Assert.assertNotNull("FuCore not initialize!", theCore);
        return theCore;
    }

    public void init(Context context) {
        this.context = context ;
        mHttpClient = new OkHttpClient();


    }

    public  HttpSceneQueue getSceneQueue() {
        if (null == mSceneQueue) {
            mSceneQueue = new HttpSceneQueue();
        }
        return mSceneQueue;
    }

    public OkHttpClient getOkClient()
    {
        return mHttpClient;
    }

}
