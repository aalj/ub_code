package net.lll0.bus.ui;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import net.lll0.bus.utils.umeng.UmengManger;
import net.youmi.android.AdManager;

/**
 * Created by liang on 2017/8/25.
 */

public class APP extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //控制开启友盟统计的日志输出
//        MobclickAgent.setDebugMode(true);
        //开启友盟日志加密
        UmengManger.getInstance().setEnableEncrypt(true);


    }


    public static Context getmContext() {
        return mContext;
    }


}
