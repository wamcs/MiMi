package com.unique.app.community.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import timber.log.Timber;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */
public class DirUtil {

    private static String SDCARD_ROOT_DIR;
    private static String DATA_ROOT_DIR;
    private static final String FILES = "/files";
    private static final String RES = "/res";

    /**
     * 判断SDCard是否正常挂载
     *
     * @return
     */
    private static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 初始化SDCard文件目录和私有文件目录
     *
     * @param context
     * @throws Exception
     */
    public static void init(Context context) throws Exception {
        File file = context.getExternalFilesDir(null);
        if (file != null) {
            SDCARD_ROOT_DIR = file.getPath();
        } else {
            throw new Exception("GET SDCARD DIR ERROR");
        }
        DATA_ROOT_DIR = context.getFilesDir().toString();
    }

    /**
     * 根据指定文件夹目录和类型创建文件夹
     *
     * @param rootDir
     * @param type
     * @return
     */
    public static File getDirectory(String rootDir, String type) {
        if (SDCARD_ROOT_DIR == null || DATA_ROOT_DIR == null) {
            Timber.e("you should invoke init() method before use DirUtils");
            return null;
        }
        File destDir = new File(rootDir + type);
        if (!destDir.exists()) {
            if (destDir.mkdirs()) {
                Timber.d("=======create dir======== %s", destDir.getAbsolutePath());
            } else {
                Timber.d("=======create dir========failed");
            }
        }
        return destDir;
    }

    public static File getResDirectory() {
        StringBuilder stringBuilder = new StringBuilder();
        if (hasSDCard())
            stringBuilder.append(SDCARD_ROOT_DIR).append(RES);
        else
            stringBuilder.append(DATA_ROOT_DIR).append(FILES).append(RES);
        File destDir = new File(stringBuilder.toString());
        if (!destDir.exists()) {
            if (destDir.mkdirs()) {
                Timber.d("=======create dir======== %s", destDir.getAbsolutePath());
            } else {
                Timber.d("=======create dir========failed");
            }
        }
        return destDir;
    }


}
