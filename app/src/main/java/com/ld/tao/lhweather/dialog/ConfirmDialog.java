package com.ld.tao.lhweather.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ld.tao.lhweather.R;
import com.ld.tao.lhweather.base.BaseDialog;

import java.util.zip.Inflater;

/**
 * Created by tao on 2015/11/13.
 */
public class ConfirmDialog extends BaseDialog {

    private Context context;
    private View view;

    public ConfirmDialog(Context context) {
        super(context);
        this.context = context;
    }

    public ConfirmDialog(Context context, boolean alignBottom) {
        super(context, alignBottom);
        this.context = context;
    }

    @Override
    protected View contentView() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_confirm, null);
        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tvTakePhoto = (TextView) view.findViewById(R.id.tv_take_photo);
        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Toast.makeText(context, "拍照", Toast.LENGTH_LONG).show();
            }
        });
    }
}
