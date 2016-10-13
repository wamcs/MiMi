package com.unique.app.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.unique.app.community.global.AppData;
import com.unique.app.community.loginAndRegister.login.LoginActivity;
import com.unique.app.community.loginAndRegister.register.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppData.init(this.getApplicationContext());
    }

    public void login(View view){
        LoginActivity.start(this, null);
    }

    public void register(View view){
        RegisterActivity.start(this, null);
    }
}
