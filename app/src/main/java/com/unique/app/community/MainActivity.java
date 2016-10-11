package com.unique.app.community;

import android.os.Bundle;
import android.widget.ImageView;

import com.unique.app.community.base.Mvp.BaseActivity;
import com.unique.app.community.global.App;
import com.unique.app.community.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter>{


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
    }



    public void selectTab(int index) {
        if (mCurrentIndex == index){
            return;
        }
        //TODO:add image change
        mCurrentIndex =index;
        mPresenter.changeTab(index);

    }

    public void selectTab(boolean isCurrent,ImageView view, int resSelected, int resUnselected){
        if (isCurrent){
            view.setImageResource(resSelected);
        } else {
            view.setImageResource(resUnselected);
        }
    }

    @OnClick(R.id.page_1)
    void page_1(){
        selectTab(0);
    }

    @OnClick(R.id.page_2)
    void page_2(){
        selectTab(1);
    }
    @OnClick(R.id.page_3)
    void page_3(){
        selectTab(2);
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
