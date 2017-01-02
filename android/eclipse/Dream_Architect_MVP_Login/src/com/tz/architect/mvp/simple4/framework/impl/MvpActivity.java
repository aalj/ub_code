package com.tz.architect.mvp.simple4.framework.impl;

import com.tz.architect.mvp.simple4.framework.AbsMvpPresenter;
import com.tz.architect.mvp.simple4.framework.IMvpView;

import android.app.Activity;
import android.os.Bundle;

public abstract class MvpActivity<V extends IMvpView, P extends AbsMvpPresenter<V>>
		extends Activity {

	private P presenter;
	private V view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (presenter == null) {
			this.presenter = bindPresenter();
		}
		if (view == null) {
			this.view = bindView();
			this.presenter.attachView(this.view);
		}

	}

	/**
	 * 绑定P层
	 * 
	 * @return
	 */
	public abstract P bindPresenter();

	/**
	 * 绑定View层
	 * 
	 * @return
	 */
	public abstract V bindView();

	public P getPresenter() {
		return presenter;
	}

	public V getView() {
		return view;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (this.presenter != null) {
			this.presenter.detachView();
		}
	}

}
