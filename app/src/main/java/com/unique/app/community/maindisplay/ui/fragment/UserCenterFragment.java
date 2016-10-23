package com.unique.app.community.maindisplay.ui.fragment;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.maindisplay.presenter.UserCenterPresenter;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/8/16.
 */
public class UserCenterFragment extends BaseFragment<UserCenterPresenter> implements IView{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected UserCenterPresenter getPresenter() {
        return new UserCenterPresenter(this);
    }
}
