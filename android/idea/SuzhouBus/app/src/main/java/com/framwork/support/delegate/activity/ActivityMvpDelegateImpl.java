package com.framwork.support.delegate.activity;

import android.os.Bundle;

import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;
import com.framwork.support.delegate.MvpDelegateCallBackProxy;

/**
 * 代理模式：静态代理  具体的目标接口实现类，该实现类对应的代理类是Activity
 * <p>
 * Created by liangjun on 2017/1/15.
 */

public class ActivityMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
        implements ActivityMvpDelegate<V, P> {

    private MvpDelegateCallBackProxy<V, P> proxy;
    private ActivityMvpDelegateCallBack<V, P> activityDelegateCallBack;

    public ActivityMvpDelegateImpl(
            ActivityMvpDelegateCallBack<V, P> activityDelegateCallBack) {
        if (activityDelegateCallBack == null) {
            throw new NullPointerException("ActivityMvpDelegateCallBack is not null !  ");
        }
        this.activityDelegateCallBack = activityDelegateCallBack;
    }

    private MvpDelegateCallBackProxy<V, P> getDelagateProxy() {
        if (proxy == null) {
            proxy = new MvpDelegateCallBackProxy<V, P>(activityDelegateCallBack);
        }
        return this.proxy;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getDelagateProxy().createPresenter();getDelagateProxy().bindingView();


    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        getDelagateProxy().unbindingView();

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
