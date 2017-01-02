package com.wdsunday.framework.base.presenter;

import android.view.View;

import com.wdsunday.framework.base.view.MvpView;

/**
 * Created by stone on 17-1-2.
 */
public interface MvpPresenter<V extends MvpView> {

    /**
     * 绑定视图
     * @param view
     */
    void attachView(V view);

    /**
     * 解除绑定
     */
    void dettachView();


}
