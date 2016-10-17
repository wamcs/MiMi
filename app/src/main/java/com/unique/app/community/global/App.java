package com.unique.app.community.global;

import android.app.Activity;
import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.unique.app.community.entity.Event;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.entity.EventTag;
import com.unique.app.community.entity.User;
import com.unique.app.community.entity.UserTag;

import java.util.Stack;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public class App extends Application {

    private static App singleton;
    private static Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        AppData.init(this);
        AVObject.registerSubclass(UserTag.class);
        AVObject.registerSubclass(EventTag.class);
        AVObject.registerSubclass(Event.class);
        AVObject.registerSubclass(EventComment.class);
        AVUser.alwaysUseSubUserClass(User.class);
        AVOSCloud.initialize(this,"X7BySQECOns3ebTvqCO12pbs-gzGzoHsz","7VyX2CaVrfDE7JXUgrj4VbUP");
    }

    public static App getInstance(){
        return singleton;
    }
    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        finishAllActivity();
    }

}
