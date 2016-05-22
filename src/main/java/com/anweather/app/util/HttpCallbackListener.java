package com.anweather.app.util;

/**
 * Created by mzbb on 2016/5/22.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
