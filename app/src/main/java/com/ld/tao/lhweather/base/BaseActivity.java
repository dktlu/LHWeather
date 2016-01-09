package com.ld.tao.lhweather.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.ld.tao.lhweather.AppManager;
import com.ld.tao.lhweather.http.MyHttpCycleContext;
import com.ld.tao.lhweather.interf.BaseViewInterface;

import butterknife.ButterKnife;
import cn.finalteam.okhttpfinal.HttpTaskHandler;

/**
 * Created by tao on 2015/11/10.
 */
public abstract class BaseActivity extends Activity implements MyHttpCycleContext {

    private int layoutId;
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        //通过注解绑定控件
        ButterKnife.bind(this);

        init(savedInstanceState);
    }

    protected void init(Bundle savedInstanceState) {

    }

    /**
     * 获取xml界面的资源id
     * @return
     */
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpTaskHandler.getInstance().removeTask(HTTP_TASK_KEY);
    }
}
