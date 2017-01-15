package com.framwork.support.delegate;


import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;

/**
 * 具体的代理对象--持有目标接口的实例（需要去实现的接口）
 * Created by liangjun on 2017/1/14.
 */

public class MvpDelegateCallBackProxy<V extends MvpView, P extends MvpPresenter<V>>
        implements MvpDelegateCallback<V, P> {

    private MvpDelegateCallback<V, P> mvpDelegateCallback;

    public MvpDelegateCallBackProxy(
            MvpDelegateCallback<V, P> mvpDelegateCallback) {
        this.mvpDelegateCallback = mvpDelegateCallback;
    }

    @Override
    public P createPresenter() {
        P presenter = mvpDelegateCallback.getPresenter();
        if (presenter == null) {
            presenter = mvpDelegateCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("Presenter is not null ! ");
        }

        //进行绑定，
        // TODO: 2017/1/14  但是这里的绑定是绑定什么，该方法的实现位于那个地方
        mvpDelegateCallback.setPresenter(presenter);
        return getPresenter();
    }

    @Override
    public P getPresenter() {
        P presenter = mvpDelegateCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter is not null! ");
        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mvpDelegateCallback.setPresenter(presenter);

    }

    @Override
    public V getMvpView() {
        return mvpDelegateCallback.getMvpView();
    }

    public void bindingView() {
        getPresenter().bindingView(getMvpView());
    }

    public void unbindingView() {
        getPresenter().unbindingView();

    }


}
