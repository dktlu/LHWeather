package com.ld.tao.lhweather.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ld.tao.lhweather.AppContext;
import com.ld.tao.lhweather.http.MyHttpCycleContext;
import com.ld.tao.lhweather.interf.BaseViewInterface;
import com.ld.tao.lhweather.interf.DialogControl;

import butterknife.ButterKnife;
import cn.finalteam.okhttpfinal.HttpTaskHandler;

/**
 * fragment基类
 * Created by tao on 2016/1/8.
 */
public class BaseFragment extends Fragment implements MyHttpCycleContext, BaseViewInterface {

    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    protected LayoutInflater mInflater;

    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public static int mState = STATE_NONE;

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    public AppContext getApplication() {
        return (AppContext) getActivity().getApplication();
    }

    public boolean onBackPressed() {
        return false;
    }

    protected int getLayoutId(){
        return 0;
    }

    protected View inflateView(int resId) {
        return this.mInflater.inflate(resId, null);
    }

    protected void hideWaitDialog() {
        Activity activity = getActivity();
        if (activity instanceof DialogControl) {
            ((DialogControl) activity).hideWaitDialog();
        }
    }

    protected ProgressDialog showWaitDialog() {
        return showWaitDialog("加载中…");
    }

    protected ProgressDialog showWaitDialog(String message) {
        Activity activity = getActivity();
        if (activity instanceof DialogControl) {
            return ((DialogControl) activity).showWaitDialog(message);
        }
        return null;
    }

    protected ProgressDialog showWaitDialog(int resId) {
        Activity activity = getActivity();
        if (activity instanceof DialogControl) {
            return ((DialogControl) activity).showWaitDialog(resId);
        }
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HttpTaskHandler.getInstance().removeTask(HTTP_TASK_KEY);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
