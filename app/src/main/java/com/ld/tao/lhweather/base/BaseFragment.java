package com.ld.tao.lhweather.base;

import android.app.Fragment;
import android.content.Context;

import com.ld.tao.lhweather.http.MyHttpCycleContext;

import cn.finalteam.okhttpfinal.HttpTaskHandler;

/**
 * Created by tao on 2016/1/8.
 */
public class BaseFragment extends Fragment implements MyHttpCycleContext {

    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HttpTaskHandler.getInstance().removeTask(HTTP_TASK_KEY);
    }
}
