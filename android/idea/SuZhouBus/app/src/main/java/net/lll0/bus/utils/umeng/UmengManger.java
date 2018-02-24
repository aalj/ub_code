package net.lll0.bus.utils.umeng;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import net.lll0.bus.mgr.ApplicationManager;
import net.lll0.bus.ui.APP;

public class UmengManger {

	private Context context;
    public static UmengManger umengManger;

	private UmengManger() {
		context = ApplicationManager.getContext();
	}

	public static UmengManger getInstance() {
		if (umengManger == null) {
			umengManger = new UmengManger();
		}
		return umengManger;
	}

	/**
	 * 设置是否对日志信息进行加密, 默认false(不加密).
	 * @param enable
	 */
	public void setEnableEncrypt(boolean enable){
		MobclickAgent.enableEncrypt(enable);
	}

	/**
	 * 友盟自定义事件统计
	 * @param key
	 * @param action
	 */
	public void onEvent(String key, String action){
		MobclickAgent.onEvent(context, key, action);
	}
}