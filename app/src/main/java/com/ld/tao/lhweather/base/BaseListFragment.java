package com.ld.tao.lhweather.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ld.tao.lhweather.R;
import com.ld.tao.lhweather.http.mdel.BaseApiResponse;
import com.ld.tao.lhweather.widget.EmptyLayout;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by tao on 2016/1/22.
 */
public abstract class BaseListFragment<T extends BaseApiResponse> extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;
    @Bind(R.id.error_layout)
    EmptyLayout mErrorLayout;

    protected int mCurrentPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    public void initData() {
        mSwiperefreshlayout.setOnRefreshListener(this);
        mSwiperefreshlayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentPage = 0;
                mState = STATE_REFRESH;
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                requestData(true);
            }
        });

    }

    private void requestData(boolean b) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pull_refresh_recyclerview;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
