package com.unique.app.community.userCenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.base.recyclerView.BaseListActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 11/8/16.
 */
public class PublishEventActivity extends BaseListActivity<PublishEventPresenter> implements IView {

    @BindView(R.id.default_tool_bar_left_imageview)
    ImageView defaultToolBarLeftImageview;
    @BindView(R.id.tool_bar_title_text_view)
    TextView toolBarTitleTextView;
    @BindView(R.id.default_tool_bar_right_imageview)
    ImageView defaultToolBarRightImageview;
    @BindView(R.id.publish_activity_recyclerview)
    RecyclerView launchActivityRecyclerview;
    @BindView(R.id.publish_activity_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private PublishEventAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onRefreshStateChanged(boolean isRefreshing) {
        refreshLayout.setRefreshing(isRefreshing);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    protected PublishEventPresenter getPresenter() {
        return new PublishEventPresenter(this,this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_event;
    }

    @Override
    protected void initEventAndData() {
        defaultToolBarLeftImageview.setImageResource(R.mipmap.img_back);
        toolBarTitleTextView.setText(R.string.launch_activity);

        refreshLayout.setOnRefreshListener(() -> mPresenter.refreshTop());
        launchActivityRecyclerview.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
        launchActivityRecyclerview.setAdapter(adapter = new PublishEventAdapter(this));
        mPresenter.setAdapter(adapter);
        launchActivityRecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    if (mPresenter != null ) {
                        mPresenter.refreshBottom();
                    }
                }
            }
        });

        mPresenter.attachView(this);
    }

    public void loading(boolean enable) {
        if (!enable) {
            if (adapter.isFooterVisible()) {
                adapter.hideFooter();
            }
        }
    }

    @OnClick(R.id.default_tool_bar_left_imageview)
    void back(){
        finish();
    }

}
