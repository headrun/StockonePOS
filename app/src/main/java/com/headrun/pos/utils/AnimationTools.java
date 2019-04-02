package com.headrun.pos.utils;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewTreeObserver;

import com.blankj.utilcode.util.LogUtils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class AnimationTools {

    public static void Animate(final Techniques techniques, final View view, final int duration, final int repeat){
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                YoYo.with(techniques)
                        .repeat(repeat)
                        .duration(duration)
                        .playOn(view);
            }
        });
    }

    public static void Animate(final Techniques techniques, final View view, final int duration,
                               final OnAnimationEndListner onAnimationEndListner){
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                YoYo.with(techniques)
                        .duration(duration)
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {
//                                LogUtils.e("Animation start");

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                onAnimationEndListner.onFinish();
//                                LogUtils.e("Animation end");
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {
//                                LogUtils.e("Animation cancel");

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {
                                onAnimationEndListner.onFinish();
                                LogUtils.e("Animation repeated");
                            }
                        })
                        .playOn(view);
            }
        });
    }

    public static void Animate(Techniques techniques, View view, int repeat){
        Animate(techniques, view, 800, repeat);
    }

    public static void Animate(Techniques techniques, int duration, View view){
        Animate(techniques, view, duration, 0);
    }

    public static void Animate(Techniques techniques, View view){
        Animate(techniques, view, 800, 0);
    }

    public static void changeColor(int current, int update, final View view){
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(current, update);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setBackgroundColor((Integer)valueAnimator.getAnimatedValue());
            }
        });
        anim.setDuration(300);
        anim.start();
    }

    public interface OnAnimationEndListner{
        void onFinish();
    }
}
