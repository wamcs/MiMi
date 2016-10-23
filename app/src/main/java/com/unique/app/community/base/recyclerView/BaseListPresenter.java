package com.unique.app.community.base.recyclerView;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.net.Response;
import com.unique.app.community.utils.NetworkUtil;
import com.unique.app.community.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public abstract class BaseListPresenter<T extends IView,D> extends BasePresenter<T> implements IRefresh {

    private int mListPage  = INIT_PAGE;
    private static final int INIT_PAGE = 1;
    private boolean isRefreshing = false;

    private RefreshListener listener;

    public BaseListPresenter(AppCompatActivity activity, RefreshListener listener) {
        super(activity);
        this.listener = listener;
    }

    public BaseListPresenter(Fragment fragment, RefreshListener listener) {
        super(fragment);
        this.listener = listener;
    }

    /**
     * use this method to get list data,the default page is 1 and I use rxjava and lambda,
     * if not understand the mean of these code,please click this
     * @Url http://blog.csdn.net/lzyzsd/article/details/41833541
     */

    @Override
    public void refreshTop() {
        if (isRefreshing){
            return;
        }
        mListPage = INIT_PAGE;
        changeRefreshState(true);
        Subscription subscription = requestData(mListPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    getAdapter().setData(data);
                    changeRefreshState(false);
                },throwable -> {
                    onError(throwable);
                    changeRefreshState(false);
                });
        addSubscribe(subscription);

    }

    /**
     * ditto,but this method gets the next page
     */

    @Override
    public void refreshBottom() {
        if (isRefreshing){
            return;
        }
        mListPage++;
        changeRefreshState(true);
        Subscription subscription = requestData(mListPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    getAdapter().addData(data);
                    changeRefreshState(false);
                },throwable -> {
                    onError(throwable);
                    changeRefreshState(false);
                });
        addSubscribe(subscription);


    }

    @Override
    public boolean isRefreshing() {
        return isRefreshing;
    }

    protected void changeRefreshState(boolean refreshing) {
        this.isRefreshing = refreshing;
        onRefreshStateChanged(refreshing);
    }

    /**
     * View will implement RefreshListener and it will call this call-back method to perfect logic
     * @param isRefreshing
     */
    protected void onRefreshStateChanged(boolean isRefreshing) {
        if (listener != null) {
            listener.onRefreshStateChanged(isRefreshing);
        }
    }

    protected void onError(Throwable t) {
        if (!NetworkUtil.checkIsNetworkConnected()) {
            ToastUtil.TextToast(R.string.no_network);
            return;
        }
        Timber.e(t, "Loading error...");
        listener.onError(t);
    }



    protected abstract Observable<List<D>> requestData(int page);
    protected abstract BaseAdapter<D> getAdapter();
}
