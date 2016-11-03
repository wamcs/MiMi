package com.unique.app.community.details;

import android.support.v4.app.Fragment;

import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseListPresenter;
import com.unique.app.community.base.recyclerView.RefreshListener;

import java.util.List;

import rx.Observable;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/26.
 */

public class DetailCommentPresenter extends BaseListPresenter {

    public DetailCommentPresenter(Fragment fragment, RefreshListener listener) {
        super(fragment, listener);
    }

    @Override
    protected Observable<List> requestData(int page) {
        return null;
    }

    @Override
    protected BaseAdapter getAdapter() {
        return null;
    }
}
