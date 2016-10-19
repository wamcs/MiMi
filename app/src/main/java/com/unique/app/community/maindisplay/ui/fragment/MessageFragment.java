package com.unique.app.community.maindisplay.ui.fragment;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.maindisplay.presenter.MessagePresenter;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/8/16.
 */
public class MessageFragment extends BaseFragment<MessagePresenter> implements IView{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected MessagePresenter getPresenter() {
        return new MessagePresenter(this);
    }
}
