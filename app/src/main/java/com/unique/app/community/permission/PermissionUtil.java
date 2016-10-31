package com.unique.app.community.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */
public class PermissionUtil {

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasPermission(Fragment fragment, String permission){
        int hasPermission = fragment.getActivity().checkSelfPermission(permission);
        return hasPermission == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasPermission(AppCompatActivity activity, String permission){
        int hasPermission = activity.checkSelfPermission(permission);
        return hasPermission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasCameraPermission(Fragment fragment){
        return hasPermission(fragment, Manifest.permission.CAMERA);
    }

    public static boolean hasCameraPermission(AppCompatActivity appCompatActivity){
        return hasPermission(appCompatActivity,Manifest.permission.CAMERA);
    }

    public static List<Method> getTargetPermissionMethods(Class clazz, int requestPermission){
        List<Method> targetMethods = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        for (Method m : methods){
            Annotation annotation = m.getAnnotation(TargetPermission.class);
            if (annotation == null){
                continue;
            }
            int permission = ((TargetPermission)annotation).value();
            if (permission == requestPermission){
                targetMethods.add(m);
            }
        }
        return targetMethods;
    }
}
