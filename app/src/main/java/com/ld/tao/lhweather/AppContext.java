package com.ld.tao.lhweather;

import android.content.SharedPreferences;

import com.ld.tao.lhweather.base.BaseApplication;
import com.ld.tao.lhweather.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import cn.finalteam.okhttpfinal.OkHttpFinal;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * Created by tao on 2016/1/6.
 */
public class AppContext extends BaseApplication {

    private static AppContext instance;
    private static String LAST_REFRESH_TIME = "last_refresh_time.pref";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        Map<String, String> commonParamMap = new HashMap<>();
        Map<String, String> commonHeaderMap  = new HashMap<>();
        commonHeaderMap.put("apikey", "9ddab0a07f9e0a30c8db0478182e953e");

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

    /**
     * 记录列表上次的刷新时间
     * @param key
     * @param value
     */
    public static void putToLastRefreshTime(String key, String value) {
        SharedPreferences preferences = getPreferences(LAST_REFRESH_TIME);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取列表的上次刷新时间
     * @param key
     * @return
     */
    public static String getLaseRefreshTime(String key) {
        return getPreferences(LAST_REFRESH_TIME).getString(key, StringUtils.getCurTimeStr());
    }
}
