package com.ld.tao.lhweather.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ld.tao.lhweather.R;
import com.ld.tao.lhweather.base.BaseActivity;
import com.ld.tao.lhweather.http.Api;
import com.ld.tao.lhweather.http.MyBaseHttpRequestCallback;
import com.ld.tao.lhweather.http.mdel.TicketDetailResponse;
import com.ld.tao.lhweather.util.TDevice;
import com.ld.tao.lhweather.widget.EmptyLayout;

import butterknife.Bind;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class MainActivity extends BaseActivity {

    @Bind(R.id.layout_error)
    EmptyLayout layoutError;
    private String[] arrays = {"相册", "相机"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestParams params = new RequestParams(this);
    }

    @OnClick(R.id.layout_error)
    public void ssh() {
        RequestParams params = new RequestParams(this);
        params.put("id", "1361653183");
        HttpRequest.get(Api.BASE_API_URL, params, new MyBaseHttpRequestCallback<TicketDetailResponse>() {
            @Override
            public void onLoginSuccess(TicketDetailResponse ticketDetailResponse) {
                showWaitDialog();
            }

            @Override
            public void onLoginFailure(TicketDetailResponse ticketDetailResponse) {
                Log.e("this", "errMsg = " + ticketDetailResponse.getErrMsg());
            }
        });
        Toast.makeText(this, TDevice.getNetworkType() + "", Toast.LENGTH_SHORT).show();
    }
}
