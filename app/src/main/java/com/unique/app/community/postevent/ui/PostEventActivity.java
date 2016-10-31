package com.unique.app.community.postevent.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */
public class PostEventActivity extends AppCompatActivity {

    @BindView(R.id.default_tool_bar_left_imageview)
    ImageView defaultToolBarLeftImageview;
    @BindView(R.id.tool_bar_title_text_view)
    TextView toolBarTitleTextView;
    @BindView(R.id.post_event_activity_bottom_button)
    Button postEventActivityBottomButton;

    List<BaseFragment> fragments = new ArrayList<>(2);
    private int mCurrentPage = -1;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        fragments.add(new FirstPostFragment());
        fragments.add(new SecondPostFragment());
        defaultToolBarLeftImageview.setImageResource(R.mipmap.img_back);
        toolBarTitleTextView.setText(R.string.post);
        change(0);

    }

    private void change(int index){
        if (index == mCurrentPage) {
            return;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFragment) {
            ft.hide(mCurrentFragment);
        }

        //先根据Tag从FragmentTransaction获取index对应的Fragment
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(
                fragments.get(index).getClass().getName());

        if (null == fragment) {
            //如fragment为空，则之前未添加此Fragment。从集合中取出加入
            fragment = fragments.get(index);
        }
        mCurrentFragment = fragment;
        mCurrentPage = index;

        //以name作为tag
        if(!fragment.isAdded()){
            ft.add(R.id.activity_post_event_container,fragment,fragment.getClass().getName());
        }else {
            ft.show(fragment);
        }
        ft.commit();

        switch (index){
            case 0:
                postEventActivityBottomButton.setText(R.string.next_step);
                break;
            case 1:
                postEventActivityBottomButton.setText(R.string.finish);
        }

    }

    @OnClick(R.id.default_tool_bar_right_imageview)
    void back(){
        switch (mCurrentPage){
            case 0:
                finish();
                break;
            case 1:
                change(0);
                break;
        }
    }

    @OnClick(R.id.post_event_activity_bottom_button)
    void button(){
        switch (mCurrentPage){
            case 0:
                change(0);
                break;
            case 1:
//TODO:call fragment.getPresenter() the use the post method
                break;
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }




}