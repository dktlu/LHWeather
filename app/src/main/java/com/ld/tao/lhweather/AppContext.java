package com.ld.tao.lhweather;

import com.ld.tao.lhweather.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

import cn.finalteam.okhttpfinal.OkHttpFinal;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * Created by tao on 2016/1/6.
 */
public class AppContext extends BaseApplication {

    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        Map<String, String> commonParamMap = new HashMap<>();
        Map<String, String> commonHeaderMap  = new HashMap<>();

        OkHttpFinal okHttpFinal = new OkHttpFinal.Builder()
                .setCommenParams(commonParamMap)
                .setCommenHeader(commonHeaderMap)
                .setTimeout(Constants.REQ_TIMEOUT)
                .setDebug(true)
                        //.setCertificates(...)
                        //.setHostnameVerifier(new SkirtHttpsHostnameVerifier())
                .build();
        okHttpFinal.init();
    }

    public static AppContext getInstance() {
        return instance;
    }
}
