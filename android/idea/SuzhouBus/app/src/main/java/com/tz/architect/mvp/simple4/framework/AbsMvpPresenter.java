package com.tz.architect.mvp.simple4.framework;

public abstract class AbsMvpPresenter<V extends IMvpView> {

	private V view;

	public V getView() {
		return view;
	}

	/**
	 * 绑定
	 * 
	 * @param view
	 */
	public void attachView(V view) {
		this.view = view;
	}

	/**
	 * 解除绑定
	 */
	public void detachView() {
		this.view = null;
	}

}
