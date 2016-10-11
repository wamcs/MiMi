package com.unique.app.community.loginAndRegister.register;

import android.app.Activity;

import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.utils.ToastUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/8.
 */

public class RegisterTwoPresenter extends BasePresenter<RegisterTwoActivity>
        implements IPresenter<RegisterTwoActivity> {

    private Subscriber<Boolean> registerSubscriber;
    private Observable<Boolean> registerObservable;

    public RegisterTwoPresenter(Activity activity) {
        super(activity);
    }

    /**
     * Get result of register
     */

    public void register(){
        initialRegister();
        addSubscribe(
                registerObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(registerSubscriber)
        );
    }

    private void initialRegister(){

        registerObservable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                // TODO: 16/10/11
                // Do something to send request and get result of register
                // Fake method
                subscriber.onNext(true);
            }
        });

        registerSubscriber = new Subscriber<Boolean>() {
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
                    // TODO: 16/10/11
                    // Do something more
                    ToastUtil.TextToast("Register successfully");
                }else{
                    // TODO: 16/10/11
                    // Do something more
                    ToastUtil.TextToast("Register unsuccessfully");
                }
            }
        };

    }

}
