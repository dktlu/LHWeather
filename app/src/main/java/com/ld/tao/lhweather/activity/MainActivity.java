package com.ld.tao.lhweather.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ld.tao.lhweather.R;
import com.ld.tao.lhweather.adapter.DataAdapter;
import com.ld.tao.lhweather.base.BaseActivity;
import com.ld.tao.lhweather.http.mdel.ItemModel;
import com.ld.tao.lhweather.recyclerview.EndlessRecyclerOnScrollListener;
import com.ld.tao.lhweather.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.ld.tao.lhweather.recyclerview.RecyclerViewStateUtils;
import com.ld.tao.lhweather.recyclerview.RecyclerViewUtils;
import com.ld.tao.lhweather.util.NetworkUtils;
import com.ld.tao.lhweather.widget.EmptyLayout;
import com.ld.tao.lhweather.widget.HeaderLayout;
import com.ld.tao.lhweather.widget.LoadingFooter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.layout_error)
    EmptyLayout layoutError;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private ArrayList<ItemModel> dataList;

    /**服务器端一共多少条数据*/
    private static final int TOTAL_COUNTER = 64;

    /**每一页展示多少条数据*/
    private static final int REQUEST_COUNT = 10;

    /**已经获取到多少条数据了*/
    private int mCurrentCounter = 0;

    private HeaderAndFooterRecyclerViewAdapter adapter = null;
    private DataAdapter mDataAdapter = null;

    private PreviewHandler mHandler = new PreviewHandler(this);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RequestParams params = new RequestParams(this);

        //initData
        dataList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ItemModel item = new ItemModel();
            item.id = i;
            item.title = "item " + i;
            dataList.add(item);
        }

        mCurrentCounter = dataList.size();
        mDataAdapter = new DataAdapter(MainActivity.this);
        mDataAdapter.addItems(dataList);
        adapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
        mRecyclerview.setAdapter(adapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewUtils.setHeaderView(mRecyclerview, new HeaderLayout(this));
        mRecyclerview.addOnScrollListener(mOnScrollListener);
    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerview);
            if (state == LoadingFooter.State.Loading) {
                return;
            }

            if (mCurrentCounter < TOTAL_COUNTER) {
                // loading more
                RecyclerViewStateUtils.setFooterViewState(MainActivity.this, mRecyclerview, REQUEST_COUNT, LoadingFooter.State.Loading, null);
                requestData();
            } else {
                //the end
                RecyclerViewStateUtils.setFooterViewState(MainActivity.this, mRecyclerview, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
            }
        }
    };

    /**
     * 模拟请求网络
     */
    private void requestData() {

        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟一下网络请求失败的情况
                if(NetworkUtils.isNetAvailable(MainActivity.this)) {
                    mHandler.sendEmptyMessage(-1);
                } else {
                    mHandler.sendEmptyMessage(-3);
                }
            }
        }.start();
    }

    private class PreviewHandler extends Handler {

        private WeakReference<MainActivity> ref;

        PreviewHandler(MainActivity activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MainActivity activity = ref.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            switch (msg.what) {
                case -1:
                    int currentSize = activity.mDataAdapter.getItemCount();

                    //模拟组装10个数据
                    ArrayList<ItemModel> newList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }

                        ItemModel item = new ItemModel();
                        item.id = currentSize + i;
                        item.title = "item" + (item.id);

                        newList.add(item);
                    }

                    mDataAdapter.addItems(newList);
                    mCurrentCounter += newList.size();
                    RecyclerViewStateUtils.setFooterViewState(activity.mRecyclerview, LoadingFooter.State.Normal);
                    break;
                case -2:
                    adapter.notifyDataSetChanged();
                    break;
                case -3:
                    RecyclerViewStateUtils.setFooterViewState(activity, activity.mRecyclerview, REQUEST_COUNT, LoadingFooter.State.NetWorkError, activity.mFooterClick);
                    break;
            }
        }
    }

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(MainActivity.this, mRecyclerview, REQUEST_COUNT, LoadingFooter.State.Loading, null);
            requestData();
        }
    };

//    @OnClick(R.id.layout_error)
//    public void ssh() {
//        RequestParams params = new RequestParams(this);
//        params.put("id", "1361653183");
//        HttpRequest.get(Api.BASE_API_URL, params, new MyBaseHttpRequestCallback<TicketDetailResponse>() {
//            @Override
//            public void onLoginSuccess(TicketDetailResponse ticketDetailResponse) {
//                showWaitDialog();
//            }
//
//            @Override
//            public void onLoginFailure(TicketDetailResponse ticketDetailResponse) {
//                Log.e("this", "errMsg = " + ticketDetailResponse.getErrMsg());
//            }
//        });
//        Toast.makeText(this, TDevice.getNetworkType() + "", Toast.LENGTH_SHORT).show();
//    }
}
