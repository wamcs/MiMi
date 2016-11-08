package com.unique.app.community.net;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.unique.app.community.entity.Event;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.entity.EventTag;
import com.unique.app.community.entity.User;

import java.util.List;

import rx.Observable;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/18/16.
 */
public class ActivityApi {

    private static ActivityApi sInstant;

    private static final String EVENT = "Event";
    private static final String EVENT_TAG = "EventTag";
    private static final String EVENT_COMMENT = "EventComment";

    //Event
    private static final String SPONSOR = "sponsor";

    //用于分页
    private static final int ITEM_NUMBER = 10;

    public static ActivityApi getInstant(){
        if (sInstant == null){
            synchronized (ActivityApi.class){
                if(sInstant == null){
                    sInstant = new ActivityApi();
                }
            }
        }
        return sInstant;
    }

    public Observable<Response<List<Event>>> getEventsInner(int page){
        return Observable.create(subscriber -> {
            AVQuery<Event> avQuery = new AVQuery<>(EVENT);
            avQuery.limit(ITEM_NUMBER);
            avQuery.skip((page-1)*ITEM_NUMBER);
            avQuery.orderByDescending("createdAt");
            avQuery.findInBackground(new FindCallback<Event>() {
                @Override
                public void done(List<Event> list, AVException e) {
                    Response<List<Event>> response = new Response<List<Event>>();
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

    public Observable<Response<List<Event>>> getPublishEventsInner(int page,User user){
        return Observable.create(subscriber -> {
            AVQuery<Event> avQuery = new AVQuery<>(EVENT);
            avQuery.limit(ITEM_NUMBER);
            avQuery.skip((page-1)*ITEM_NUMBER);
            avQuery.orderByDescending("createdAt");
            avQuery.whereEqualTo(SPONSOR,user);
            avQuery.findInBackground(new FindCallback<Event>() {
                @Override
                public void done(List<Event> list, AVException e) {
                    Response<List<Event>> response = new Response<List<Event>>();
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

    public Observable<Response<List<EventTag>>> getEventsTagInner(){
        return Observable.create(subscriber -> {
            AVQuery<EventTag> avQuery = new AVQuery<>(EVENT_TAG);
            avQuery.findInBackground(new FindCallback<EventTag>() {
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

    public Observable<Response<Void>> postEvent(Event event){
        return Observable.create(subscriber -> {
            event.saveInBackground(new SaveCallback() {
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

    public Observable<Response<Void>> postEventComment(EventComment eventComment){
        return Observable.create(subscriber -> {
            eventComment.saveInBackground(new SaveCallback() {
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
