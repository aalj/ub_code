package com.framwork.support.delegate;


import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;

/**
 * 代理模式--静态代理 ：目标接口
 * 理解为需要实现的接口
 * <p>
 * Created by liangjun on 2017/1/10.
 */

public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * 创建Presenter的接口方法
     */
    P createPresenter();


    /**
     * 得到Presenter的接口方法
     */
    P getPresenter();

    /**
     * 设置新的Presenter的方法
     */
    void setPresenter(P presenter);

    /**
     * 得到具体的MVPview示例对象
     */

    V getMvpView();


}
