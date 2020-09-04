package com.bksx.mobile.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

    public static String getStringNum(String s) {
        String regex = "[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll("").trim();
    }
}
