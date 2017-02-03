package com.framwork.support.delegate;


import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;

/**
 * 代理模式--静态代理 ：目标接口
 * 理解为需要实现的接口
 * <p>
 * 对于所有的View Activity Fragment 如果采用MVP模式那么就会实现该接口
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


    //--------------------以上为mvp中各层之间的 绑定 接下来的是 对与数据的存储
    void setRetainInstance(boolean retaionInstance);
     boolean isRetainInstance();

    //判断是够保存数据(可以通过该方法判断横竖屏的切换)
    boolean shouldInstanceBeRetained();



}
