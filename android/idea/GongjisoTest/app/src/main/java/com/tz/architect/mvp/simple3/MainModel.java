package com.tz.architect.mvp.simple3;

import com.tz.architect.mvp.utils.HttpTask;
import com.tz.architect.mvp.utils.HttpUtils.OnHttpResultListener;

/**
 * M层(数据、网络)
 * @author Dream
 *
 */
public class MainModel {
	
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
