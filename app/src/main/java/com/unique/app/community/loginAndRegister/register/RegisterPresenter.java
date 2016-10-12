package com.unique.app.community.loginAndRegister.register;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.global.conf;
import com.unique.app.community.utils.ToastUtil;

import java.util.Locale;

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
    private FragmentTransaction transaction;
    private RegisterFragmentFirst registerFragmentFirst;
    private RegisterFragmentSecond registerFragmentSecond;

    public RegisterPresenter(Activity activity){
        super(activity);
        initialFrags();
    }

    public void initialFrags(){
        manager = ((RegisterActivity)mActivity).getSupportFragmentManager();
        registerFragmentFirst = new RegisterFragmentFirst();
        registerFragmentSecond = new RegisterFragmentSecond();
        manager.beginTransaction()
                .add(R.id.reg_frag, registerFragmentFirst, conf.REG_FRAG_FIRST_TAG)
                .commit();
    }

    /**
     * Send request for verification code
     */

    public void sendRequest(){
        // TODO: 16/10/10
        // Do something to send request
        ToastUtil.TextToast("Send request successfully!");
    }

    /**
     * Count time for getting verification code
     */

    public void startCount(TextView countText){
        count = 60;
        timer = new CountDownTimer(61000, 1000) {
            @Override
            public void onTick(long l) {
                countText.setEnabled(false);
                countText.setText(String.format(Locale.CHINA, "%ds", count--));
            }

            @Override
            public void onFinish() {
                countText.setEnabled(true);
                countText.setText(mActivity.getResources().getString(R.string.get_verification_code));
            }
        }.start();
    }

    public void endCount(){
        if(timer != null) {
            timer.onFinish();
            timer.cancel();
        }
    }

    /**
     * Check verification
     */

    public void nextStep(){
        // TODO: 16/10/12
        // Do some check
        if(true) {
            manager.beginTransaction()
                    .add(R.id.reg_frag, registerFragmentSecond)
                    .addToBackStack(null)
                    .hide(registerFragmentFirst)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
            ToastUtil.TextToast("Check successfully!");
        }else{

        }
    }

    public void register(){
        // TODO: 16/10/12
        // Do something
        ToastUtil.TextToast("Register successfully!");
    }

}
