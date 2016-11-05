package com.unique.app.community.details;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.details.AskFragment.DetailAskFragment;
import com.unique.app.community.details.AskFragment.DetailAskPresenter;
import com.unique.app.community.details.CommentFragment.DetailCommentFragment;
import com.unique.app.community.details.CommentFragment.DetailCommentPresenter;
import com.unique.app.community.utils.ToastUtil;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/19.
 */

public class DetailPresenter extends BasePresenter<DetailActivity>
        implements IPresenter<DetailActivity> {

    private FragmentManager fragmentManager;
    private DetailAskFragment askFragment;
    private DetailCommentFragment commentFragment;

    private DetailAskPresenter askPresenter;
    private DetailCommentPresenter commentPresenter;

    public DetailPresenter(AppCompatActivity activity) {
        super(activity);
        initialFrags();
    }

    public void initialFrags(){
        fragmentManager = mActivity.getSupportFragmentManager();
        askFragment = new DetailAskFragment();
        commentFragment = new DetailCommentFragment();
        askPresenter = new DetailAskPresenter(askFragment, askFragment);
        commentPresenter = new DetailCommentPresenter(commentFragment, commentFragment);
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

    public void setMainTitle(String title){
        ((DetailActivity)mView).setMainTitle(title);
    }

    public void setMainText(String text){
        ((DetailActivity)mView).setMainText(text);
    }

    public void setStartTime(String time){
        ((DetailActivity)mView).setStartTime(time);
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

    public void setCost(Float cost){
        ((DetailActivity)mView).setCost(cost);
    }

    public void setNameOfStarter(String name){
        ((DetailActivity)mView).setNameOfStarter(name);
    }

    public void setRatioOfLike(int ratio){
        ((DetailActivity)mView).setRatioOfLike(ratio);
    }

    public void addPicToFlipper(Bitmap picture){
        ((DetailActivity)mView).addPicToFlipper(picture);
    }

    public void setStarterIcon(Bitmap icon){
        ((DetailActivity)mView).setStarterIcon(icon);
    }

    public void addPicToAppliedIcons(Bitmap icon){
        ((DetailActivity)mView).addPicToAppliedIcons(icon);
    }

    public void addPicToJoinedIcons(Bitmap icon){
        ((DetailActivity)mView).addPicToJoinedIcons(icon);
    }

    public DetailCommentPresenter getCommentPresenter(){
        return commentPresenter;
    }

    public DetailAskPresenter getAskPresenter(){
        return askPresenter;
    }

    public DetailAskFragment getAskFragment(){
        return askFragment;
    }

    public DetailCommentFragment getCommentFragment(){
        return commentFragment;
    }

    public FragmentManager getFragmentManager(){
        return fragmentManager;
    }
}
