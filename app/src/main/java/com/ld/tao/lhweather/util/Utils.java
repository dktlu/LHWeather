package com.ld.tao.lhweather.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.ld.tao.lhweather.base.BaseApplication;

/**
 * Created by tao on 2015/11/10.
 */
public class Utils {

    /**
     * 是否有网络连接
     * @return
     */
    public static boolean hasInternet() {
        boolean flag;
        if (((ConnectivityManager) BaseApplication.context().getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}
