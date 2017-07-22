package net.lll0.framwork.mvpbase.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.mvpbase.view.MvpView;


/**
 * Created by liangjun on 2017/1/10.
 * 该类可以作为Activity的基类来处理但是这样或导致每写一个Activity
 * 都需要实现该类的抽象方法，导致重复代码会大量增多，而且不利于后期的维护
 * 所以对View层有进行再次封住，
 * <br/>
 * 采用代理来处理view和Presenter的绑定，
 */

public abstract class MvpBaseActivity<V extends MvpView, P extends MvpPresenter> extends AppCompatActivity {

    private V view;
    private P presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (view == null) {
            this.view = createView();
        }
        if (presenter == null) {
            this.presenter = createPresenter();
        }
        /*
        当绑定的view和persenter都不为空的时候进行view层和Presenter层的绑定
         */
        if (view != null && presenter != null) {
            presenter.bindingView(this.view);
        }


    }

    protected abstract P createPresenter();

    protected abstract V createView();
}
