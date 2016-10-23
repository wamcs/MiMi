package com.unique.app.community.net;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.FindCallback;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.entity.EventTag;
import com.unique.app.community.entity.User;

import java.util.List;

import rx.Observable;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/21/16.
 */
public class OtherApi {
    private static OtherApi sInstant;

    public static OtherApi getInstant(){
        if (sInstant == null){
            synchronized (OtherApi.class){
                if(sInstant == null){
                    sInstant = new OtherApi();
                }
            }
        }
        return sInstant;


    }

    public Observable<Response<List<EventTag>>> getRelativeEventsTagInner(AVRelation<EventTag> avRelation){
        return Observable.create(subscriber -> {
            avRelation.getQuery().findInBackground(new FindCallback<EventTag>() {
                @Override
                public void done(List<EventTag> list, AVException e) {
                    Response<List<EventTag>> response = new Response<List<EventTag>>();
                    response.setData(list);
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

    public Observable<Response<List<EventComment>>> getRelativeEventsCommentsInner(AVRelation<EventComment> avRelation){
        return Observable.create(subscriber -> {
            avRelation.getQuery().findInBackground(new FindCallback<EventComment>() {
                @Override
                public void done(List<EventComment> list, AVException e) {
                    Response<List<EventComment>> response = new Response<>();
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

    public Observable<Response<List<User>>> getRelativeUsersInner(AVRelation<User> avRelation){
        return Observable.create(subscriber -> {
            avRelation.getQuery().findInBackground(new FindCallback<User>() {
                @Override
                public void done(List<User> list, AVException e) {
                    Response<List<User>> response = new Response<>();
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
