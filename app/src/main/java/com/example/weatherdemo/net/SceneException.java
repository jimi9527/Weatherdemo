package com.example.weatherdemo.net;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class SceneException extends RuntimeException {

    private static final long serialVersionUID = -6483894414333873347L;

    int mErrCode = -1;

    public SceneException(String detailMessage) {
        super(detailMessage);
    }

    public SceneException(String detailMessage, int errCode) {
        super(detailMessage);
        mErrCode = errCode;
    }

    public int getErrCode() {
        return mErrCode;
    }

}
