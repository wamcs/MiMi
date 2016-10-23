package com.unique.app.community.details;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.base.Mvp.IPresenter;
import com.unique.app.community.utils.ToastUtil;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/19.
 */

public class DetailPresenter extends BasePresenter<DetailActivity>
        implements IPresenter<DetailActivity> {

    public DetailPresenter(AppCompatActivity activity) {
        super(activity);
    }

    public void iWannaJoin(){
        ToastUtil.TextToast("I wanna join!");
        // TODO: 16/10/23
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
}
