package com.unique.app.community.loginAndRegister.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseActivity;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.loginAndRegister.utils.Listeners;
import com.unique.app.community.utils.PhoneChecker;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/9/29.
 */

public class LoginActivity extends BaseActivity<LoginPresenter>
        implements IView{

    @BindView(R.id.tool_bar_back_button)
    TextView textViewBackButton;
    @BindView(R.id.tool_bar_title_text_view)
    TextView textViewTitle;


    @BindView(R.id.login_phone_edit_text)
    EditText editTextMobilePhoneNumber;
    @BindView(R.id.login_password_edit_text)
    EditText editTextPassword;
    @BindView(R.id.login_button)
    Button buttonLogin;
    @BindView(R.id.forget_password_text_view)
    TextView textViewForgetPassword;
    @BindView(R.id.eye_image_view)
    ImageView eyeImage;

    private SpannableString spannableString;

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(mContext);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
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

    @OnClick(R.id.login_button)
    void login(){
        String phone = editTextMobilePhoneNumber.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (phone.length()!= 11 || !PhoneChecker.checkPhoneForm(phone)){
            spannableString = new SpannableString(this.getResources().getString(R.string.error_user_phone));
            spannableString.setSpan(new ForegroundColorSpan(Color.RED),
                    0, spannableString.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            editTextMobilePhoneNumber.setText(spannableString);
        }

        mPresenter.login(phone,password);
    }

    @OnClick(R.id.forget_password_text_view)
    void forgetPassword(){
       mPresenter.forget();
    }

    @OnClick(R.id.tool_bar_back_button)
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
        buttonLogin.setText(getResources().getString(R.string.login));
        textViewTitle.setText(getResources().getString(R.string.login));
    }


}
