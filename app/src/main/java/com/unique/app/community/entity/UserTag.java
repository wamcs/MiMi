package com.unique.app.community.entity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */


@AVClassName("UserTag")
public class UserTag extends AVObject {

    public static final String TITLE = "title";

    public UserTag(){
        super();
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        put(TITLE,title);
    }
    public UserTag(Parcel in){
        super(in);
    }

    public static final Creator CREATOR = AVObjectCreator.instance;
}
