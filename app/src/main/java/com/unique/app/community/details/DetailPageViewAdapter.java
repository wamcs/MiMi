package com.unique.app.community.details;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.unique.app.community.R;
import com.unique.app.community.details.AskFragment.DetailAskFragment;
import com.unique.app.community.details.CommentFragment.DetailCommentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/4.
 */

public class DetailPageViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> frags;
    private ArrayList<String> titles;
    private Context mContext;

    public DetailPageViewAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        frags = fragmentList;
        titles = new ArrayList<>();
        titles.add("提问");
        titles.add("评论");
    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return frags.size();
    }

    @Override
    public String getPageTitle(int position){
        return titles.get(position);
    }
}
