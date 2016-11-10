package com.unique.app.community;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.unique.app.community.base.Mvp.BasePresenter;
import com.unique.app.community.maindisplay.ui.fragment.DisplayEventsFragment;
import com.unique.app.community.maindisplay.ui.fragment.MessageFragment;
import com.unique.app.community.maindisplay.ui.fragment.UserCenterFragment;

import java.util.ArrayList;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/8/16.
 */
public class MainPresenter extends BasePresenter<MainActivity> {


    private int mCurrentPage = -1;
    private Fragment mCurrentFragment;
    private ArrayList<Fragment> fragments = new ArrayList<>(3);

    public MainPresenter(AppCompatActivity activity) {
        super(activity);
        init();
    }

    public void changeTab(int index){
        if (index == mCurrentPage) {
            return;
        }

        FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();

        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFragment) {
            ft.hide(mCurrentFragment);
        }

        //先根据Tag从FragmentTransaction获取index对应的Fragment
        Fragment fragment = mActivity.getSupportFragmentManager().findFragmentByTag(
                fragments.get(index).getClass().getName());

        if (null == fragment) {
            //如fragment为空，则之前未添加此Fragment。从集合中取出加入
            fragment = fragments.get(index);
        }
        mCurrentFragment = fragment;
        mCurrentPage = index;

        //以name作为tag
        if(!fragment.isAdded()){
            ft.add(R.id.container_layout,fragment,fragment.getClass().getName());
        }else {
            ft.show(fragment);
        }
        ft.commit();




    }

    private void init(){
        fragments.add(new DisplayEventsFragment());
        fragments.add(new MessageFragment());
        fragments.add(new UserCenterFragment());
        changeTab(0);
    }
}
