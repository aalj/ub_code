package net.lll0.bus.ui;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

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

    }


    public static Context getmContext() {
        return mContext;
    }


}
