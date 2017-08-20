package ru.unionfreeart.ufart.utils;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import ru.unionfreeart.ufart.R;

/**
 * Created by NeoSvet on 20.05.2017.
 */

public class FabHelper {

    public static void help(Context context, ListView list,  final View[] views) {
        final Animation anMin = AnimationUtils.loadAnimation(context, R.anim.minimize);
        anMin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                for (int i = 0; i < views.length; i++) {
                    views[i].setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final Animation anMax = AnimationUtils.loadAnimation(context, R.anim.maximize);

        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        for (int i = 0; i < views.length; i++) {
                            views[i].startAnimation(anMin);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        for (int i = 0; i < views.length; i++) {
                            views[i].setVisibility(View.VISIBLE);
                            views[i].startAnimation(anMax);
                        }
                        break;
                }
                return false;
            }
        });
    }
}
