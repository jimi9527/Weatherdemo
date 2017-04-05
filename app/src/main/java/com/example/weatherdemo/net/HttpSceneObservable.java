package com.example.weatherdemo.net;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.functions.Func1;

public class HttpSceneObservable<R> implements Func1<ObservableParams, R> {
    static final String TAG = "HttpSceneObservable";

    @Override
    public R call(ObservableParams params) {
        /*
        try {
            for (String name : params.data.keySet()) {
                Object value = params.data.get(name);
                if (value != null) {
                    reqJsonObj.put(name, value);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "copy data failed: " + e.getMessage());
        }

        */
        Request request = new Request.Builder()
                .url(params.reqUrl)
                .build();
        Log.e(TAG, "params.reqUrl: " + params.reqUrl);
        Call call = MyCore.getCore().getOkClient().newCall(request);
        try {
            Response response = call.execute();
            int code = response.code();
            Log.e(TAG, "code: " + code);
            if (code != 200) {
                Log.e(TAG, "http status: " + code);
                response.close();

            }

            boolean success = false;
            JSONObject respJson = null;
            String bodyStr = response.body().string();
            JSONObject jsonObject = new JSONObject(bodyStr);

            int ret = jsonObject.getInt("status");
            String errMsg = jsonObject.optString("msg");

            if (ret == 0) {
                // 只有返回值为0的时候，才回调onSuccess
                // 回复成功了
                success = true;
                respJson = jsonObject;
            }
             else {
                success = false;
                respJson = jsonObject;
            }
             response.close();
            if (success) {
                return dealRespData(respJson);
            } else {
                throw new SceneException("return code is " + ret, ret);
            }
        } catch (IOException e) {
            throw new SceneException("IOException while get response, " + e.getMessage());
        } catch (JSONException e) {
            throw new SceneException("JSONException while get response, " + e.getMessage());
        }
    }

    protected R dealRespData(JSONObject respObj) {
        return null;
    }
}
