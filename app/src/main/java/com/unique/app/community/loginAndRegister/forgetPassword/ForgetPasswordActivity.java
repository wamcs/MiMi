package com.unique.app.community.loginAndRegister.forgetPassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseActivity;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.global.conf;
import com.unique.app.community.loginAndRegister.login.LoginActivity;
import com.unique.app.community.loginAndRegister.utils.Listeners;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/5.
 */

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter>
        implements IView {

    @BindView(R.id.tool_bar_back_button)
    TextView textViewBackButton;
    @BindView(R.id.tool_bar_title_text_view)
    TextView textViewTitle;

    @BindView(R.id.forget_phone_edit_text)
    EditText editTextMobilePhoneNumber;
    @BindView(R.id.forget_password_edit_text)
    EditText editTextPassword;
    @BindView(R.id.eye_image_view)
    ImageView eyeImage;
    @BindView(R.id.forget_ver_edit_text)
    EditText editTextVerificationCode;
    @BindView(R.id.forget_ver_text_view)
    TextView textViewVerificationCode;
    @BindView(R.id.forget_button)
    Button buttonLogin;

    @Override
    protected ForgetPasswordPresenter getPresenter() {
        return new ForgetPasswordPresenter(mContext);
    }

    public static void start(Context context, @Nullable Bundle bundle){
        Intent starter = new Intent(context, ForgetPasswordActivity.class);
        context.startActivity(starter, bundle);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initEventAndData() {
        initializeTexts();
        initialListeners();
        mPresenter.attachView(this);
    }

    @Override
    protected boolean isToolbarEnable() {
        return true;
    }

    @Override
    protected void onStop(){
        super.onStop();
        // Stop count
        mPresenter.endCount();
    }

    /**
     *  Initialize all listeners
     */
    @OnClick(R.id.forget_button)
    void finishRegister(){
        mPresenter.finish();
    }

    @OnClick(R.id.forget_ver_text_view)
    void getVerificationCode(){
        mPresenter.sendRequest();
        mPresenter.startCount(textViewVerificationCode);
    }

    @OnClick(R.id.tool_bar_back_button)
    void getBack(){
        onBackPressed();
    }

    private void initialListeners(){
        editTextVerificationCode.addTextChangedListener(Listeners.getPasswordWatcher(buttonLogin));
        eyeImage.setOnTouchListener(Listeners.getEyeListener(editTextPassword));
    }

    /**
     *  Initialize all hints and texts
     */

    private void initializeTexts(){
        editTextMobilePhoneNumber.setHint(getResources().getString(R.string.mobile_phone_number));
        editTextPassword.setHint(getResources().getString(R.string.new_password));
        buttonLogin.setText(R.string.finish);
        textViewTitle.setText(getResources().getString(R.string.find_back_password));
    }
}
