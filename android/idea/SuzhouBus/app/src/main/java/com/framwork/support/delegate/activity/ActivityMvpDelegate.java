package com.framwork.support.delegate.activity;


import android.app.Activity;
import android.os.Bundle;

import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;

/**
 * 代理模式中的静态代理：定义Activity目标接口
 * <p>
 * 监听Activity生命周期的方法
 * Created by liangjun on 2017/1/15.
 */

public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {
    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onContentChanged();

    void onSaveInstanceState(Bundle outState);

    void onAttachedToWindow();

    Object onRetainCustomNonConfigurationInstance();

    Object getLastCustomNonConfigurationInstance();


}
