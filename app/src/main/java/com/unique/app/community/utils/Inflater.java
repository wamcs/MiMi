package com.unique.app.community.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unique.app.community.global.AppData;


/**
 * author:wamcs
 * date:2016/5/19
 * email:kaili@hustunique.com
 */
public class Inflater {
    public static View inflate(int resId, ViewGroup parent, boolean attach){
        return LayoutInflater.from(AppData.getContext()).inflate(resId,parent,attach);
    }
}
