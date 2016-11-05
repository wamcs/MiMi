package com.unique.app.community.net;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.unique.app.community.entity.User;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/18/16.
 */
public class UserApi {

    private static UserApi sInstant;

    public static UserApi getInstant(){
        if (sInstant == null){
            synchronized (UserApi.class){
                if(sInstant == null){
                    sInstant = new UserApi();
                }
            }
        }
        return sInstant;
    }

    //用Response来封装返回值,接入流中


    public Observable<Response<Void>> verifyCodeInner(String code){
        return Observable.create(subscriber -> {
            User.verifyMobilePhoneInBackground(code, new AVMobilePhoneVerifyCallback() {
                @Override
                public void done(AVException e) {
                    Response<Void> response = new Response<Void>();
                    if (e != null){
                        response.setCode(e.getCode());
                        response.setMessage(e.getMessage());
                        subscriber.onNext(response);
                        subscriber.onError(e.getCause());
                    }else {
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }
            });
        });
    }

    public Observable<Response<Void>> loginInner(String username,String password){
        return Observable.create(subscriber -> {
            User.logInInBackground(username, password, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    User.changeCurrentUser(avUser,true);
                    Response<Void> response = new Response<Void>();
                    if (e != null){
                        response.setCode(e.getCode());
                        response.setMessage(e.getMessage());
                        subscriber.onNext(response);
                        subscriber.onError(e.getCause());
                    }else {
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }
            });
        });
    }

    public void logOutInner(){
        User.logOut();
    }

    public Observable<Response<Void>> requestResetCodeInner(String phone){
        return Observable.create(subscriber -> {
            User.requestPasswordResetBySmsCodeInBackground(phone, new RequestMobileCodeCallback() {
                @Override
                public void done(AVException e) {
                    Response<Void> response = new Response<Void>();
                    if (e != null){
                        response.setCode(e.getCode());
                        response.setMessage(e.getMessage());
                        subscriber.onNext(response);
                        subscriber.onError(e.getCause());
                    }else {
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }
            });
        });
    }

    public Observable<Response<Void>> requestCodeInner(String phone){
        return Observable.create(subscriber -> {
            User.requestMobilePhoneVerifyInBackground(phone, new RequestMobileCodeCallback() {
                @Override
                public void done(AVException e) {
                    Response<Void> response = new Response<Void>();
                    if (e != null){
                        response.setCode(e.getCode());
                        response.setMessage(e.getMessage());
                        subscriber.onNext(response);
                        subscriber.onError(e.getCause());
                    }else {
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }
            });
        });
    }


    public Observable<Response<Void>> registerInner(String username,String phone,String password){
        return Observable.create(subscriber -> {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            user.setMobilePhoneNumber(phone);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    Response<Void> response = new Response<Void>();
                    if (e != null){
                        response.setCode(e.getCode());
                        response.setMessage(e.getMessage());
                        subscriber.onNext(response);
                        subscriber.onError(e.getCause());
                    }else {
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }
            });
        });
    }

    public Observable<Response<Void>> resetPasswordInner(String code,String password){
        return Observable.create(subscriber -> {
            User.resetPasswordBySmsCodeInBackground(code, password, new UpdatePasswordCallback() {
                @Override
                public void done(AVException e) {
                    Response<Void> response = new Response<Void>();
                    if (e != null){
                        response.setCode(e.getCode());
                        response.setMessage(e.getMessage());
                        subscriber.onNext(response);
                        subscriber.onError(e.getCause());
                    }else {
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }
            });
        });
    }
}
