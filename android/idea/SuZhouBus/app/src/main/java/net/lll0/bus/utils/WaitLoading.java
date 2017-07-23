package net.lll0.bus.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import net.lll0.bus.utils.wight.ShapeLoadingDialog;

/**
 * Created by stone on 17-7-22.
 */

public class WaitLoading {

    private static ShapeLoadingDialog shapeLoadingDialog;

    private static final  String SIMPLE_NAME = WaitLoading.class.getSimpleName();

    public static  void show(boolean isTouchDismis, Activity activity) {
        shapeLoadingDialog = new ShapeLoadingDialog(activity);
        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.setCanceledOnTouchOutside(isTouchDismis);
            shapeLoadingDialog.show();

        }

    }

    public static void dismiss() {
        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }


    public static void  hide(){
        if (shapeLoadingDialog!=null) {
            try{
                shapeLoadingDialog.dismiss();

            }catch (Exception e ){
                e.printStackTrace();
                Log.e(SIMPLE_NAME, "hide  >  "+e.getMessage() );
            }
        }
    }


}
