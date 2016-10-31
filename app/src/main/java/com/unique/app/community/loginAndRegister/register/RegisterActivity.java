package com.unique.app.community.loginAndRegister.register;


import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseActivity;
import com.unique.app.community.base.Mvp.IView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/1.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter>
        implements IView{

    @BindView(R.id.tool_bar_title_text_view)
    TextView titleText;

    @Override
    protected RegisterPresenter getPresenter() {
        return new RegisterPresenter(mContext);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        initialText();
    }

    @Override
    protected boolean isToolbarEnable() {
        return true;
    }

    public RegisterPresenter getMPresenter(){
        return mPresenter;
    }

    /**
     * Initial listeners
     */

    @OnClick(R.id.tool_bar_back_button)
    void getBack(){
        onBackPressed();
    }

    /**
     * Initial text
     */

    private void initialText(){
        titleText.setText(getResources().getString(R.string.register));
    }
}
