package com.ld.tao.lhweather.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ld.tao.lhweather.AppContext;
import com.ld.tao.lhweather.http.MyHttpCycleContext;
import com.ld.tao.lhweather.interf.DialogControl;

import butterknife.ButterKnife;
import cn.finalteam.okhttpfinal.HttpTaskHandler;

/**
 * fragment基类
 * Created by tao on 2016/1/8.
 */
public class BaseFragment extends Fragment implements MyHttpCycleContext {

    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    protected LayoutInflater mInflater;

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
}
