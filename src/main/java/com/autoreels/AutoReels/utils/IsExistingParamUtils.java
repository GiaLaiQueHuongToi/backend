package com.autoreels.AutoReels.utils;

public class IsExistingParamUtils {
    public static boolean isExistingParam (Object param) {
        return param != null && !param.equals("");
    }
}
