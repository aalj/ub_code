package net.lll0.framwork.support.delegate.fragment;

import android.os.Bundle;
import android.view.View;

import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.mvpbase.view.MvpView;
import net.lll0.framwork.support.delegate.MvpDelegateCallBackProxy;


/**
 * Created by liangjun on 2017/1/15.
 */

public class FragmentMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
        implements FragmentMvpDelegate {

    private MvpDelegateCallBackProxy<V, P> proxy;

    //具体的目标实现类，需要持有创建的MVP实例
    private FragmentMvpDelegateCallBack<V, P> fragmentMvpDelegateCallBack;


    public FragmentMvpDelegateImpl(
            FragmentMvpDelegateCallBack<V, P> fragmentMvpDelegateCallBack) {
        if (fragmentMvpDelegateCallBack == null) {
            throw new NullPointerException(" FragmentMvpDelegateCallBack id not null ! ");
        }
        this.fragmentMvpDelegateCallBack = fragmentMvpDelegateCallBack;
    }

    private MvpDelegateCallBackProxy<V,P> getFragmentMvpDelegateCallBack(){
        if(proxy == null){
            this.proxy   = new MvpDelegateCallBackProxy<V,P>(fragmentMvpDelegateCallBack);
        }

        return this.proxy;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {

    }

    @Override
    public void onCreateView(View view, Bundle saveInstanceState) {

        getFragmentMvpDelegateCallBack().createPresenter();
        getFragmentMvpDelegateCallBack().bindingView();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroyView() {
        getFragmentMvpDelegateCallBack().unbindingView();

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState() {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }
}
