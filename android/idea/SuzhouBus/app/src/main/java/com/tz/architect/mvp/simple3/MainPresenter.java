package com.tz.architect.mvp.simple3;

import com.tz.architect.mvp.simple4.framework.AbsMvpPresenter;
import com.tz.architect.mvp.utils.HttpUtils.OnHttpResultListener;

/**
 * P层
 * 
 * @author Dream
 *
 */
public class MainPresenter{

	private MainView mainView;
	private MainModel mainModel;

	public MainPresenter() {
		this.mainModel = new MainModel();
	}

	public void login(String username, String pwd) {
		this.mainModel.login(username, pwd, new OnHttpResultListener() {

			@Override
			public void onResult(String result) {
				if(mainView != null){
					mainView.onLoginResult(result);
				}
			}
		});
	}

	/**
	 * 绑定视图层
	 * 
	 * @param mainView
	 */
	public void attachView(MainView mainView) {
		this.mainView = mainView;
	}

	/**
	 * 解除绑定
	 */
	public void dettachView() {
		this.mainView = null;
	}

}
