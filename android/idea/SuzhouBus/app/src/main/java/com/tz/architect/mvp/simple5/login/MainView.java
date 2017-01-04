package com.tz.architect.mvp.simple5.login;

import com.tz.architect.mvp.simple5.framework.view.MvpView;

/**
 * View接口
 * @author Dream
 * 
 */
public interface MainView extends MvpView{
	public void onLoginResult(String result);
}
