package net.lll0.framwork.mvpbase.presenter.impl;

import android.content.Context;
import android.util.Log;


import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.mvpbase.view.MvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liangjun on 2017/1/10.
 */

/**
 * 对于的该类主要的的任务就是进行视图的绑定和解绑。
 * <br/>
 * 但是考虑到View的销毁和意外切换，在该类中采用动态代理
 */

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<Context> weakContext;
    WeakReference<V> weakView;

    private V view;

    public MvpBasePresenter(Context context) {
      this.weakContext =   new WeakReference<Context>(context);
    }


    public Context getContext() {
        return weakContext.get();
    }

    @Override
    public void bindingView(V mvpView) {
        weakView = new WeakReference<V>(mvpView);
        MvpInvocationHandler mvpInvocationHandler = new MvpInvocationHandler(this.weakView.get());
        Log.e("Stone",mvpView.getClass().getSimpleName());
        view = (V) Proxy.newProxyInstance(mvpView
                        .getClass().getClassLoader(), mvpView.getClass().getInterfaces(),
                mvpInvocationHandler);

    }

    @Override
    public void unbindingView() {
        view = null;
    }

    /**
     * 提供获取当前绑定View的方法。
     *
     * @return
     */
    public V getView() {
        return view;
    }


    /**
     * 判断当前的view是否为空
     *
     * @return
     */
    public boolean isBindingView() {
        if (weakView != null && weakView.get() != null) {
            return true;
        }
        return false;
    }


    private class MvpInvocationHandler implements InvocationHandler {

        private MvpView mvpView;

        public MvpInvocationHandler(MvpView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isBindingView()) {
                return method.invoke(mvpView, args);
            }
            return null;
        }
    }


}
