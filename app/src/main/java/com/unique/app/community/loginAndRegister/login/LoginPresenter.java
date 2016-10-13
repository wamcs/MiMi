package com.unique.app.community.loginAndRegister.login;

import android.app.Activity;

import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.loginAndRegister.forgetPassword.ForgetPasswordActivity;
import com.unique.app.community.utils.ToastUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/9/29.
 */

public class LoginPresenter extends BasePresenter<LoginActivity>
        implements IPresenter<LoginActivity> {

    public LoginPresenter(Activity activity){
        super(activity);
    }

    public void login(){
        // TODO: 16/10/12
        // To login
        ToastUtil.TextToast("Login successfully!");
    }

    public void forget(){
        // TODO: 16/10/12
        ForgetPasswordActivity.start(mActivity, null);
    }
}
