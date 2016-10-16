package com.unique.app.community.entity;

import android.os.Parcel;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */

@AVClassName("Event")
public class Event extends AVObject {

    public static final String PLACE = "place";
    public static final String CONTENT = "content";
    public static final String COMMENTS = "comments";
    public static final String TIME = "time";
    public static final String TYPE = "type";
    public static final String PARTICIPATION = "participation";
    public static final String COMMENT = "comment";
    public static final String IMAGE = "image";
    public static final String TAG = "tag";
    public static final String SUBJECT = "subject";
    public static final String SPONSOR = "sponsor";
    public static final String PRIVATE_COMMENT = "private_comment";

    public Event(){}

    public Event(Parcel in){
        super(in);
    }

    public static final Creator CREATOR = AVObjectCreator.instance;
}
