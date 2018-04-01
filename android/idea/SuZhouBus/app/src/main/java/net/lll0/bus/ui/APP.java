package net.lll0.bus.ui;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import net.lll0.bus.utils.MyLog;
import net.lll0.bus.database.dao.DaoMaster;
import net.lll0.bus.database.dao.DaoSession;
import net.lll0.bus.utils.umeng.UmengManger;
import net.youmi.android.AdManager;

/**
 * Created by liang on 2017/8/25.
 */

public class APP extends Application {
    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //控制开启友盟统计的日志输出
//        MobclickAgent.setDebugMode(true);
        //开启友盟日志加密
        UmengManger.getInstance().setEnableEncrypt(true);
        MyLog.init(false);


    }


    public static Context getContext() {
        return mContext;
    }


    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "blue_collar", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
