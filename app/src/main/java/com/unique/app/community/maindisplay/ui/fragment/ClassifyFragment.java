package com.unique.app.community.maindisplay.ui.fragment;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.maindisplay.presenter.ClassifyPresenter;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/20/16.
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements IView{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected ClassifyPresenter getPresenter() {
        return new ClassifyPresenter(this);
    }
}
