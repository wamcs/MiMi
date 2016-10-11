package com.unique.app.community.loginAndRegister.login;

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
 * Since: 16/9/29.
 */

public class LoginPresenter extends BasePresenter<LoginActivity>
        implements IPresenter<LoginActivity> {

    private Subscriber<Boolean> loginSubscriber;
    private Observable<Boolean> loginObservable;

    public LoginPresenter(Activity activity){
        super(activity);
    }

    public void login(){
        initialLogin();
        addSubscribe(
                loginObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginSubscriber)
        );
    }

    private void initialLogin(){

        loginObservable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                // TODO: 16/10/9
                // Do something to check whether login has succeed and return true or false
                // Fake method
                subscriber.onNext(false);
            }
        });

        loginSubscriber = new Subscriber<Boolean>() {
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
                    // Login successfully
                    // TODO: 16/10/9
                    // Start next activities and do more things
                    ToastUtil.TextToast("Login successfully!");
                }else{
                    // Login unsuccessfully
                    // TODO: 16/10/9
                    //
                    ToastUtil.TextToast("Login unsuccessfully!");
                }
            }
        };
    }

}
