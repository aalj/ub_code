package net.lll0.bus.utils;

import android.content.Context;

import net.lll0.bus.utils.wight.ShapeLoadingDialog;

/**
 * Created by stone on 17-7-22.
 */

public class WaitLoading {

    private volatile static WaitLoading instance; //声明成 volatile
    private static boolean isTouchDismiss;
    private static Context context;
    private static ShapeLoadingDialog shapeLoadingDialog;

    private WaitLoading() {
    }

    public static WaitLoading getWaitLoading( Context pContext) {
        context = pContext;
        if (instance == null) {
            synchronized (WaitLoading.class) {
                if (instance == null) {
                    instance = new WaitLoading();
                    shapeLoadingDialog = new ShapeLoadingDialog(context);
                }
            }
        }
        return instance;
    }


    public void show(boolean isTouchDismis) {
        if (shapeLoadingDialog != null) {
        isTouchDismiss = isTouchDismis;
            shapeLoadingDialog.setCanceledOnTouchOutside(false);
            shapeLoadingDialog.show();

        }

    }

    public void dismiss() {
        if (shapeLoadingDialog != null) {

            shapeLoadingDialog.dismiss();
        }
    }


}
