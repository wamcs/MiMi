package com.unique.app.community.maindisplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unique.app.community.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/8/16.
 */
public class DisplayFragment extends Fragment {

    @BindView(R.id.display_tab_layout)
    TabLayout displayTabLayout;
    @BindView(R.id.display_viewpager)
    ViewPager displayViewpager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init(){

        FragmentPagerAdapter adapter = new PageAdapter(getChildFragmentManager());
        displayViewpager.setAdapter(adapter);
        displayTabLayout.setupWithViewPager(displayViewpager);
        displayTabLayout.setTabMode(TabLayout.MODE_FIXED);


    }

    class PageAdapter extends FragmentPagerAdapter{

        private List<Fragment> fragments;
        private int[] pageTitles = {R.string.display_show_events,R.string.display_classification};

        public PageAdapter(FragmentManager fm) {
            super(fm);
            Fragment displayEventsFragment = new DisplayEventsFragment();
            Fragment classifyFragment = new ClassifyFragment();
            fragments = new ArrayList<>();
            fragments.add(displayEventsFragment);
            fragments.add(classifyFragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(pageTitles[position]);
        }
    }
}
