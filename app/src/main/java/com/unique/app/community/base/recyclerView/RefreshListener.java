package com.unique.app.community.base.recyclerView;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public interface RefreshListener {
    void onRefreshStateChanged(boolean isRefreshing);
    void onError(Throwable t);

}
