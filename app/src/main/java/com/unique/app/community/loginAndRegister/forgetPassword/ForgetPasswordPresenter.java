package com.unique.app.community.loginAndRegister.forgetPassword;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.utils.ToastUtil;

import java.util.Locale;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/5.
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordActivity>
        implements IPresenter<ForgetPasswordActivity>{

    private volatile int count;
    private CountDownTimer timer;

    public ForgetPasswordPresenter(Activity activity) {
        super(activity);
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

    public void finish(){
        // TODO: 16/10/12
        // To finish
        ToastUtil.TextToast("Finish successfully!");
    }
}
