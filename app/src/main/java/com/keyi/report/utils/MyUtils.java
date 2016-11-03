package com.keyi.report.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.keyi.report.R;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MyUtils {
    public static final int DELAY = 138;
    public static int mLastPosition = -1;
    public static final void inTent(Context context, Class o) {
        Intent intent = new Intent(context, o);
        context.startActivity(intent);
    }
    public static final int[] getColors() {
        int stacksize = 2;
        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];
        colors[1] = Color.rgb(21, 112, 188);
        colors[0] = Color.rgb(8, 31, 93);
        return colors;
    }

    public static final int[] getColors1() {
        int stacksize = 2;
        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];
        colors[1] = Color.rgb(21, 112, 188);
        colors[0] = Color.rgb(255, 0, 102);
        return colors;
    }
    //listView的动画
    public static final void showItemAnim(final View view, final int position) {
        final Context context = view.getContext();
        if (position > mLastPosition) {
            view.setAlpha(0);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    {
                        Animation animation = AnimationUtils.loadAnimation(context,
                                R.anim.slide_in_right);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override public void onAnimationStart(Animation animation) {
                                view.setAlpha(1);
                            }


                            @Override public void onAnimationEnd(Animation animation) {}


                            @Override public void onAnimationRepeat(Animation animation) {}
                        });
                        view.startAnimation(animation);
                    }
                }
            }, DELAY * position);
            mLastPosition = position;
        }
    }
}
