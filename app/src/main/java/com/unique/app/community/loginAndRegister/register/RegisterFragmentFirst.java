package com.unique.app.community.loginAndRegister.register;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.global.conf;
import com.unique.app.community.loginAndRegister.utils.Listeners;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/12.
 */

public class RegisterFragmentFirst extends BaseFragment<RegisterPresenter>
        implements IView {

    @BindView(R.id.reg_first_phone_edit_text)
    EditText editTextMobilePhoneNumber;
    @BindView(R.id.reg_first_password_edit_text)
    EditText editTextPassword;
    @BindView(R.id.eye_image_view)
    ImageView eyeImage;
    @BindView(R.id.reg_ver_edit_text)
    EditText editTextVerificationCode;
    @BindView(R.id.reg_ver_text_view)
    TextView textViewVerificationCode;
    @BindView(R.id.reg_first_button)
    Button buttonLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_first;
    }

    @Override
    protected void initEventAndData() {
        initializeTexts();
        initialListeners();
    }

    @Override
    protected RegisterPresenter getPresenter() {
        Log.d("getPresenter", "" + ((RegisterActivity)getActivity()));
        return ((RegisterActivity)getActivity()).getMPresenter();
    }

    /**
     *  Initialize all listeners
     */

    @OnClick(R.id.reg_first_button)
    void finishRegister(){
        mPresenter.nextStep();
    }

    @OnClick(R.id.reg_ver_text_view)
    void getVerificationCode(){
        mPresenter.sendRequest();
        mPresenter.startCount(textViewVerificationCode);
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
    }

    /**
     * Counting verification code seconds
     */

}
