package net.lll0.bus.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by liang on 2017/8/25.
 */

public class APP extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    public static Context getmContext() {
        return mContext;
    }


}
