package com.unique.app.community.loginAndRegister.register;

import android.app.Activity;
import android.util.Log;

import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.utils.ActivityStarter;
import com.unique.app.community.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/1.
 */

public class RegisterPresenter extends BasePresenter<RegisterActivity>
        implements IPresenter<RegisterActivity> {

    private Subscriber<Boolean> verificationCheckSubscriber;
    private Observable<Boolean> verificationCheckObservable;
    private volatile int count;
    private Timer countTaskTimer;

    public RegisterPresenter(Activity activity){
        super(activity);
    }

    /**
     * Send request for verification code
     */

    public void sendRequest(){
        // TODO: 16/10/10
        // Do something to send request
        startCount();
    }

    /**
     * Count time for getting verification code
     */

    private void startCount(){
        count = 60;
        countTaskTimer = new Timer();
        countTaskTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mView.changeVerificationText(count--);
            }
        }, 0, 1000);
    }

    public void endCount(){
        if(countTaskTimer != null)
            countTaskTimer.cancel();
    }

    /**
     * Check verification
     */

    public void nextStep(){
        initialCheck();
        addSubscribe(
            verificationCheckObservable
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(verificationCheckSubscriber)
        );
    }

    private void initialCheck(){

        verificationCheckObservable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                // TODO: 16/10/9
                // Do something to get the result of checking verification
                // Fake method
                subscriber.onNext(true);
            }
        });

        verificationCheckSubscriber = new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                // I don't care
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if(aBoolean){
                    // Check successfully
                    // TODO: 16/10/9
                    // Do more things
                    ToastUtil.TextToast("Check successfully!");
                    ActivityStarter.start(mView, RegisterTwoActivity.class, null);
                }else{
                    // Check unsuccessfully
                    // TODO: 16/10/9
                    //
                    ToastUtil.TextToast("Check unsuccessfully!");
                }
            }
        };
    }

}
