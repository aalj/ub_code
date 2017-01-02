package com.tz.mvp.framework.support.view;

import android.app.Activity;
import android.os.Bundle;

import com.tz.mvp.framework.base.presenter.MvpPresenter;
import com.tz.mvp.framework.base.view.MvpView;
import com.tz.mvp.framework.support.delegate.activity.ActivityMvpDelegate;
import com.tz.mvp.framework.support.delegate.activity.ActivityMvpDelegateCallback;
import com.tz.mvp.framework.support.delegate.activity.ActivityMvpDelegateImpl;

/**
 * 代理模式－静态代理：MvpActivity是MvpDelegateCallback具体的实现类
 * 
 * @author Dream
 *
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
		extends Activity implements ActivityMvpDelegateCallback<V, P>, MvpView {

	private P presenter;

	private ActivityMvpDelegate<V, P> activityMvpDelegate;

	protected ActivityMvpDelegate<V, P> getActivityMvpDelegate() {
		if (this.activityMvpDelegate == null) {
			this.activityMvpDelegate = new ActivityMvpDelegateImpl<V, P>(this);
		}
		return this.activityMvpDelegate;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivityMvpDelegate().onCreate(savedInstanceState);
	}

	@Override
	public void setPresenter(P presenter) {
		this.presenter = presenter;
	}

	@Override
	public P getPresenter() {
		return this.presenter;
	}

	@Override
	public V getMvpView() {
		return (V) this;
	}

	@Override
	protected void onStart() {
		super.onStart();
		getActivityMvpDelegate().onStart();
	}

	@Override
	protected void onPause() {
		super.onPause();
		getActivityMvpDelegate().onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getActivityMvpDelegate().onResume();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		getActivityMvpDelegate().onRestart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		getActivityMvpDelegate().onStop();
	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();
		getActivityMvpDelegate().onContentChanged();
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		getActivityMvpDelegate().onAttachedToWindow();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getActivityMvpDelegate().onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		getActivityMvpDelegate().onDestroy();
	}

}
