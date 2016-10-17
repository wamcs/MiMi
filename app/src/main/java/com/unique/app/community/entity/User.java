package com.unique.app.community.entity;

import android.os.Parcel;

import com.alibaba.fastjson.annotation.JSONType;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVUser;

import java.util.List;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */

@JSONType(ignores = {"tags"})
public class User extends AVUser {
    //username是学号,父类实现。密码,电话等也是父类实现。

    //用户标签
    private static final String TAGS = "tags";
    // 真实姓名
    private static final String REALNAME = "realname";
    // 学校
    private static final String SCHOOL = "school";
    //昵称
    private static final String NICKNAME = "nickname";
    // 头像文件
    private static final String AVATAR = "avatarFile";
    // 资料是否公开
    private static final String OPENINFO = "openInfo";
    //性别
    private static final String GENDER = "gender";
    //个人简介
    private static final String AUTOGRAPH = "autograph";
    //QQ
    private static final String QQ = "qq";
    //微信
    private static final String WECHAT = "weChat";



    public AVRelation<UserTag> getTags(){
        return getRelation(TAGS);
    }

    public void setTags(AVRelation<UserTag> tags){
        put(TAGS,tags);
    }

    public String getRealname(){
        return getString(REALNAME);
    }

    public void setRealname(String realname){
        put(REALNAME,realname);
    }

    public String getSchool(){
        return getString(SCHOOL);
    }

    public void setSchool(String school){
        put(SCHOOL,school);
    }

    public String getNickname(){
        return getString(NICKNAME);
    }

    public void setNickname(String nickname){
        put(NICKNAME,nickname);
    }

    public AVFile getAvatat(){
        return getAVFile(AVATAR);
    }

    public void setAvatar(AVFile avatar){
        put(AVATAR,avatar);
    }

    public int getOpenInfo(){
        return getInt(OPENINFO);
    }

    public void setOpeninfo(int openinfo) {
        put(OPENINFO,openinfo);
    }

    public int getGender(){
        return getInt(GENDER);
    }

    public void setGender(int gender) {
        put(GENDER,gender);
    }

    public String getAutograph() {
        return getString(AUTOGRAPH);
    }

    public void setAutograph(String autograph) {
        put(AUTOGRAPH,autograph);
    }

    public String getQQ() {
        return getString(QQ);
    }

    public void setQQ(String Qq) {
        put(QQ,Qq);
    }

    public String getWechat() {
        return getString(WECHAT);
    }

    public void setWechat(String wechat) {
        put(WECHAT,wechat);
    }

    public User(){}


    public User(Parcel in){
        super(in);
    }

    public static final Creator CREATOR = AVObjectCreator.instance;



}
