package com.unique.app.community.loginAndRegister.forgetPassword;

import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.utils.PhoneChecker;
import com.unique.app.community.utils.ToastUtil;

import java.util.Locale;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/5.
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordActivity>
        implements IPresenter<ForgetPasswordActivity>{

    public ForgetPasswordPresenter(AppCompatActivity activity) {
        super(activity);
    }

    /**
     * Send request for verification code
     */

    public void sendRequest(String phoneNumber){

        // TODO: 16/10/10
        // Do something to send request
        ToastUtil.TextToast("Send request successfully!");
    }


    /** m
     * Check verification
     */

    public void finish(String phoneNumber,String password,String code){
        // TODO: 16/10/12
        // To finish
        ToastUtil.TextToast("Finish successfully!");
    }
}
