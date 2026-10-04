package org.example.bookmyshow.util;

import org.example.bookmyshow.entity.User;

public class StringUtil {

    private StringUtil() {}    //private constructor is used as utility class should not be instantiated e.g new StringUtil


    public static boolean isBlank(String value){

        if(value == null || value.isBlank()){
            return true;
        }
        return false;
    }
}
