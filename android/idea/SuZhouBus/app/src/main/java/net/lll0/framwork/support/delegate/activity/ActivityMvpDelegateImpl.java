package net.lll0.framwork.support.delegate.activity;

import android.os.Bundle;

import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.mvpbase.view.MvpView;
import net.lll0.framwork.support.delegate.MvpDelegateCallBackProxy;


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
        getDelagateProxy().createPresenter();
        getDelagateProxy().bindingView();


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

    //以下两个方法用于对数据是否保存的处理
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        // 存储对象(思考：到底要存什么数据？)
        // 保存：Presenter对象引用（时时刻刻保持引用对象）
        // 保存：数据（List数据、对象数据）例如：用户名、密码等等......
        // 判断到底要不要缓存该对象（可配置）
        // 不是所有的Activity都需要缓存(具体的要更具需求确定)
        // 例如：表单页面
        // 思考怎么判断我要保存呢？
        boolean retained = activityDelegateCallBack.shouldInstanceBeRetained();
        // 根据状态，判断是否保存Presenter，不保存我就立马释放
        P presenter = retained ? activityDelegateCallBack.getPresenter() : null;
        //保存数据对象
        Object instance = activityDelegateCallBack
                .onRetainCustomNonConfigurationInstance();
        //两个对对象都等于空,不需要缓存,
        if (presenter == null && instance == null) {
            return null;
        }
        //需要缓存
        return new ActivityMvpConfigurationInstance<V, P>(presenter
                , instance);
    }

    @Override
    public Object getLastCustomNonConfigurationInstance() {
        Object instance = activityDelegateCallBack
                .getLastCustomNonConfigurationInstance();
        if (instance != null && instance instanceof ActivityMvpConfigurationInstance) {
            ActivityMvpConfigurationInstance<V,P> configurationInstance = (ActivityMvpConfigurationInstance<V, P>) instance;
            return  configurationInstance.getCustomeConfigurationInstance();
        }
        return null;
    }


}
