package com.unique.app.community.net;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.unique.app.community.entity.User;

import rx.Observable;

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


    public Observable<Response<Void>> VerifyInner(){
        return Observable.create(subscriber -> {

        });
    }

    public Observable<Response<Void>> loginInner(){
        return Observable.create(subscriber -> {

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
                        subscriber.onError(e.getCause());
                    }
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }
            });
        });
    }

    public Observable<Response<Void>> resetPasswordInner(){
        return Observable.create(subscriber -> {

        });
    }
}
