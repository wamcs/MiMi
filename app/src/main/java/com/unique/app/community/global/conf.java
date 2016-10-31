package com.unique.app.community.global;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */

import com.unique.app.community.utils.TimeUtils;

/**
 * this class store constant value
 */
public final class Conf {

    /**
     * Image Conf
     */
    public static final int RESULT_CODE_CANCEL = 1;
    public static final int REQUEST_CODE_TAKE_PHOTO = 2;
    public static final int REQUEST_CODE_GALLERY = 3;
    public static final int REQUEST_CODE_CUT_PHOTO = 4;
    public static final int REQUEST_CODE_ALBUM = 5;
    public static final String TEMP_IMAGE_NAME = "temp_image.png";
    public static final int USER_AVATAR_SIZE = 320;

    /**
     * user information
     */

    public static final int CURRENT_YEAR = TimeUtils.getCurrentYear();
    public static final int MAX_YEAR = CURRENT_YEAR + 10;
    public static final int FEB_MONTH = 2;
    public static final int NORMAL_BIG_MONTH_MAX_DAY = 31;
    public static final int NORMAL_SMALL_MONTH_MAX_DAY = 30;
    public static final int FEB_BIG_MONTH_MAX_DAY = 29;
    public static final int FEB_SMALL_MONTH_MAX_DAY = 28;


}
