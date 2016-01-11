package com.ld.tao.lhweather.interf;

import android.app.ProgressDialog;

/**
 * Created by tao on 2016/1/11.
 */
public interface DialogControl {

    public abstract void hideWaitDialog();

    public abstract ProgressDialog showWaitDialog();

    public abstract ProgressDialog showWaitDialog(int resid);

    public abstract ProgressDialog showWaitDialog(String message);

}
