package com.tz.architect.mvp.simple5.framework.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.tz.architect.mvp.simple5.framework.presenter.MvpPresenter;
import com.tz.architect.mvp.simple5.framework.view.MvpView;

/**
 * V层定义
 * 
 * @author Dream
 *
 */
public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>>
		extends Fragment {

	private P presenter;
	private V view;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (this.presenter == null) {
			this.presenter = createPresenter();
		}
		if (this.view == null) {
			this.view = createView();
		}
		if (this.presenter != null && this.view != null) {
			this.presenter.attachView(view);
		}
	}

	/**
	 * 绑定P层
	 * 
	 * @return
	 */
	public abstract P createPresenter();

	/**
	 * 创建View层
	 * 
	 * @return
	 */
	public abstract V createView();

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (this.presenter != null) {
			this.presenter.dettachView();
			this.presenter = null;
		}
	}

}
