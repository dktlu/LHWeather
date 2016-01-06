package com.ld.tao.lhweather;

import com.ld.tao.lhweather.base.BaseApplication;

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

    }

    public static AppContext getInstance() {
        return instance;
    }
}
