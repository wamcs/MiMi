package com.unique.app.community.details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVRelation;
import com.unique.app.community.entity.User;
import com.unique.app.community.net.HttpApi;
import com.unique.app.community.net.Response;
import com.unique.app.community.utils.TimeUtils;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.details.CommentFragment.DetailCommentFragment;
import com.unique.app.community.details.CommentFragment.DetailCommentPresenter;
import com.unique.app.community.entity.Event;
import com.unique.app.community.utils.ToastUtil;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/19.
 */

public class DetailPresenter extends BasePresenter<DetailActivity>
        implements IPresenter<DetailActivity> {

    private Event event;
    private List<User> applied;
    private List<User> joined;

    private FragmentManager fragmentManager;
    private DetailCommentFragment commentFragment;
    private DetailCommentPresenter commentPresenter;

    public DetailPresenter(AppCompatActivity activity) {
        super(activity);
        initialFrag();
    }

    private void initialFrag(){
        commentFragment = new DetailCommentFragment();
        commentPresenter = new DetailCommentPresenter(commentFragment, commentFragment, event);
        fragmentManager = mActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.detail_fragment_layout, commentFragment)
                .commit();
    }

    public void getData(Event event){
        this.event = event;
        //initialAllText();
    }

    public void iWannaJoin(){
        ToastUtil.TextToast("I wanna join!");
        // TODO: 16/10/23
    }

    private void initialAllText(){
        initialAVRelation();
        ((DetailActivity)mView).addPic(event.getImage());
        ((DetailActivity)mView).setMainTitle(event.getSubject());
        ((DetailActivity)mView).setMainText(event.getContent());
        ((DetailActivity)mView).setStartTime(TimeUtils.parseDate(event.getStartTime()));
        ((DetailActivity)mView).setUtilTime(TimeUtils.parseDate(event.getEndTime()));
        ((DetailActivity)mView).setActivityPlace(event.getPlace());
        ((DetailActivity)mView).setRequirement(event.getExcepted());
        ((DetailActivity)mView).setCost(event.getType());
        ((DetailActivity)mView).setStarterIcon(event.getSponsor().getAvatat().getUrl());
        ((DetailActivity)mView).setNameOfStarter(event.getSponsor().getNickname());
        ((DetailActivity)mView).setRatioOfLike(event.getGrade());
        setAppliedIcons();
        setJoinedIcons();
    }

    public void setUtilTime(String time){
        ((DetailActivity)mView).setUtilTime(time);
    }

    public void setActivityPlace(String place){
        ((DetailActivity)mView).setActivityPlace(place);
    }

    public void setRequirement(int num){
        ((DetailActivity)mView).setRequirement(num);
    }

    public void setCost(int i){
        ((DetailActivity)mView).setCost(i);
    }

    public void setNameOfStarter(String name){
        ((DetailActivity)mView).setNameOfStarter(name);
    }

    public void setRatioOfLike(String ratio){
        ((DetailActivity)mView).setRatioOfLike(ratio);
    }

    public void addPicToFlipper(String picture){
        ((DetailActivity)mView).addPic(picture);
    }

    public void setStarterIcon(String icon){
        ((DetailActivity)mView).setStarterIcon(icon);
    }

    private void setAppliedIcons(){
        for(User user : applied){
            ((DetailActivity)mView).addPicToAppliedIcons(user.getAvatat().getUrl());
        }
    }

    private void setJoinedIcons(){
        for(User user : joined){
            ((DetailActivity)mView).addPicToJoinedIcons(user.getAvatat().getUrl());
        }
    }

    public void addPicToAppliedIcons(String icon){
        ((DetailActivity)mView).addPicToAppliedIcons(icon);
    }

    public void addPicToJoinedIcons(String icon){
        ((DetailActivity)mView).addPicToJoinedIcons(icon);
    }

    public DetailCommentPresenter getCommentPresenter(){
        return commentPresenter;
    }

    public DetailCommentFragment getCommentFragment(){
        return commentFragment;
    }

    public FragmentManager getFragmentManager(){
        return fragmentManager;
    }

    private void initialAVRelation(){
        HttpApi.getRelativeUser(event.getWaitingUser())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(listResponse -> {
                    if (listResponse.getCode() == Response.SUCCESS) {
                        applied = listResponse.getData();
                    }
                }, throwable -> {
                    Timber.d("get waiting user error:%s"+throwable.toString());
                });
        HttpApi.getRelativeUser(event.getParticipation())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(listResponse -> {
                    if (listResponse.getCode() == Response.SUCCESS) {
                        joined = listResponse.getData();
                    }
                }, throwable -> {
                    Timber.d("get participation error:%s"+throwable.toString());
                });
        ((DetailActivity)mView).setHasAppliedNum(applied.size());
        ((DetailActivity)mView).setHasJoinNum(joined.size());
    }

}
