package com.unique.app.community.global;

import android.app.Application;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AppData.init(this);
    }
}
