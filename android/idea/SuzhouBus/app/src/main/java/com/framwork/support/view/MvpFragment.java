package com.framwork.support.view;

import android.support.v4.app.Fragment;

import com.framwork.mvpbase.presenter.MvpPresenter;
import com.framwork.mvpbase.view.MvpView;
import com.framwork.support.delegate.fragment.FragmentMvpDelegate;
import com.framwork.support.delegate.fragment.FragmentMvpDelegateCallBack;
import com.framwork.support.delegate.fragment.FragmentMvpDelegateImpl;

/**
 * Created by liangjun on 2017/1/17.
 */

public class MvpFragment <V extends MvpView,P extends MvpPresenter<V>>
        extends Fragment
        implements FragmentMvpDelegateCallBack<V,P>,MvpView {

    private P presenter;
    private FragmentMvpDelegate<V,P> fragmentMvpDelegate;

    public FragmentMvpDelegate<V, P> getFragmentMvpDelegate() {
        if (fragmentMvpDelegate == null) {
            this.fragmentMvpDelegate =
                    new FragmentMvpDelegateImpl<V, P>(this);
        }


        return fragmentMvpDelegate;
    }

    @Override
    public P createPresenter() {
        return null;
    }

    @Override
    public P getPresenter() {
        return null;
    }

    @Override
    public void setPresenter(P presenter) {

    }

    @Override
    public V getMvpView() {
        return null;
    }

    @Override
    public boolean isRetainInstance() {
        return false;
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return false;
    }
}
