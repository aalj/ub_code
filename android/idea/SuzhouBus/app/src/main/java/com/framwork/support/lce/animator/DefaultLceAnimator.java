package com.framwork.support.lce.animator;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wdsunday.R;

/**
 *
 * 策略模式,具体的策略,采用默认的执行策略
 * Created by liangjun on 2017/1/17.
 */

public class DefaultLceAnimator implements ILceAnimator {

    public static DefaultLceAnimator defaultLceAnimator;


    private DefaultLceAnimator() {
    }

    public static DefaultLceAnimator getInstance() {
        if (defaultLceAnimator == null) {

            synchronized (DefaultLceAnimator.class) {
                if (defaultLceAnimator == null) {
                    defaultLceAnimator = new DefaultLceAnimator();

                }
            }

        }


        return defaultLceAnimator;
    }

    @Override
    public void showError(final View loadingView, View contentView, final View errorView) {

        contentView.setVisibility(View.GONE);

        Resources resources = loadingView.getResources();

        AnimatorSet set = new AnimatorSet();

        //不要导错包了

        ObjectAnimator in = ObjectAnimator.ofFloat(errorView, "alpha", 1f);
        final ObjectAnimator loadingOut = ObjectAnimator.ofFloat(loadingView, "alpha", 0f);

        set.playTogether(in, loadingOut);
        set.setDuration(resources.getInteger(R.integer.lce_error_view_show_animation_time));
        set.addListener(
                new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        loadingView.setVisibility(View.GONE);
                        loadingView.setAlpha(1f);
                    }


                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        errorView.setVisibility(View.VISIBLE);
                    }

                }
        );
        set.start();

    }

    @Override
    public void showContent(final View loadingView, final View contentView, final View errorView) {
        if (contentView.getVisibility() == View.VISIBLE) {
            errorView.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
        } else {
            //隐藏加载错误页面
            errorView.setVisibility(View.GONE);
            Resources resources = loadingView.getResources();
            AnimatorSet set = new AnimatorSet();
            int translateInPixels = resources.getDimensionPixelSize(R.dimen.lce_content_view_animation_translate_y);
            final ObjectAnimator contentFadeIn
                     = ObjectAnimator.ofFloat(contentView,"alpha",0f,1f);

            ObjectAnimator contentTranslateIn = ObjectAnimator.ofFloat(
                    contentView,"translationY",translateInPixels,0);

            ObjectAnimator loadingFadeOut = ObjectAnimator.ofFloat(
                    loadingView,"alpha",1f,0f);

            ObjectAnimator loadingTranslateOut = ObjectAnimator.ofFloat(
                    loadingView,"translationY",0,-translateInPixels);
            set.playTogether(contentFadeIn,contentTranslateIn,loadingFadeOut,loadingTranslateOut);
            set.setDuration(resources.getInteger(R.integer.lce_content_view_show_animation_time));


            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    loadingView.setVisibility(View.GONE);
                    loadingView.setAlpha(1f);
                    contentView.setTranslationY(0);
                    loadingView.setTranslationY(0);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    contentView.setTranslationY(0);
                    loadingView.setTranslationY(0);
                    contentView.setVisibility(View.VISIBLE);
                }
            });



        }


    }

    @Override
    public void showLoading(View loadingView, View contentView, View errorView) {
        loadingView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }
}
