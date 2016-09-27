package com.example.myapp;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gD;

    public OnSwipeTouchListener (Context ctx){
        gD = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gD.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int ST = 100;
        private static final int SVT = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent MotionE1, MotionEvent MotionE2, float vX, float vY) {
            boolean delta = false;
            try {
                float dY = MotionE2.getY() - MotionE1.getY();
                float dX = MotionE2.getX() - MotionE1.getX();
                if (Math.abs(dX) > Math.abs(dY)) {
                    if (Math.abs(dX) > ST && Math.abs(vX) > SVT) {
                        if (dX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    delta = true;
                }
                else if (Math.abs(dY) > ST && Math.abs(vY) > SVT) {
                    if (dY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                delta = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return delta;
        }
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}

