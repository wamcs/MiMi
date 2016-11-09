package com.unique.app.community.details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.avos.avoscloud.AVRelation;
import com.unique.app.community.entity.User;
import com.unique.app.community.utils.TimeUtils;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.details.CommentFragment.DetailCommentFragment;
import com.unique.app.community.details.CommentFragment.DetailCommentPresenter;
import com.unique.app.community.entity.Event;
import com.unique.app.community.utils.ToastUtil;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/19.
 */

public class DetailPresenter extends BasePresenter<DetailActivity>
        implements IPresenter<DetailActivity> {

    private Event event;

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
        initialAllText();
    }

    public void iWannaJoin(){
        ToastUtil.TextToast("I wanna join!");
        // TODO: 16/10/23
    }
    
    public void replyToWho(int who){
        String name = getCommentPresenter().getAdapter().getData().get(who).getSender().getNickname();
        ToastUtil.TextToast("I reply to " + who + " " + name);
        // TODO: 16/11/5  
    }

    private void initialAllText(){
        // FIXME: 16/11/9
        // fix the way of getting image
        ((DetailActivity)mView).addPic(event.getImage());
        ((DetailActivity)mView).setMainTitle(event.getSubject());
        ((DetailActivity)mView).setMainText(event.getContent());
        // Get the number of application
        ((DetailActivity)mView).setHasJoinNum(event.getCount());
        ((DetailActivity)mView).setStartTime(TimeUtils.parseDate(event.getTime()));
        // Get the util-time
        ((DetailActivity)mView).setActivityPlace(event.getPlace());
        ((DetailActivity)mView).setRequirement(event.getExcepted());
        ((DetailActivity)mView).setCost(event.getType());
        ((DetailActivity)mView).setStarterIcon(BitmapFactory.decodeFile(event.getSponsor().getAvatat().getUrl()));
        ((DetailActivity)mView).setNameOfStarter(event.getSponsor().getNickname());
        // Get the ratio of like
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

    public void setRatioOfLike(int ratio){
        ((DetailActivity)mView).setRatioOfLike(ratio);
    }

    public void addPicToFlipper(String picture){
        ((DetailActivity)mView).addPic(picture);
    }

    public void setStarterIcon(Bitmap icon){
        ((DetailActivity)mView).setStarterIcon(icon);
    }

    private void setAppliedIcons(){

    }

    private void setJoinedIcons(){
        AVRelation<User> joins = event.getParticipation();

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

}
