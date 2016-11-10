package com.unique.app.community;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.base.Mvp.BaseActivity;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.global.App;
import com.unique.app.community.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements IView {


    /**
     * Init value is a non-zero value, and then will be set to value.
     */
    public int mCurrentIndex = 2;

    @BindView(R.id.page_1)
    ImageView page1;
    @BindView(R.id.page_2)
    ImageView page2;
    @BindView(R.id.page_3)
    ImageView page3;

    @BindView(R.id.default_tool_bar_left_imageview)
    ImageView mToolBarLeftImageview;
    @BindView(R.id.tool_bar_title_text_view)
    TextView mToolBarTitleTextView;
    @BindView(R.id.default_tool_bar_right_imageview)
    ImageView mToolBarRightImageview;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        selectTab(0);
        mToolBarLeftImageview.setVisibility(View.INVISIBLE);
    }


    public void selectTab(int index) {
        if (mCurrentIndex == index) {
            return;
        }
        selectTab(index == 0,page1,R.mipmap.img_display_selected,R.mipmap.img_display_unselected);
        selectTab(index == 1,page2,R.mipmap.img_message_selected,R.mipmap.img_message_unselected);
        selectTab(index == 2,page3,R.mipmap.img_user_selected,R.mipmap.img_user_unselected);
        //TODO:add image change
        mCurrentIndex = index;
        switch (mCurrentIndex){
            case 0:
                mToolBarTitleTextView.setText(R.string.app_name);
                mToolBarRightImageview.setImageResource(R.mipmap.img_post);
                break;
            case 1:
                break;
            case 2:
                mToolBarTitleTextView.setText(R.string.user_center);
                mToolBarRightImageview.setImageResource(R.mipmap.img_edit);
                break;

        }
        mPresenter.changeTab(index);

    }

    @Override
    protected boolean isToolbarEnable() {
        return true;
    }

    private void selectTab(boolean isCurrent, ImageView view, int resSelected, int resUnselected) {
        if (isCurrent) {
            view.setImageResource(resSelected);
        } else {
            view.setImageResource(resUnselected);
        }
    }



    @OnClick(R.id.page_1)
    void page_1() {
        selectTab(0);
    }

    @OnClick(R.id.page_2)
    void page_2() {
        selectTab(1);
    }

    @OnClick(R.id.page_3)
    void page_3() {
        selectTab(2);
    }

    //根据不同的tab激活不同的方法
    @OnClick(R.id.default_tool_bar_right_imageview)
    void invoke(){
        switch (mCurrentIndex){
            case 0:
                //TODO:postEvent
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.TextToast(getString(R.string.exit));
            exitTime = System.currentTimeMillis();
        } else {
            App.getInstance().AppExit();
        }
    }
}
