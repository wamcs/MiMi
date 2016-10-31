package com.unique.app.community.postevent.ui;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.postevent.Presenter.SecondPostFragmentPresenter;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */
public class SecondPostFragment extends BaseFragment<SecondPostFragmentPresenter> implements IView{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second_post;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected SecondPostFragmentPresenter getPresenter() {
        return new SecondPostFragmentPresenter(this);
    }

}
