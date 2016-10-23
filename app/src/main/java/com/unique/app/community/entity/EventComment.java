package com.unique.app.community.entity;

import android.os.Parcel;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */
@AVClassName("EventComment")
public class EventComment extends AVObject{

    private static final String CONTENT = "content";
    private static final String RECEIVER = "receiver";
    private static final String EVENT = "event";
    private static final String SENDER = "sender";

    public Event getEvent(){
        return getAVObject(EVENT);
    }

    public void setEvent(Event event){
        put(EVENT,event);
    }

    public User getSender(){
        return getAVUser(SENDER);
    }

    public void setSender(User sender){
        put(SENDER,sender);
    }

    public User getRecevier(){
        return getAVUser(RECEIVER);
    }

    public void setReceiver(User receiver){
        put(RECEIVER,receiver);
    }

    public String getContent(){
        return getString(CONTENT);
    }

    public void setContent(String content){
        put(CONTENT,content);
    }


    public EventComment(){}

    public EventComment(Parcel in){
        super(in);
    }

    public static final Creator CREATOR = AVObjectCreator.instance;
}
