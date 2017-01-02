package com.tz.architect.mvp.simple5.framework.view.impl.layout;

import com.tz.architect.mvp.simple5.framework.presenter.MvpPresenter;
import com.tz.architect.mvp.simple5.framework.view.MvpView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public abstract class MvpLinearLayout<V extends MvpView, P extends MvpPresenter<V>>
		extends LinearLayout {

	private P presenter;
	private V view;

	public MvpLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MvpLinearLayout(Context context) {
		super(context);
		init();
	}

	private void init() {
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
	public void removeView(View view) {
		super.removeView(view);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (this.presenter != null) {
			this.presenter.dettachView();
			this.presenter = null;
		}
	}

}
