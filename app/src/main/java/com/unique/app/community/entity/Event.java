package com.unique.app.community.entity;

import android.os.Parcel;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

import java.util.Date;
import java.util.List;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */

@AVClassName("Event")
public class Event extends AVObject {

    // 活动状态
    // 0-报名 1-开始 2-结束
    private static final String STATE = "state";
    public static final int signup = 0;
    public static final int begining = 1;
    public static final int end = 2;

    // 活动所需人数
    private static final String EXCEPTED = "excepted";
    //地点
    private static final String PLACE = "place";
    // 内容
    private static final String CONTENT = "content";
    //评论
    private static final String COMMENTS = "comments";
    //开始时间
    private static final String START_TIME = "start_time";
    //结束
    private static final String END_TIME = "end_time";
    // 种类 0-普通 1-付费
    private static final String TYPE = "type";
    public static final int NORMAL = 0;
    public static final int PAY = 1;
    //参与者
    private static final String PARTICIPATION = "participation";
    // 图片
    private static final String IMAGE = "image";
    // 标签
    private static final String TAG = "tag";
    // 主题
    private static final String SUBJECT = "subject";
    //发起人
    private static final String SPONSOR = "sponsor";
    //参与人数
    private static final String PARTICIPANTS_COUNT = "ParticipantsCount";
    //活动评分
    private static final String GRADE = "grade";
    //等待审核用户
    private static final String WAITING_USER = "waiting";

    public  String getGrade() {
        return getString(GRADE);
    }

    public void setGrade(String grade){
        put(GRADE,grade);
    }

    public AVRelation<User> getWaitingUser(){
        return getRelation(WAITING_USER);
    }

    public void setWaitingUser(User user){
        AVRelation<User> avRelation = getParticipation();
        avRelation.add(user);
        put(WAITING_USER,avRelation);
    }

    public void setWaitingUsers(List<User> users){
        AVRelation<User> avRelation = getParticipation();
        avRelation.addAll(users);
        put(WAITING_USER,avRelation);
    }

    public int getCount() {
        return getInt(PARTICIPANTS_COUNT);
    }

    public void addCount(){
        put(PARTICIPANTS_COUNT,getCount()+1);
    }

    public void reduceCount(){
        if (getCount() == 0){
            return;
        }
        put(PARTICIPANTS_COUNT,getCount()-1);
    }

    public String getImage(){
        AVFile img = getAVFile(IMAGE);
        return img.getUrl();
    }

    public void setImage(AVFile image){
        put(IMAGE,image);
    }

    public AVRelation<EventComment> getComments(){
        return getRelation(COMMENTS);
    }

    public void setComments(EventComment comment){
        AVRelation<EventComment> avRelation = getComments();
        avRelation.add(comment);
        put(COMMENTS,avRelation);

    }

    public String getContent(){
        return getString(CONTENT);
    }

    public void setContent(String content){
        put(CONTENT,content);
    }

    public Date getStartTime(){
        return getDate(START_TIME);
    }

    public void setStartTime(Date date){
        put(START_TIME,date);
    }

    public Date getEndTime(){
        return getDate(END_TIME);
    }

    public void setEndTime(Date date){
        put(END_TIME,date);
    }

    public String getPlace(){
        return getString(PLACE);
    }

    public void setPlace(String place){
        put(PLACE,place);
    }

    public int getExcepted(){
        return getInt(EXCEPTED);
    }

    public void setExcepted(int excepted){
        put(EXCEPTED,excepted);
    }

    public int getState(){
        return getInt(STATE);
    }

    public void setState(int state){
        put(STATE,state);
    }

    public int getType(){
        return getInt(TYPE);
    }

    public void setType(int type){
        put(TYPE,type);
    }

    public AVRelation<User> getParticipation(){
        return getRelation(PARTICIPATION);
    }

    public void setParticipation(User user){
        AVRelation<User> avRelation = getParticipation();
        avRelation.add(user);
        put(PARTICIPATION,avRelation);
    }

    public AVRelation<EventTag> getTags(){
        return getRelation(TAG);
    }

    public void setTags(List<EventTag> tagList){
        AVRelation<EventTag> avRelation = getTags();
        avRelation.addAll(tagList);
        put(TAG,avRelation);
    }

    public String getSubject(){
        return getString(SUBJECT);
    }

    public void setSubject(String subject){
        put(SUBJECT,subject);
    }

    public User getSponsor(){
        return getAVUser(SPONSOR);
    }

    public void setSponsor(User sponsor){
        put(SPONSOR,sponsor);
    }


    public Event(){}

    public Event(Parcel in){
        super(in);
    }

    public static final Creator CREATOR = AVObjectCreator.instance;
}
