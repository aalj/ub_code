package net.lll0.framwork.support.lce;


import net.lll0.framwork.mvpbase.view.MvpView;

/**
 * Created by liangjun on 2017/1/17.
 */

public interface MvpLceView<M> extends MvpView {

    void showLoading(boolean isPullRefresh);

    void showError();

    void showContent();

    void bindingData(M data);

    void loadData(boolean isPullRefresh);


}
