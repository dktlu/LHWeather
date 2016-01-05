package com.ld.tao.lhweather.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ld.tao.lhweather.R;
import com.ld.tao.lhweather.base.BaseActivity;
import com.ld.tao.lhweather.dialog.ConfirmDialog;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindView();
        initData();
    }

    public void initView() {

    }

    public void bindView() {
    }

    public void initData() {
    }

}
