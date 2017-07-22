package net.lll0.framwork.support.lce.animator;

import android.view.View;

/**
 * Created by liangjun on 2017/1/17.
 */

public interface ILceAnimator {

    void showError(View loadingView, View contentView, View errorView);
    void showContent(View loadingView, View contentView, View errorView);
    void showLoading(View loadingView, View contentView, View errorView);


}
