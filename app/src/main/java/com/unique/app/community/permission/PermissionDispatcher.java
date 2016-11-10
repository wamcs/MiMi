package com.unique.app.community.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.unique.app.community.base.Mvp.BaseActivity;
import com.unique.app.community.base.Mvp.BaseFragment;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */
public class PermissionDispatcher {

    public static final int PERMISSION_REQUEST_CODE_CAMERA = 0;

    public static void invokeMethodWithCamera(BaseFragment fragment){
        nextStepWithCheck(fragment, PERMISSION_REQUEST_CODE_CAMERA);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void nextStepWithCheck(BaseFragment fragment, int requestPermission){
        if (Build.VERSION.SDK_INT < 23) {
            invokeTargetMethodWithRequestCode(fragment, requestPermission);
            return;
        }
        switch (requestPermission){
            case PERMISSION_REQUEST_CODE_CAMERA:
                if (PermissionUtil.hasPermission(fragment,Manifest.permission.CAMERA)){
                    invokeTargetMethodWithRequestCode(fragment, requestPermission);
                    return;
                }else {
                    if (fragment.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                    return;
                    }
                    fragment.getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA}, requestPermission);
                }
        }
    }

    private static void invokeTargetMethodWithRequestCode(BaseActivity activity, int requestCodeAskPermission) {
        List<Method> targetFragmentMethods = PermissionUtil.getTargetPermissionMethods(activity.getClass(), requestCodeAskPermission);
        for (Method m : targetFragmentMethods) {
            try {
                m.invoke(activity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void invokeMethodWithCamera(BaseActivity activity){
        nextStepWithCheck(activity, PERMISSION_REQUEST_CODE_CAMERA);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void nextStepWithCheck(BaseActivity activity, int requestPermission){
        if (Build.VERSION.SDK_INT < 23) {
            invokeTargetMethodWithRequestCode(activity, requestPermission);
            return;
        }
        switch (requestPermission){
            case PERMISSION_REQUEST_CODE_CAMERA:
                if (PermissionUtil.hasPermission(activity,Manifest.permission.CAMERA)){
                    invokeTargetMethodWithRequestCode(activity, requestPermission);
                    return;
                }else {
                    if (activity.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                        return;
                    }
                    activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, requestPermission);
                }
        }
    }

    private static void invokeTargetMethodWithRequestCode(BaseFragment fragment, int requestCodeAskPermission) {
        List<Method> targetFragmentMethods = PermissionUtil.getTargetPermissionMethods(fragment.getClass(), requestCodeAskPermission);
        for (Method m : targetFragmentMethods) {
            try {
                m.invoke(fragment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private static void showMessageOKCancel(AppCompatActivity activity, String message) {
        final AppCompatActivity appCompatActivity = activity;
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startPermissionSettingActivity(appCompatActivity);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private static void startPermissionSettingActivity(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", appCompatActivity.getPackageName(), null));
        appCompatActivity.startActivity(intent);
    }
}
