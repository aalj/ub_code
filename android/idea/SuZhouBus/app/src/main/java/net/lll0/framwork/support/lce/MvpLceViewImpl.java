package net.lll0.framwork.support.lce;

import android.view.View;

import net.lll0.framwork.support.lce.animator.DefaultLceAnimator;
import net.lll0.framwork.support.lce.animator.ILceAnimator;
import net.lll0.bus.suzhoubus.R;


/**
 * 代理模式的目标接口
 * Created by liangjun on 2017/1/17.
 */

public class MvpLceViewImpl<M> implements MvpLceView<M> {

    private View loadingView;
    private View contentView;
    private View errorView;


    private ILceAnimator iLceAnimator;

    public ILceAnimator getiLceAnimator() {
        if (iLceAnimator == null) {
            iLceAnimator = DefaultLceAnimator.getInstance();
        }
        return iLceAnimator;
    }



    /**
     * 添加重写加载监听
     *
     * @param onClickListener
     */
    public void setOnErrorViewClickListener(View.OnClickListener onClickListener) {
        if (this.errorView != null) {
            this.errorView.setOnClickListener(onClickListener);
        }
    }

    /**
     * 绑定动画的加载策略
     * @param iLceAnimator
     */
    public void setiLceAnimator(ILceAnimator iLceAnimator) {
        this.iLceAnimator = iLceAnimator;
    }


    public void initLceView(View view) {
        if (loadingView == null) {
            loadingView = view.findViewById(R.id.loadingView);

        }
        if (contentView == null) {
            contentView = view.findViewById(R.id.contentView);
        }
        if (errorView == null) {
            errorView = view.findViewById(R.id.errorView);
        }


        if (loadingView == null) {
            throw new NullPointerException("loadingView in not null ");
        }
        if (contentView == null) {
            throw new NullPointerException("contentView in not null ");

        }
        if (errorView == null) {
            throw new NullPointerException("errorView in not null ");

        }


    }

    @Override
    public void showLoading(boolean isPullRefresh) {
        iLceAnimator.showLoading(loadingView,contentView,errorView);
    }

    @Override
    public void showError() {
        iLceAnimator.showError(loadingView,contentView,errorView);

    }

    @Override
    public void showContent() {
        iLceAnimator.showContent(loadingView,contentView,errorView);

    }

    @Override
    public void bindingData(M data) {

    }

    @Override
    public void loadData(boolean isPullRefresh) {

    }

    @Override
    public void getError(String errorMessge) {

    }
}
