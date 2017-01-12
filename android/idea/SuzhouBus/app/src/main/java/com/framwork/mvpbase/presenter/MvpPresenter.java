package com.framwork.mvpbase.presenter;

import com.framwork.mvpbase.view.MvpView;

/**
 * Created by liangjun on 2017/1/10.
 */

public interface MvpPresenter<V extends MvpView>{
    //绑定视图和视图的接触绑定

    public  void bindingView(V mvpView);



    public void unbindingView();





}
