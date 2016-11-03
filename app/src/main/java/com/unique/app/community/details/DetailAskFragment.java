package com.unique.app.community.details;

import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.base.recyclerView.BaseListFragment;
import com.unique.app.community.base.Mvp.IView;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/26.
 */

public class DetailAskFragment extends BaseListFragment implements IView {
    @Override
    public void onRefreshStateChanged(boolean isRefreshing) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected IPresenter getPresenter() {
        return ((DetailActivity)getActivity()).getMPresent();
    }
}
