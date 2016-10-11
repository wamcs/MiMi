package com.unique.app.community.loginAndRegister.utils;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.unique.app.community.R;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/9.
 */

public class Listeners {

    public static View.OnTouchListener getEyeListener(EditText editTextPassword){
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(view.getId() == R.id.image_view_eye){
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:{
                            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:{
                            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            break;
                        }
                    }
                    return true;
                }else{
                    return false;
                }
            }
        };
    }

    public static TextWatcher getPasswordWatcher(Button buttonLogin){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                buttonLogin.setEnabled(editable.length() != 0);
            }
        };
    }

}
