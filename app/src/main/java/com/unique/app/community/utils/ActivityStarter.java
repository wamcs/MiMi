package com.unique.app.community.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unique.app.community.base.Mvp.BaseActivity;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/1.
 */

public class ActivityStarter {

    public static void start(Context context, Class< ? extends BaseActivity> aim, @Nullable Bundle bundle){
        Intent starter = new Intent(context, aim);
        if(bundle == null)
            context.startActivity(starter);
        else
            context.startActivity(starter, bundle);
    }

}
