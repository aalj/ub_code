package com.wdsunday.framework.base.presenter.impl;

import android.content.Context;

import com.wdsunday.framework.base.presenter.MvpPresenter;
import com.wdsunday.framework.base.view.MvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 所有的视图都需要通过该类进行绑定和解绑
 * <p>
 * Created by stone on 17-1-2.
 */

//通过泛型把需要绑定的视图传递。
public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    //在该类中使用了动态代理，用于处理的问题是在网络请求过程中视图销毁的处理

    private WeakReference<Context> weakContext;
    private WeakReference<V> weakView;

    //需要绑定的视图，但是已经使用动态代理进行处理了
    private V proxyView;


    public MvpBasePresenter(Context context) {
        //我的理解是现在是持有代理的对象
        this.weakContext = new WeakReference<Context>(context);
    }

    /**
     * 检查视图是否为空
     *
     * @return
     */
    private boolean isAttachView() {
        //通过代理判断当前视图是否为空
        if (this.weakView != null && this.weakView.get() != null) {
            return true;
        }
        return false;
    }


    @Override
    public void attachView(V view) {
        //通过代理模式进行处理以后进行视图的绑定
        this.weakView = new WeakReference<V>(view);
        MvpViewInvocationHandler invocationHandler =
                new MvpViewInvocationHandler(this.weakView.get());
        proxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),invocationHandler);

    }

    @Override
    public void dettachView() {

        if (this.weakView != null) {
            //清空代理对象
            this.weakView.clear();

            this.weakView = null;
        }

    }


    private class MvpViewInvocationHandler implements InvocationHandler {

        private MvpView mvpView;

        public MvpViewInvocationHandler(MvpView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            //判断当前视图对象是否为空，如果不为空返回当前代理对象中的视图对象，
            //否则返回空对象
            if (isAttachView()) {
                return method.invoke(mvpView, args);
            }


            return null;
        }
    }
}
