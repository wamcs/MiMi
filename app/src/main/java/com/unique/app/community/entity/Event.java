package com.unique.app.community.entity;

import android.os.Parcel;
import android.util.Log;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.FindCallback;
import com.unique.app.community.net.HttpApi;
import com.unique.app.community.net.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/15/16.
 */

@AVClassName("Event")
public class Event extends AVObject {

    private List<User> participation;
    private List<EventTag> tags;
    private List<EventComment> comments;

    // 活动状态
    // 1-有效 0-无效
    private static final String STATE = "state";
    public static final int invalid = 0;
    public static final int valid = 1;

    // 活动所需人数
    private static final String EXCEPTED = "excepted";
    //地点
    private static final String PLACE = "place";
    // 内容
    private static final String CONTENT = "content";
    //评论
    private static final String COMMENTS = "comments";
    //时间
    private static final String TIME = "time";
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

    public ArrayList<String> getImage(){
        ArrayList<String> urls = new ArrayList<>();
        ArrayList arrayList = (ArrayList) get(IMAGE);
        if (arrayList == null || arrayList.size() == 0){
            Timber.d("no image error");
        }else {
            for (Object file : arrayList){
                urls.add(((AVFile)file).getUrl());
            }
        }
        return urls;
    }

    public void setImage(List<AVFile> images){
        addAll(IMAGE,images);
    }

    public List<EventComment> getComments(){

        if (comments == null) {
            AVRelation<EventComment> avRelation = getRelation(COMMENTS);
            HttpApi.getRelativeEventComment(avRelation)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Action1<Response<List<EventComment>>>() {
                        @Override
                        public void call(Response<List<EventComment>> listResponse) {
                            if (listResponse.getCode() == Response.SUCCESS){
                                comments = listResponse.getData();
                            }else {
                                Timber.d("getting relative comments failed.message is %s",listResponse.getMessage());
                            }
                        }
                    });
        }

        return comments;
    }

    public void setComments(EventComment comment){
        AVRelation<EventComment> avRelation = new AVRelation<>();
        if (comments == null){
            comments = getComments();
        }
        avRelation.addAll(comments);
        avRelation.add(comment);
        put(COMMENTS,avRelation);

    }

    public String getContent(){
        return getString(CONTENT);
    }

    public void setContent(String content){
        put(CONTENT,content);
    }

    public Date getTime(){
        return getDate(TIME);
    }

    public void setTime(Date date){
        put(TIME,date);
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

    public List<User> getParticipation(){

        if (participation == null) {
            AVRelation<User> avRelation = getRelation(PARTICIPATION);
            HttpApi.getRelativeUser(avRelation)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Action1<Response<List<User>>>() {
                        @Override
                        public void call(Response<List<User>> listResponse) {
                            if (listResponse.getCode() == Response.SUCCESS){
                                participation = listResponse.getData();
                            }else {
                                Timber.d("getting relative user failed.message is %s",listResponse.getMessage());
                            }
                        }
                    });
        }

        return participation;
    }

    public void setParticipation(User user){
        AVRelation<User> avRelation = new AVRelation<>();
        if (participation == null){
            participation = getParticipation();
        }
        avRelation.addAll(participation);
        avRelation.add(user);
        put(PARTICIPATION,avRelation);
    }

    public List<EventTag> getTags(){
        if (tags == null){
            AVRelation<EventTag> avRelation = getRelation(TAG);
            HttpApi.getRelativeEventTag(avRelation)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Response<List<EventTag>>>() {
                        @Override
                        public void call(Response<List<EventTag>> listResponse) {
                            if (listResponse.getCode() == Response.SUCCESS){
                                tags = listResponse.getData();
                            }else {
                                Timber.d("getting relative tags failed.message is %s",listResponse.getMessage());
                            }
                        }
                    });

        }
        return tags;
    }

    public void setTags(List<EventTag> tagList){
        AVRelation<EventTag> avRelation = new AVRelation<>();
        if (tags == null){
            tags = getTags();
        }
        avRelation.addAll(tags);
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
