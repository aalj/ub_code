package com.tz.architect.mvp.simple5.framework.view.impl;

import com.tz.architect.mvp.simple5.framework.presenter.MvpPresenter;
import com.tz.architect.mvp.simple5.framework.view.MvpView;

import android.app.Activity;
import android.os.Bundle;

/**
 * V层定义
 * 
 * @author Dream
 *
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
		extends Activity {

	private P presenter;
	private V view;

	public P getPresenter() {
		return presenter;
	}

	public V getView() {
		return view;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	protected void onDestroy() {
		super.onDestroy();
		if (this.presenter != null) {
			this.presenter.dettachView();
			this.presenter = null;
		}
	}

}
