package net.lll0.bus.utils;

import android.app.Activity;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

/**
 * Created by liang on 2017/9/2.
 * 获取系统资源的工具类
 */

public class ResourcesUtils {

    public static String getString(Activity activity, @StringRes int rID) {
        if (activity != null) {
            return activity.getResources().getString(rID);
        }


        return "";
    }

    public static int getColor(Activity activity, @ColorRes int rID) {
        if (activity != null) {
            return activity.getResources().getColor(rID);
        }


        return 0xff333333;
    }

}
