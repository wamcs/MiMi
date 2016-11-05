package com.unique.app.community.global;

import com.unique.app.community.utils.ShaPrefer;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/13/16.
 */

//全使用leancloud的sdk,这个类应该废了。不过别人的sdk封的太好也不好,什么都不用做了。
//但ios的后台已经写死了,没法改,不能用rest api来构了。如果以后迁移的话,自己做缓存这个类还是要用的
//估计会和有书一样吧==
public class Account {

    public static void setPhoneNumber(String number) {
        ShaPrefer.put("user_phone", number);
    }

    public static String getPhoneNumber() {
        return ShaPrefer.getString("user_phone", "");
    }

    public static long getId() {
        return ShaPrefer.getLong("user_id", 0L);
    }

    public static void setId(long s) {
        ShaPrefer.put("user_id", s);
    }

    public static String getInstallationId(){
        return ShaPrefer.getString("installationId","");
    }

    public static void setInstallationId(String installationId){
        ShaPrefer.put("installationId",installationId);
    }

    public static String getToken() {
        return ShaPrefer.getString("user_token", "");
    }

    public static void setToken(String s) {
        ShaPrefer.put("user_token", s);
    }

    public static String getNickName() {
        return ShaPrefer.getString("user_nickname", "");
    }

    public static void setNickName(String s) {
        ShaPrefer.put("user_nickname", s);
    }

    public static String getAvatar() {
        return ShaPrefer.getString("user_avatar_url", "");
    }

    public static void setAvatar(String s) {
        ShaPrefer.put("user_avatar_url", s);
    }

    public static boolean isLogin() {
        long key = getId();
        return (key != 0L);
    }

    public static void logOut() {
        setId(0L);
    }
}
