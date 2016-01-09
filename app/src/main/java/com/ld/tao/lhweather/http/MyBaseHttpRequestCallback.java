package com.ld.tao.lhweather.http;

import com.ld.tao.lhweather.http.mdel.BaseApiResponse;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;

/**
 * Created by tao on 2016/1/9.
 */
public class MyBaseHttpRequestCallback<T extends BaseApiResponse> extends BaseHttpRequestCallback<T> {

    @Override
    protected void onSuccess(T t) {
        int code = t.getCode();
        if (code == 1) {
            onLoginSuccess(t);
        } else {
            onLoginFailure(t);
        }
    }

    public void onLoginSuccess(T t) {

    }

    public void onLoginFailure(T t) {

    }
}
