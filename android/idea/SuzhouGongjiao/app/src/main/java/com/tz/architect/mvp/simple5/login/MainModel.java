package com.tz.architect.mvp.simple5.login;

import com.tz.architect.mvp.simple5.framework.model.impl.MvpBaseModel;
import com.tz.architect.mvp.utils.HttpTask;
import com.tz.architect.mvp.utils.HttpUtils.OnHttpResultListener;

/**
 * M层(数据、网络)
 * @author Dream
 *
 */
public class MainModel extends MvpBaseModel{
	
	public void login(String username,String pwd,final OnHttpResultListener onHttpResultListener){
		HttpTask httpTask = new HttpTask(new OnHttpResultListener() {

			@Override
			public void onResult(String result) {
				onHttpResultListener.onResult(result);
			}
		});
		httpTask.execute(username,pwd,"http://www.baidu.com");
	}
}
