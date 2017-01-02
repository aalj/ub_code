package com.tz.architect.mvp.simple2;

import com.tz.architect.mvp.utils.HttpUtils.OnHttpResultListener;

/**
 * P层
 * 
 * @author Dream
 *
 */
public class MainPresenter {

	private MainView mainView;
	private MainModel mainModel;

	public MainPresenter(MainView mainView) {
		this.mainView = mainView;
		this.mainModel = new MainModel();
	}

	public void login(String username, String pwd) {
		this.mainModel.login(username, pwd, new OnHttpResultListener() {

			@Override
			public void onResult(String result) {
				mainView.onLoginResult(result);
			}
		});
	}
}
