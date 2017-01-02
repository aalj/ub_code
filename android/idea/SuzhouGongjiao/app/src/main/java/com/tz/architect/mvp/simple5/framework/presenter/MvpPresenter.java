package com.tz.architect.mvp.simple5.framework.presenter;

import com.tz.architect.mvp.simple5.framework.view.MvpView;

/**
 * 抽象为接口
 * 
 * @author Dream
 *
 */
public interface MvpPresenter<V extends MvpView> {

	/**
	 * 绑定视图
	 * 
	 * @param view
	 */
	public void attachView(V view);

	/**
	 * 解除绑定
	 */
	public void dettachView();
	
}
