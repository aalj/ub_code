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

    // 获取实例
    public Object getLastCustomNonConfigurationInstance();

    // 还需要定义其它的一些自定义方法(用处？)－－－我要做一些逻辑处理
    // 注意：该方法可以改名字，可以不改
    public Object onRetainCustomNonConfigurationInstance();

    // 一下方法是对getLastCustomNonConfigurationInstance方法处理
    //为了方便扩展，所以留在
    public Object getNonLastCustomNonConfigurationInstance();




}
