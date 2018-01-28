package net.lll0.bus.mgr;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import net.lll0.bus.database.dao.DaoMaster;
import net.lll0.bus.database.dao.DaoSession;
import net.lll0.bus.utils.MyLog;
import net.lll0.bus.utils.umeng.UmengManger;

/**
 * @author liangjun on 2018/1/27.
 */

public class ApplicationManager {


    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;


    private static ApplicationManager applicationManager;

    private ApplicationManager() {

    }

    public static ApplicationManager getInstance() {
        if (applicationManager == null) {
            synchronized (ApplicationManager.class) {
                if (applicationManager == null) {
                    applicationManager = new ApplicationManager();
                    return applicationManager;
                }
            }
        }
        return applicationManager;
    }

    public void  init(Application application){
        mContext = application;
        //控制开启友盟统计的日志输出
//        MobclickAgent.setDebugMode(true);
        //开启友盟日志加密
        UmengManger.getInstance().setEnableEncrypt(true);
        MyLog.init(true);
        TinkerInstaller.onReceiveUpgradePatch(application.getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");

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
