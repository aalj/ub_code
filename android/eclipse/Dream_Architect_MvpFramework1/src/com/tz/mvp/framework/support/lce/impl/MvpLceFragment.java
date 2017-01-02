package com.tz.mvp.framework.support.lce.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.tz.architect.mvpframework.R;
import com.tz.mvp.framework.base.presenter.MvpPresenter;
import com.tz.mvp.framework.support.lce.MvpLceView;
import com.tz.mvp.framework.support.lce.impl.animator.DefaultLceAnimator;
import com.tz.mvp.framework.support.lce.impl.animator.ILceAnimator;
import com.tz.mvp.framework.support.view.MvpFragment;

public abstract class MvpLceFragment<M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
		extends MvpFragment<V, P> implements MvpLceView<M> {

	private View loadingView;
	private View contentView;
	private View errorView;

	// 初始化Lce UI布局（规定你的Lce布局文件的id）

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initLceView(view);
	}

	private void initLceView(View v) {
		if (loadingView == null) {
			loadingView = v.findViewById(R.id.loadingView);
		}
		if (contentView == null) {
			contentView = v.findViewById(R.id.contentView);
		}
		if (errorView == null) {
			errorView = v.findViewById(R.id.errorView);
		}
		if (loadingView == null) {
			throw new NullPointerException("loadingView is not null!");
		}
		if (contentView == null) {
			throw new NullPointerException("contentView is not null!");
		}
		if (errorView == null) {
			throw new NullPointerException("errorView is not null!");
		}
		errorView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onErrorClick();
			}
		});
	}

	/**
	 * 如果子类需要配置自己项目中的动画特性，需要重写该方法
	 * 
	 * @return
	 */
	public ILceAnimator getLceAnimator() {
		return DefaultLceAnimator.getInstance();
	}

	@Override
	public void showLoading(boolean pullToRefresh) {
		// 执行动画
		getLceAnimator().showLoading(loadingView, contentView, errorView);
	}

	@Override
	public void showContent() {
		// 执行动画
		getLceAnimator().showContent(loadingView, contentView, errorView);
	}

	@Override
	public void showError() {
		// 执行动画
		getLceAnimator().showErrorView(loadingView, contentView, errorView);
	}

	public void onErrorClick() {
		loadData(false);
	}

}
