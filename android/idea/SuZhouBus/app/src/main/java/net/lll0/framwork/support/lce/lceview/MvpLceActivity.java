package net.lll0.framwork.support.lce.lceview;

import android.view.View;

import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.support.lce.MvpLceView;
import net.lll0.framwork.support.lce.MvpLceViewImpl;
import net.lll0.framwork.support.lce.animator.ILceAnimator;
import net.lll0.framwork.support.view.MvpActivity;


/**
 * 加载页面出不同情况的activity
 * 带有动画的
 * <p>
 * Created by liangjun on 2017/2/3.
 */

public abstract class MvpLceActivity<M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> implements MvpLceView<M> {

    private MvpLceViewImpl<M> lceViewImpl;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        lceViewImpl = new MvpLceViewImpl<M>();
        //获取当前窗口中最顶层的View
        initLceView(getWindow().getDecorView());

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

    public void setLceAnimator(ILceAnimator lceAnimator) {
        lceViewImpl.setiLceAnimator(lceAnimator);
    }

    @Override
    public void showLoading(boolean isPullRefresh) {
        lceViewImpl.showLoading(isPullRefresh);
    }

    @Override
    public void showContent() {
        lceViewImpl.showContent();
    }

    @Override
    public void showError() {
        lceViewImpl.showError();
    }

    public void onErrorClick() {
        loadData(false);
    }

}
