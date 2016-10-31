package com.unique.app.community.loginAndRegister.register;

import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.utils.ToastUtil;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/1.
 */

public class RegisterPresenter extends BasePresenter<RegisterActivity>
        implements IPresenter<RegisterActivity> {

    private volatile int count;
    private CountDownTimer timer;

    private FragmentManager manager;
    private RegisterFragmentFirst registerFragmentFirst;
    private RegisterFragmentSecond registerFragmentSecond;

    private String phone;
    private String password;


    public RegisterPresenter(AppCompatActivity activity) {
        super(activity);
        initialFrags();
    }

    public void initialFrags() {
        manager = mActivity.getSupportFragmentManager();
        registerFragmentFirst = new RegisterFragmentFirst();
        registerFragmentSecond = new RegisterFragmentSecond();
        manager.beginTransaction()
                .add(R.id.reg_frag, registerFragmentFirst)
                .commit();
    }

    /**
     * Send request for verification code
     */

    public void sendRequest(String phone) {
        // TODO: 16/10/10
        // Do something to send request
        ToastUtil.TextToast("Send request successfully!");
    }



    /**
     * Check verification
     */

    public void nextStep(String phone,String password,String code) {
        this.phone = phone;
        this.password = password;

        // TODO: 16/10/12
        // Do some check
        manager.beginTransaction()
                .add(R.id.reg_frag, registerFragmentSecond)
                .hide(registerFragmentFirst)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        ToastUtil.TextToast("Check successfully!");

    }

    public void register(String sID,String nickname) {
        // TODO: 16/10/12
        // Do something
        ToastUtil.TextToast("Register successfully!");
    }

}
