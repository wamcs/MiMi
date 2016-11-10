package com.unique.app.community.loginAndRegister.forgetPassword;

import android.graphics.Color;
import android.os.CountDownTimer;
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
    Button textViewVerificationCode;
    @BindView(R.id.forget_button)
    Button buttonLogin;

    //be used to countdown
    private static final long COUNT = 60000L;

    private SpannableString spannableString;

    @Override
    protected ForgetPasswordPresenter getPresenter() {
        return new ForgetPasswordPresenter(mContext);
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


    /**
     *  Initialize all listeners
     */
    @OnClick(R.id.forget_button)
    void finishRegister(){
        String phone = editTextMobilePhoneNumber.getText().toString().trim();
        String password = editTextMobilePhoneNumber.getText().toString().trim();
        String code = editTextVerificationCode.getText().toString().trim();

        if (!checkPhone(phone)){
            return;
        }

        mPresenter.finish(phone,password,code);
    }

    @OnClick(R.id.forget_ver_text_view)
    void getVerificationCode(){
        //trim() remove blank in head or tail of string
        String phone = editTextMobilePhoneNumber.getText().toString().trim();
        if (!checkPhone(phone)){
            return;
        }
        mPresenter.sendRequest(phone);
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
        buttonLogin.setClickable(false);
        buttonLogin.setEnabled(false);
        //TODO: 要写drawable去实现可点击和不可点击的效果
        textViewTitle.setText(getResources().getString(R.string.find_back_password));
    }



    public void getCode(){
        textViewVerificationCode.setEnabled(false);
        textViewVerificationCode.setClickable(false);
        TimeCounter timer = new TimeCounter(COUNT,1000);
        timer.start();
        buttonLogin.setClickable(true);
        buttonLogin.setEnabled(true);
    }

    private boolean checkPhone(String phone){
        if (phone.length()!=11||!PhoneChecker.checkPhoneForm(phone)){
            spannableString = new SpannableString(this.getResources().getString(R.string.error_user_phone));
            spannableString.setSpan(new ForegroundColorSpan(Color.RED),
                    0, spannableString.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            editTextMobilePhoneNumber.setText(spannableString);
            return false;
        }
        return true;
    }

    //TODO: check password ?


    class TimeCounter extends CountDownTimer{

        public TimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            textViewVerificationCode.setText(String.format("%ds".toLowerCase(), l));
        }

        @Override
        public void onFinish() {
            textViewVerificationCode.setText(R.string.get_verification_code);
            textViewVerificationCode.setEnabled(true);
            textViewVerificationCode.setClickable(true);

        }
    }

}
