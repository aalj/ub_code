package com.wdsunday.utils;

import java.util.List;

/**
 * Created by liangjun on 2017/3/8.
 */

public class ObjectUtils {


    public static boolean isBlank(Object o) {
        if (o != null) {
            if (o instanceof List) {
                if (((List) o).size() > 0) {
                    return false;
                } else {
                    return true;
                }
            }
            if (o instanceof String) {
                if (((String) o).length() > 0) {
                    return false;
                } else {
                    return true;
                }
            }


        } else {
            return false;
        }


        return false;
    }


}
