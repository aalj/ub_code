package com.wdsunday.utils;

import java.util.List;

/**
 * Created by liangjun on 2017/3/8.
 */

public class ObjectUtils {

    /**
     * 判断传入的参数是否为空
     * @param o
     * @return
     */
    public static boolean isBlank(Object o) {
        if (o != null) {
            if (o instanceof List) {//判断是List集合
                if (((List) o).size() > 0) {
                    return false;
                } else {
                    return true;
                }
            }
            if (o instanceof String) {//判断是否是字符串
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
