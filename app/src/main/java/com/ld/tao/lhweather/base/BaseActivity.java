package com.ld.tao.lhweather.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.ld.tao.lhweather.AppManager;
import com.ld.tao.lhweather.http.MyHttpCycleContext;
import com.ld.tao.lhweather.interf.DialogControl;
import com.ld.tao.lhweather.util.DialogHelp;

import butterknife.ButterKnife;
import cn.finalteam.okhttpfinal.HttpTaskHandler;

/**
 * Created by tao on 2015/11/10.
 */
public abstract class BaseActivity extends Activity implements MyHttpCycleContext, DialogControl {

    private int layoutId;
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private boolean isVisible;
    private ProgressDialog waitDialog;

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

        isVisible = true;
    }

    protected void init(Bundle savedInstanceState) {

    }

    /**
     * 获取xml界面的资源id
     *
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

    @Override
    public void hideWaitDialog() {
        if (isVisible && waitDialog != null) {
            try {
                waitDialog.dismiss();
                waitDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ProgressDialog showWaitDialog() {
        return showWaitDialog("加载中…");
    }

    @Override
    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public ProgressDialog showWaitDialog(String message) {
        if (isVisible) {
            if (waitDialog == null) {
                waitDialog = DialogHelp.getWaitDialog(this, message);
            }
            if (waitDialog != null) {
                waitDialog.setMessage(message);
                waitDialog.show();
            }
            return waitDialog;
        }
        return null;
    }
}
