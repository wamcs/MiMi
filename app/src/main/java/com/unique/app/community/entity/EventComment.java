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

    public static final String CONTENT = "content";
    public static final String REPLYS = "replys";
    public static final String EVENT = "event";
    public static final String SENDER = "sender";


    public EventComment(){}

    public EventComment(Parcel in){
        super(in);
    }

    public static final Creator CREATOR = AVObjectCreator.instance;
}
