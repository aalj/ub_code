package com.wdsunday.framework.base.presenter;

import android.view.View;

/**
 * Created by stone on 17-1-2.
 */
public interface MvpPresenter {
    /**
     * 绑定视图
     * @param View
     */
    void attachView(View View);

    /**
     * 解除绑定
     */
    void dettachView();


}
