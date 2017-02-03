package com.framwork.support.delegate.activity;

import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;

/**
 * Created by liangjun on 2017/2/3.
 */

public class ActivityMvpConfigurationInstance<V extends MvpView,P extends MvpPresenter<V>> {
    private P presenter;
    private Object customeConfigurationInstance;

    public ActivityMvpConfigurationInstance(P presenter,Object customeConfigurationInstance){
        super();
        this.presenter=presenter;
        this.customeConfigurationInstance = customeConfigurationInstance;
    }


    public P getPresenter(){
        return presenter;
    }

    public void setPresenter(P presenter){
        this.presenter = presenter;
    }
    public Object getCustomeConfigurationInstance(){
        return customeConfigurationInstance;
    }
    public void setCustomeConfigurationInstance(Object customeConfigurationInstance){
        this.customeConfigurationInstance = customeConfigurationInstance;
    }



}


