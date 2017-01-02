package com.tz.architect.mvp.simple5.login;

import android.content.Context;

import com.tz.architect.mvp.simple5.framework.presenter.impl.MvpBasePresenter;
import com.tz.architect.mvp.utils.HttpUtils.OnHttpResultListener;

/**
 * P层
 * 
 * @author Dream
 *
 */
public class MainPresenter extends MvpBasePresenter<MainView> {

	private MainModel mainModel;

	public MainPresenter(Context context) {
		super(context);
		this.mainModel = new MainModel();
	}

	public void login(String username, String pwd) {
		this.mainModel.login(username, pwd, new OnHttpResultListener() {

			@Override
			public void onResult(String result) {
				getView().onLoginResult(result);
			}
		});
	}

}
