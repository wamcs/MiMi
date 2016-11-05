package com.unique.app.community.maindisplay.ui.fragment;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.base.recyclerView.BaseListFragment;

import com.unique.app.community.maindisplay.presenter.DisplayEventsPresenter;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/20/16.
 */
public class DisplayEventsFragment extends BaseListFragment<DisplayEventsPresenter> implements IView{
    @Override
    public void onRefreshStateChanged(boolean isRefreshing) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_display_events;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected DisplayEventsPresenter getPresenter() {
        return new DisplayEventsPresenter(this,this);
    }


}
