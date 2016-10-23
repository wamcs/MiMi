package com.unique.app.community.Issue;

import android.support.v4.app.Fragment;

import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/23.
 */

public class IssuePresenterSecond extends BasePresenter<IssueFragmentSecond>
        implements IPresenter<IssueFragmentSecond> {

    public IssuePresenterSecond(Fragment fragment) {
        super(fragment);
    }
}
