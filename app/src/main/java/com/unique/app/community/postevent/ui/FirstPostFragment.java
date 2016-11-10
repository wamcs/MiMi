package com.unique.app.community.postevent.ui;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.postevent.Presenter.FirstPostFragmentPresenter;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */
public class FirstPostFragment extends BaseFragment<FirstPostFragmentPresenter> implements IView{


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first_post;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected FirstPostFragmentPresenter getPresenter() {
        return new FirstPostFragmentPresenter(this);
    }
}
