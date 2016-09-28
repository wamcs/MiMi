package com.unique.app.community.base.Mvp;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/27/16.
 */
public interface IPresenter<V extends IView> {
    public void attachView(V view);
    public void detachView();
}
