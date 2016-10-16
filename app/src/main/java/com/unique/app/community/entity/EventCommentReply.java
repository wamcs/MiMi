package com.unique.app.community.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */
@AVClassName("EventCommentReply")
public class EventCommentReply extends AVObject{

    public static final String COMMENT = "comment";
    public static final String CONTENT = "content";
    public static final String REPLYS = "replys";
    public static final String SENDER = "sender";
    public static final String RECEIVER = "receiver";

    public EventCommentReply(){}

    public EventCommentReply(Parcel in){
        super(in);
    }

    public static final Parcelable.Creator CREATOR = AVObject.AVObjectCreator.instance;
}
