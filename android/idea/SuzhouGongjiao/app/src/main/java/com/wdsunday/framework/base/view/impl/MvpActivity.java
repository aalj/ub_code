package com.wdsunday.framework.base.view.impl;

/**
 * Created by stone on 17-1-2.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wdsunday.framework.base.presenter.MvpPresenter;
import com.wdsunday.framework.base.view.MvpView;

/**
 * 这是抽象出来的Activity的父类
 * @param <V> 需要关联的view
 * @param <P> 需要关联的Presenter
 */
public abstract class MvpActivity<V extends MvpView,P extends MvpPresenter> extends AppCompatActivity {

    private P presenter;
    private V view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }

        if (this.view == null) {
            this.view = createView();
        }
        if(this.presenter != null && this.view != null){
            this.presenter.attachView(view);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.dettachView();
            this.presenter = null;
        }
    }

    public P getPresenter(){
        return presenter;
    }



    public abstract P createPresenter();
    public abstract V createView();



}
