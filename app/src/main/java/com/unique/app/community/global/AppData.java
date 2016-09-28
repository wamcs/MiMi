package com.unique.app.community.global;

import android.content.Context;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public class AppData {

    private static Context sContext;

    public static Context getContext(){
        return sContext;
    }

    public static void init(Context context){
        sContext = context;
    }
}
