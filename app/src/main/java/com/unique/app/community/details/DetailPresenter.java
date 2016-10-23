package com.unique.app.community.details;

import android.support.v7.app.AppCompatActivity;

import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/19.
 */

public class DetailPresenter extends BasePresenter<DetailActivity>
        implements IPresenter<DetailActivity> {

    public DetailPresenter(AppCompatActivity activity) {
        super(activity);
    }
}
