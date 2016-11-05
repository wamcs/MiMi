package com.unique.app.community.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/13/16.
 */
public class PhoneChecker {

    public static boolean checkPhoneForm(String phoneNumber) {
        String expression =
                "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
