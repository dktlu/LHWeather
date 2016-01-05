package com.ld.tao.lhweather.base;

import android.app.Activity;
import android.os.Bundle;

import com.ld.tao.lhweather.interf.BaseViewInterface;

/**
 * Created by tao on 2015/11/10.
 */
public abstract class BaseActivity extends Activity {

    private int layoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
