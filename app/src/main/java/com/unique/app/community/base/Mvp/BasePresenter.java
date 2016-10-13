package com.unique.app.community.base.Mvp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/27/16.
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T>{

    protected AppCompatActivity mActivity;
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter(AppCompatActivity activity) {
        this.mActivity = activity;
    }


    @Override
    public void attachView(T view) {
        mView = view;
    }

    protected void unSubscribe(){
        if(mCompositeSubscription!=null){
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void detachView() {
        this.mView = null;
        mActivity = null;
        unSubscribe();
    }
}
