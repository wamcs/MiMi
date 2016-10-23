package com.unique.app.community.Issue;

import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.Mvp.IView;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/23.
 */

public class IssueFragmentSecond extends BaseFragment<IssuePresenterSecond>
        implements IView{
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected IssuePresenterSecond getPresenter() {
        return null;
    }
}
