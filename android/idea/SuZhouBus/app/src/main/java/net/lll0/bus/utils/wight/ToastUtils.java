package net.lll0.bus.utils.wight;

import android.content.Context;
import android.widget.Toast;

import net.lll0.bus.utils.ObjectUtils;


/**
 * Created by liangjun on 2017/4/2.
 */

public class ToastUtils {
    /**
     * 短时间显示
     *
     * @param context
     * @param msg
     */
    public static void showShort(Context context, String msg) {
        if (ObjectUtils.isBlank(msg)) {
            msg = "";
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


    }

    /**
     * 长时间显示
     *
     * @param context
     * @param msg
     */
    public static void showLong(Context context, String msg) {
        if (ObjectUtils.isBlank(msg)) {
            msg = "";
        }
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();


    }


}
