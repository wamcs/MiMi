package com.unique.app.community.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.unique.app.community.global.AppData;


/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public class Sharefer {

    private static SharedPreferences preferences;

    private static final String DEFAULT_SHAREFER_NAME = "setting";

    public static String getString(String key, String defValue) {
        checkDefault();
        return preferences.getString(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        checkDefault();
        return preferences.getBoolean(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        checkDefault();
        return preferences.getFloat(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        checkDefault();
        return preferences.getInt(key, defValue);
    }

    public static long getLong(String key,long defValue){
        checkDefault();
        return preferences.getLong(key,defValue);
    }

    public static void remove(String...keys){
        checkDefault();
        SharedPreferences.Editor editor = preferences.edit();
        for (String key : keys){
            editor.remove(key);
        }
        editor.apply();
    }

    public static void put(String key, Object value){
        checkDefault();
        SharedPreferences.Editor editor = preferences.edit();
        if (value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if (value instanceof Float){
            editor.putFloat(key, (Float) value);
        }else if (value instanceof String){
            editor.putString(key, (String) value);
        }else if (value instanceof Long){
            editor.putLong(key, (Long) value);
        }else if (value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else {
            throw new IllegalArgumentException("ShaPrefer illegal argument");
        }
        editor.apply();
    }


    private static void checkDefault(){
        if (preferences == null){
            preferences = AppData.getContext().getSharedPreferences(DEFAULT_SHAREFER_NAME, Context.MODE_PRIVATE);
        }
    }

}
