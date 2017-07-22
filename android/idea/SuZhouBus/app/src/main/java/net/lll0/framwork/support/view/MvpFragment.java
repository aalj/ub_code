package net.lll0.framwork.support.view;

import android.support.v4.app.Fragment;

import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.mvpbase.view.MvpView;
import net.lll0.framwork.support.delegate.fragment.FragmentMvpDelegate;
import net.lll0.framwork.support.delegate.fragment.FragmentMvpDelegateCallBack;
import net.lll0.framwork.support.delegate.fragment.FragmentMvpDelegateImpl;


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
