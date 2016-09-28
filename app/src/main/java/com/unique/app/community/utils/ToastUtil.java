package com.unique.app.community.utils;

import android.content.Context;
import android.widget.Toast;

import com.unique.app.community.global.AppData;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public class ToastUtil {

    private static Toast toast = null;

    public static int LENGTH_LONG = Toast.LENGTH_LONG;
    public static int LENGTH_SHORT = Toast.LENGTH_SHORT;
    private static Context CONTEXT = AppData.getContext();

    public static void Exception(Exception e) {
        ToastUtil.TextToast(e.getClass() + ": " + e.getMessage());
    }

    /**
     * show toast with text
     *
     * @param text
     */
    public static void TextToast(CharSequence text) {
        toast = Toast.makeText(CONTEXT, text, LENGTH_SHORT);
        toast.show();
    }

    public static void TextToast(int resId) {
        toast = Toast.makeText(CONTEXT, resId, LENGTH_SHORT);
        toast.show();
    }

}
