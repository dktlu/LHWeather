package com.ld.tao.lhweather.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.ld.tao.lhweather.AppContext;
import com.ld.tao.lhweather.base.BaseApplication;

/**
 * Created by tao on 2016/1/6.
 */
public class TDevice {
    //手机网络类型
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static boolean GTE_HC;
    public static boolean GTE_ICS;
    public static boolean PRE_HC;
    private static Boolean hasBigScreen = null;
    private static Boolean hasCamera = null;
    private static Boolean isTablet = null;
    private static Integer loadFactor = null;

    private static int pageSize = -1;
    public static float displayDensity = 0.0F;

    public TDevice() {

    }

    /**
     * dp转换为px
     * @param dp
     * @return
     */
    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    /**
     * px转换为dp
     * @param px
     * @return
     */
    public static float pixelToDp(float px) {
        return px / (getDisplayMetrics().densityDpi / 160F);
    }

    public static int getDefaultLoadFactor() {
        if (loadFactor == null) {
            Integer integer = Integer.valueOf(0xf & BaseApplication.context()
                    .getResources().getConfiguration().screenLayout);
            loadFactor = integer;
            loadFactor = Integer.valueOf(Math.max(integer.intValue(), 1));
        }
        return loadFactor.intValue();
    }

    /**
     * 获取设备的屏幕密度
     * @return
     */
    public static float getDensity() {
        if (displayDensity == 0.0) {
            displayDensity = getDisplayMetrics().density;
        }
        return displayDensity;
    }

    /**
     * 获取设备的屏幕高度，单位是px
     * @return
     */
    public static float getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 获取设备的屏幕宽度，单位是px
     * @return
     */
    public static float getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 判断设备是否拥有相机功能
     * @return
     */
    public static final boolean hasCamera() {
        if (hasCamera == null) {
            PackageManager pckMgr = BaseApplication.context()
                    .getPackageManager();
            boolean flag = pckMgr
                    .hasSystemFeature("android.hardware.camera.front");
            boolean flag1 = pckMgr.hasSystemFeature("android.hardware.camera");
            boolean flag2;
            if (flag || flag1)
                flag2 = true;
            else
                flag2 = false;
            hasCamera = Boolean.valueOf(flag2);
        }
        return hasCamera.booleanValue();
    }

    /**
     * 判断设备是否有网络
     * @return
     */
    public static boolean hasInternet() {
        boolean flag;
        if (((ConnectivityManager) BaseApplication.context().getSystemService(
                "connectivity")).getActiveNetworkInfo() != null) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 隐藏输入法键盘
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        if (view == null) {
            return;
        } else {
            ((InputMethodManager)BaseApplication.context().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    view.getWindowToken(), 0);
        }
    }

    /**
     * 在dialog中显示输入法键盘
     * @param dialog
     */
    public static void showSoftKeyboard(Dialog dialog) {
        dialog.getWindow().setSoftInputMode(4);
    }

    /**
     * 在界面显示输入法键盘
     * @param view
     */
    public static void showSoftKeyboard(View view) {
        ((InputMethodManager) BaseApplication.context().getSystemService(
                Context.INPUT_METHOD_SERVICE)).showSoftInput(view,
                InputMethodManager.SHOW_FORCED);
    }

    /**
     * 自适应输入法
     * @param view
     */
    public static void toogleSoftKeyboard(View view) {
        ((InputMethodManager) BaseApplication.context().getSystemService(
                Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断是否存在sd卡
     * @return
     */
    public static boolean isSdcardReady() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 获取设备分辨率信息
     * @return
     */
    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) BaseApplication.context().getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * 设置为全屏
     * @param activity
     */
    public static void setFullScreen(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow()
                .getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(params);
        activity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * 退出全屏
     * @param activity
     */
    public static void cancelFullScreen(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow()
                .getAttributes();
        params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setAttributes(params);
        activity.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public static int getNetworkType() {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) AppContext
                .getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }
}
