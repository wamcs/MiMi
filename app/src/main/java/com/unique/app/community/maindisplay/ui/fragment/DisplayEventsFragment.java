package com.unique.app.community.maindisplay.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.base.recyclerView.BaseListFragment;
import com.unique.app.community.base.recyclerView.RefreshListener;
import com.unique.app.community.maindisplay.presenter.DisplayEventsPresenter;
import com.unique.app.community.maindisplay.ui.adapter.DisplayEventsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/20/16.
 */
public class DisplayEventsFragment extends BaseListFragment<DisplayEventsPresenter> implements IView{


    @BindView(R.id.display_item_event_recycler_view)
    RecyclerView displayItemEventRecyclerView;
    @BindView(R.id.display_item_event_refresh_layout)
    SwipeRefreshLayout displayItemEventRefreshLayout;

    private DisplayEventsAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onRefreshStateChanged(boolean isRefreshing) {
        displayItemEventRefreshLayout.setRefreshing(isRefreshing);

    }

    @Override
    public void onError(Throwable t) {
        //可以根据错误类型在主界面加载图片,不过暂时没要求没要求
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_display_events;
    }

    @Override
    protected void initEventAndData() {
        displayItemEventRefreshLayout.setOnRefreshListener(() -> mPresenter.refreshTop());
        displayItemEventRecyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(getContext()));
        displayItemEventRecyclerView.setAdapter(adapter = new DisplayEventsAdapter(this));
        displayItemEventRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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

    }

    @Override
    protected DisplayEventsPresenter getPresenter() {
        return new DisplayEventsPresenter(this, this);
    }

    public DisplayEventsAdapter getAdapter(){
        return adapter;
    }

    public void loading(boolean enable) {
        if (!enable) {
            if (adapter.isFooterVisible()) {
                adapter.hideFooter();
            }
        }
    }


}
