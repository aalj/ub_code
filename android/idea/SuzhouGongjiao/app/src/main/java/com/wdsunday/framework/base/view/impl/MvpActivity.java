package com.wdsunday.framework.base.view.impl;

/**
 * Created by stone on 17-1-2.
 */

import android.app.Activity;
import android.os.Bundle;
import com.wdsunday.framework.base.presenter.MvpPresenter;
import com.wdsunday.framework.base.view.MvpView;

/**
 * 这是抽象出来的Activity的父类
 * @param <V> 需要关联的view
 * @param <P> 需要关联的Presenter
 */
public abstract class MvpActivity<V extends MvpView,P extends MvpPresenter> extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
