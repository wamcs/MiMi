package com.unique.app.community.entity;

import android.os.Parcel;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */

@AVClassName("EventTag")
public class EventTag extends AVObject {

    private static final String TITLE = "title";

    public EventTag(){
        super();
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        put(TITLE,title);
    }
    public EventTag(Parcel in){
        super(in);
    }

    public static final Creator CREATOR = AVObjectCreator.instance;
}
