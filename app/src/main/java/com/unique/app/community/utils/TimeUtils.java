package com.unique.app.community.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/21/16.
 */
public class TimeUtils {

    public static final long ONE_SECOND_TIME = 1000L;
    public final static long ONE_MINUTE_TIME = 60 * ONE_SECOND_TIME;
    public final static long ONE_HOUR_TIME = ONE_MINUTE_TIME * 60;
    public final static long ONE_DAY_TIME = ONE_HOUR_TIME * 24;

    public static final SimpleDateFormat totalFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.CHINA);
    public static final DateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
    public static final DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    public static final DateFormat timeFormatter2 = new SimpleDateFormat("HH:mm", Locale.CHINA);
    public static final DateFormat timeFormatter3 = new SimpleDateFormat("mm:ss", Locale.CHINA);

    public static String parseTime(Date time) {
        return dateFormatter.format(time)+timeFormatter2.format(time);
    }
}
