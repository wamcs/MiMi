package com.unique.app.community.loginAndRegister.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.loginAndRegister.forgetPassword.ForgetPasswordActivity;
import com.unique.app.community.utils.ToastUtil;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/9/29.
 */

public class LoginPresenter extends BasePresenter<LoginActivity>
        implements IPresenter<LoginActivity> {

    public LoginPresenter(AppCompatActivity activity){
        super(activity);
    }

    public void login(String phone,String password){
        // To login
        ToastUtil.TextToast("Login successfully!");

        //TODO:if success , finish activity,and add info to Account

    }

    public void forget(){
        // TODO: 16/10/12
        mActivity.startActivity(new Intent(mActivity,ForgetPasswordActivity.class));
    }
}
