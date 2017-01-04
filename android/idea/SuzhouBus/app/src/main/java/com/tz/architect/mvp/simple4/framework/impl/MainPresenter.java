package com.tz.architect.mvp.simple4.framework.impl;

import com.tz.architect.mvp.simple4.framework.AbsMvpPresenter;
import com.tz.architect.mvp.utils.HttpUtils.OnHttpResultListener;

/**
 * P层
 * 
 * @author Dream
 *
 */
public class MainPresenter extends AbsMvpPresenter<MainView> {

	private MainModel mainModel;

	public MainPresenter() {
		this.mainModel = new MainModel();
	}

	public void login(String username, String pwd) {
		this.mainModel.login(username, pwd, new OnHttpResultListener() {

			@Override
			public void onResult(String result) {
				if (getView() != null) {
					getView().onLoginResult(result);
				}
			}
		});
	}

}
