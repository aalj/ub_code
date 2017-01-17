package com.framwork.support.lce;

import com.framwork.mvpbase.view.MvpView;

/**
 * Created by liangjun on 2017/1/17.
 */

public interface MvpLceView<M> extends MvpView {

    void showLoading(boolean isPullRefresh);
    void showError();
    void shoeContent();

    void bindingData(M data);

    void loadData(boolean isPullRefresh);




}
