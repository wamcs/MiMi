package com.unique.app.community.net;


import com.avos.avoscloud.AVRelation;
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
public class HttpApi {

    public static Observable<Response<Void>> verifyCode(String code){
        return UserApi.getInstant().verifyCodeInner(code);
    }

    public static Observable<Response<Void>> login(String username,String password ){
        return UserApi.getInstant().loginInner(username, password);
    }

    public static Observable<Response<Void>> requestResetCode(String phone){
        return UserApi.getInstant().requestResetCodeInner(phone);
    }

    public static Observable<Response<Void>> requestCode(String phone){
        return UserApi.getInstant().requestCodeInner(phone);
    }

    public static Observable<Response<Void>> register(String username,String phone,String password){
        return UserApi.getInstant().registerInner(username, phone, password);
    }

    public static Observable<Response<Void>> resetPassword(String code,String password ){
        return UserApi.getInstant().resetPasswordInner(code, password);
    }

    public static void loginOut(){
        UserApi.getInstant().logOutInner();
    }

    public static Observable<Response<List<Event>>> getEvents(int page){
        return ActivityApi.getInstant().getEventsInner(page);
    }

    public static Observable<Response<List<EventTag>>> getEventTags(){
        return ActivityApi.getInstant().getEventsTagInner();
    }

    public static Observable<Response<Void>> postEvent(Event event){
        return ActivityApi.getInstant().postEvent(event);
    }

    public static Observable<Response<List<EventTag>>> getRelativeEventTag(AVRelation<EventTag> avRelation){
        return OtherApi.getInstant().getRelativeEventsTagInner(avRelation);
    }

    public static Observable<Response<List<EventComment>>> getRelativeEventComment(AVRelation<EventComment> avRelation){
        return OtherApi.getInstant().getRelativeEventsCommentsInner(avRelation);
    }

    public static Observable<Response<List<User>>> getRelativeUser(AVRelation<User> avRelation){
        return OtherApi.getInstant().getRelativeUsersInner(avRelation);
    }
}
