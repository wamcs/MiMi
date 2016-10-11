package com.unique.app.community.loginAndRegister.login;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseActivity;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.global.AppData;
import com.unique.app.community.loginAndRegister.forgetPassword.ForgetPasswordActivity;
import com.unique.app.community.loginAndRegister.forgetPassword.ForgetPasswordPresenter;
import com.unique.app.community.loginAndRegister.register.RegisterActivity;
import com.unique.app.community.loginAndRegister.register.RegisterTwoActivity;
import com.unique.app.community.loginAndRegister.utils.Listeners;
import com.unique.app.community.utils.ActivityStarter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/9/29.
 */

public class LoginActivity extends BaseActivity<LoginPresenter>
        implements IView{

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
    @BindView(R.id.text_view_forget_password)
    TextView textViewForgetPassword;
    @BindView(R.id.image_view_eye)
    ImageView eyeImage;

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(mContext);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_and_register_two;
    }

    @Override
    protected void initEventAndData() {
        initialListeners();
        initialTexts();
        mPresenter.attachView(this);
        // TODO: 16/10/1   
    }

    @Override
    protected boolean isToolbarEnable() {
        return true;
    }

    /**
     *  Initialize all listeners
     */

    @OnClick(R.id.button_login_login)
    void login(){
        mPresenter.login();
    }

    @OnClick(R.id.text_view_forget_password)
    void goToForgetPassword(){
        ActivityStarter.start(this, ForgetPasswordActivity.class, null);
    }

    @OnClick(R.id.tool_bar_login_back_button)
    void getBack(){
        onBackPressed();
    }

    private void initialListeners(){
        editTextPassword.addTextChangedListener(Listeners.getPasswordWatcher(buttonLogin));
        eyeImage.setOnTouchListener(Listeners.getEyeListener(editTextPassword));
    }

    /**
     *  Initialize all hints and texts
     */

    private void initialTexts(){
        editTextMobilePhoneNumber.setHint(getResources().getString(R.string.mobile_phone_number));
        editTextPassword.setHint(getResources().getString(R.string.password));
        eyeImage.setVisibility(View.VISIBLE);
        textViewForgetPassword.setVisibility(View.VISIBLE);
        buttonLogin.setText(getResources().getString(R.string.login));
        textViewTitle.setText(getResources().getString(R.string.login));
    }
}
