package com.ld.tao.lhweather.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.ld.tao.lhweather.R;
import com.ld.tao.lhweather.base.BaseActivity;
import com.ld.tao.lhweather.util.TDevice;
import com.ld.tao.lhweather.widget.EmptyLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.layout_error)
    EmptyLayout layoutError;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.layout_error)
    public void ssh() {
        Toast.makeText(this, TDevice.getNetworkType() + "", Toast.LENGTH_SHORT).show();
    }

}
