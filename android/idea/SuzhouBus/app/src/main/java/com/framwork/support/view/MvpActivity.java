package com.framwork.support.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;
import com.framwork.support.delegate.activity.ActivityMvpDelegate;
import com.framwork.support.delegate.activity.ActivityMvpDelegateCallBack;
import com.framwork.support.delegate.activity.ActivityMvpDelegateImpl;

/**
 * Created by liangjun on 2017/2/3.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends FragmentActivity implements ActivityMvpDelegateCallBack<V, P>, MvpView {

    private P presenter;
    private ActivityMvpDelegate<V,P> activityMvpDelegate;

    protected ActivityMvpDelegate<V,P> getActivityMvpDelegate(){
        if (this.activityMvpDelegate==null) {
            this.activityMvpDelegate = new ActivityMvpDelegateImpl<V,P>(this);
        }

        return this.activityMvpDelegate;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityMvpDelegate().onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getActivityMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getActivityMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getActivityMvpDelegate().onStart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getActivityMvpDelegate().onContentChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getActivityMvpDelegate().onStop();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityMvpDelegate().onCreate(savedInstanceState);
    }



    @Override
    public V getMvpView() {
        return (V)this;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;

    }

    @Override
    public P getPresenter() {
        return   this.presenter;
    }

}
