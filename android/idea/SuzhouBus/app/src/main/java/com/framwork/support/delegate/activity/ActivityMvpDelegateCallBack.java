package com.framwork.support.delegate.activity;

import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;
import com.framwork.support.delegate.MvpDelegateCallback;

/**
 *
 * 该接口是用于在不同模块中，自己的扩展
 * Created by liangjun on 2017/1/15.
 */

public interface ActivityMvpDelegateCallBack<V extends MvpView,P extends MvpPresenter<V>>
        extends MvpDelegateCallback<V,P> {






}
