package net.lll0.bus.utils;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by liang on 2017/9/23.
 */

public class MyLog {

    private static final String TAG = MyLog.class.getSimpleName();

    private static boolean p = false;

    public MyLog() {
        Log.e(TAG, "MyLog: 这里是构造方法");
    }

    /**
     * 日志输出等级。
     * <p>
     * <p>
     * 1、Log.v 的输出颜色为黑色的，输出大于或等于VERBOSE日志级别的信息
     * 　2、Log.d的输出颜色是蓝色的，输出大于或等于DEBUG日志级别的信息
     * 　3、Log.i的输出为绿色，输出大于或等于INFO日志级别的信息
     * 　4、Log.w的输出为橙色, 输出大于或等于WARN日志级别的信息
     * 　5、Log.e的输出为红色，仅输出ERROR日志级别的信息.
     */


    public static void v() {
        String tag = getStackTraceElements();


        if (p)

        {

        }

    }

    @NonNull
    private static String getStackTraceElements() {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        if (stack.length >= 3) {
            StackTraceElement s = stack[2];
            return s.getFileName();
        }
        return "Log";
    }

    public static void d(String m) {
        String s = getStackTraceElements();
        if (p) {
            Log.d(s, m);
        }
    }

    public static void i(String m) {
        String s = getStackTraceElements();
        if (p) {
            Log.i(s, m);
        }
    }

    public static void w(String m) {
        String s = getStackTraceElements();
        if (p) {
            Log.w(s, m);
        }
    }

    public static void e(String m) {
        String s = getStackTraceElements();
        if (p) {
            Log.e(s, m);
        }
    }

    public static void init(boolean isShow) {

        p = isShow;

    }

}
