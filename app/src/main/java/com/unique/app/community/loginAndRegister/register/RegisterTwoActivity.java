package com.unique.app.community.loginAndRegister.register;

import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseActivity;
import com.unique.app.community.base.Mvp.IView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/8.
 */

public class RegisterTwoActivity extends BaseActivity<RegisterTwoPresenter>
        implements IView {

    @BindView(R.id.tool_bar_login_back_button)
    TextView textViewBackButton;
    @BindView(R.id.tool_bar_login_title)
    TextView textViewTitle;


    @BindView(R.id.edit_text_login_mobile_phone_number)
    EditText editTextMobilePhoneNumber;
    @BindView(R.id.edit_text_login_password)
    EditText editTextPassword;
    @BindView(R.id.button_login_login)
    Button buttonLogin;

    @Override
    protected RegisterTwoPresenter getPresenter() {
        return new RegisterTwoPresenter(mContext);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_and_register_two;
    }

    @Override
    protected void initEventAndData() {
        initializeTexts();
    }

    @Override
    protected boolean isToolbarEnable() {
        return true;
    }

    /**
     *  Initialize all listeners
     */

    @OnClick(R.id.button_login_login)
    void finishRegister(){
        mPresenter.register();
    }

    @OnClick(R.id.tool_bar_login_back_button)
    void getBack(){
        onBackPressed();
    }

    /**
     *  Initialize all hints and texts
     */

    private void initializeTexts(){
        editTextMobilePhoneNumber.setHint(getResources().getString(R.string.student_id));
        editTextMobilePhoneNumber.setInputType(InputType.TYPE_CLASS_TEXT);
        editTextPassword.setHint(getResources().getString(R.string.nickname));
        editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        buttonLogin.setText(getResources().getString(R.string.finish_register));
        buttonLogin.setEnabled(true);
        textViewTitle.setText(getResources().getString(R.string.register));
    }

}
