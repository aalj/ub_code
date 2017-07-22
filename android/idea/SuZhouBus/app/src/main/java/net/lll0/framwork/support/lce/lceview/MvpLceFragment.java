package net.lll0.framwork.support.lce.lceview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.support.lce.MvpLceView;
import net.lll0.framwork.support.lce.MvpLceViewImpl;
import net.lll0.framwork.support.view.MvpFragment;


/**
 * Created by liangjun on 2017/2/3.
 */

public class MvpLceFragment<M,V extends MvpLceView<M>,P extends MvpPresenter<V>>
extends MvpFragment<V,P> implements MvpLceView<M>{
    private MvpLceViewImpl<M> lceViewImpl;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (lceViewImpl==null) {
            lceViewImpl = new MvpLceViewImpl<M>();
        }
        initLceView(view);
    }

    private void initLceView(View v) {
        lceViewImpl.initLceView(v);
        lceViewImpl.setOnErrorViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorClick();
            }
        });
    }


    @Override
    public void showLoading(boolean isPullRefresh) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void bindingData(M data) {

    }

    @Override
    public void loadData(boolean isPullRefresh) {

    }

    public void onErrorClick(){
        loadData(false);
    }

}
