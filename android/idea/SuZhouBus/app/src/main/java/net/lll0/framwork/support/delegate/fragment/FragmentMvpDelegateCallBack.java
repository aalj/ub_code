package net.lll0.framwork.support.delegate.fragment;


import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.mvpbase.view.MvpView;
import net.lll0.framwork.support.delegate.MvpDelegateCallback;

/**
 *
 * 用于扩展每个模块各自扩展自己的功能
 * Created by liangjun on 2017/1/15.
 */

public interface FragmentMvpDelegateCallBack<V extends MvpView,P extends MvpPresenter<V>>
        extends MvpDelegateCallback<V,P> {







}
