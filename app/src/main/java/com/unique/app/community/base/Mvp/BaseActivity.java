package com.unique.app.community.base.Mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.unique.app.community.R;

import butterknife.ButterKnife;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/27/16.
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity {

    protected T mPresenter;
    protected Activity mContext;

    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = getPresenter();
        if (isToolbarEnable()){
            mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        }
        baseInit();
        initEventAndData();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    protected boolean isToolbarEnable() {
        return false;
    }

    protected void baseInit() {
        if (isToolbarEnable()) {
            initToolbar();
        }
    }

    private void initToolbar(){
        setSupportActionBar(mToolbar);
        mToolbar.setContentInsetsAbsolute(0, 0);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(null);
        }
    }

    protected abstract T getPresenter();
    protected abstract int getLayout();
    /**
     * remember call presenter.attach() in this method
     */
    protected abstract void initEventAndData();
}
