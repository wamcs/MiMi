package com.unique.app.community.loginAndRegister.register;

import android.os.Handler;
import android.os.Message;
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
import com.unique.app.community.global.conf;
import com.unique.app.community.loginAndRegister.utils.Listeners;
import com.unique.app.community.utils.ActivityStarter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/1.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter>
        implements IView{

    @BindView(R.id.tool_bar_login_back_button)
    TextView textViewBackButton;
    @BindView(R.id.tool_bar_login_title)
    TextView textViewTitle;

    @BindView(R.id.edit_text_login_mobile_phone_number)
    EditText editTextMobilePhoneNumber;
    @BindView(R.id.edit_text_login_password)
    EditText editTextPassword;
    @BindView(R.id.image_view_eye)
    ImageView eyeImage;
    @BindView(R.id.edit_text_login_verification_code)
    EditText editTextVerificationCode;
    @BindView(R.id.text_view_login_verification_code)
    TextView textViewVerificationCode;
    @BindView(R.id.button_login_login)
    Button buttonLogin;

    @Override
    protected RegisterPresenter getPresenter() {
        return new RegisterPresenter(mContext);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_password_and_register_one;
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
        changeVerificationText(conf.VERIFICATION_END_COUNT);
    }

    /**
     *  Initialize all listeners
     */

    @OnClick(R.id.button_login_login)
    void finishRegister(){
        mPresenter.nextStep();
    }

    @OnClick(R.id.text_view_login_verification_code)
    void getVerificationCode(){
        mPresenter.sendRequest();
    }

    @OnClick(R.id.tool_bar_login_back_button)
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
        editTextPassword.setHint(getResources().getString(R.string.password));
        buttonLogin.setText(R.string.next_step);
        textViewTitle.setText(getResources().getString(R.string.register));
    }

    /**
     * Counting verification code seconds
     */

    private Handler countHandler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case conf.VERIFICATION_START_COUNT:{
                    textViewVerificationCode.setText(String.format(Locale.CHINA, "%ds",msg.arg1));
                    textViewVerificationCode.setEnabled(false);
                    break;
                }
                case conf.VERIFICATION_STOP_COUNT:{
                    textViewVerificationCode.setText(getResources().getString(R.string.get_verification_code));
                    textViewVerificationCode.setEnabled(true);
                    break;
                }
            }
        }
    };

    public void changeVerificationText(Integer count){
        Message countMsg = new Message();
        if(count != conf.VERIFICATION_END_COUNT) {
            countMsg.what = conf.VERIFICATION_START_COUNT;
            countMsg.arg1 = count;
        }else{
            // Refresh at -1s
            countMsg.what = conf.VERIFICATION_STOP_COUNT;
            mPresenter.endCount();
        }
        countHandler.sendMessage(countMsg);
    }

}
