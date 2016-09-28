package com.unique.app.community.base.Mvp;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/27/16.
 */
public interface ILoadingView {

    public void showLoading();
    public void showContent();
    public void showNoData();
    public void showError(String error);

}
