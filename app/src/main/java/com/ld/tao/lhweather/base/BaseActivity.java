package com.ld.tao.lhweather.base;

import android.app.Activity;
import android.os.Bundle;

import com.ld.tao.lhweather.AppManager;
import com.ld.tao.lhweather.interf.BaseViewInterface;

import butterknife.ButterKnife;

/**
 * Created by tao on 2015/11/10.
 */
public abstract class BaseActivity extends Activity {

    private int layoutId;

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
}
