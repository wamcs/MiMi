package com.unique.app.community.entity;

import android.os.Parcel;

import com.avos.avoscloud.AVUser;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */
public class User extends AVUser {

    public static final String TAGS = "tags";
    public static final String REALNAME = "realname";
    public static final String SCHOOL = "school";
    public static final String NICKNAME = "nickname";
    public static final String USERNAME = "username";
    public static final String PHONE = "mobilePhoneNumber";
    public static final String AVATER = "avater";
    public static final String PASSWORD = "password";
    public static final String OPENINFO = "openInfo";
    public static final String GENDER = "gender";



    public User(){}


    public User(Parcel in){
        super(in);
    }

    public static final Creator CREATOR = AVObjectCreator.instance;}
