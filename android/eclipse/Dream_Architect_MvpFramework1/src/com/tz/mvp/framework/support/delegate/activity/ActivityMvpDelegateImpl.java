package com.tz.mvp.framework.support.delegate.activity;

import android.os.Bundle;

import com.tz.mvp.framework.base.presenter.MvpPresenter;
import com.tz.mvp.framework.base.view.MvpView;
import com.tz.mvp.framework.support.delegate.MvpDelegateCallbackProxy;

/**
 * 代理模式－静态代理：具体的目标接口实现类 该实现类对应的代理类是Activity
 * 
 * @author Dream
 *
 * @param <V>
 * @param <P>
 */
public class ActivityMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
		implements ActivityMvpDelegate<V, P> {

	private MvpDelegateCallbackProxy<V, P> proxy;
	// 具体的目标接口实现类，需要持有创建Mvp实例
	private ActivityMvpDelegateCallback<V, P> delegateCallback;

	public ActivityMvpDelegateImpl(
			ActivityMvpDelegateCallback<V, P> delegateCallback) {
		if (delegateCallback == null) {
			throw new NullPointerException("delegateCallback is not null!");
		}
		this.delegateCallback = delegateCallback;
	}

	private MvpDelegateCallbackProxy<V, P> getDelegateProxy() {
		if (this.proxy == null) {
			this.proxy = new MvpDelegateCallbackProxy<V, P>(delegateCallback);
		}
		return this.proxy;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		getDelegateProxy().createPresenter();
		getDelegateProxy().attachView();
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onPause() {

	}

	@Override
	public void onResume() {

	}

	@Override
	public void onRestart() {

	}

	@Override
	public void onStop() {

	}

	@Override
	public void onDestroy() {
		getDelegateProxy().detachView();
	}

	@Override
	public void onContentChanged() {

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

	}

	@Override
	public void onAttachedToWindow() {

	}

}
