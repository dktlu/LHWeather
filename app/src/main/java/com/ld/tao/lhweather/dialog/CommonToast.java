package com.ld.tao.lhweather.dialog;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ld.tao.lhweather.R;

/**
 * Created by tao on 2016/1/6.
 */
public class CommonToast {
    private long duration = 3500L;
    private ToastView toastVw;

    public CommonToast(Activity activity) {
        init(activity);
    }

    public CommonToast(Activity activity, String message, int icon, long l) {
        duration = l;
        init(activity);
        setMessage(message);
        setMessageIc(icon);
    }

    private void setMessageIc(int icon) {

    }

    private void setMessage(String message) {

    }

    private void init(Activity activity) {
        toastVw = new ToastView(activity);
        setLayoutGravity(81);
    }

    private void setLayoutGravity(int i) {
        if (i != 0) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
            params.gravity = i;
        }
    }

    private class ToastView extends FrameLayout {

        public ImageView messageIc;
        public TextView messageTv;

        public ToastView(Context context) {
            this(context, null);
        }

        public ToastView(Context context, AttributeSet attrs) {
            super(context, attrs, 0);
        }

        public ToastView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            LayoutInflater.from(getContext()).inflate(
                    R.layout.view_toast, this, true);
            messageTv = (TextView) findViewById(R.id.title_tv);
            messageIc = (ImageView) findViewById(R.id.icon_iv);
        }
    }
}
