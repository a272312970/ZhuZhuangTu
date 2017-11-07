package com.example.pengshan.zhuzhuangtu;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

import com.example.pengshan.zhuzhuangtu.R;


/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class MyViewFlipper extends ViewFlipper {
    private final GestureDetector mDetector;
    private  Context context;


    public MyViewFlipper(Context context) {
        super(context);
        this.context = context;
        mDetector = new GestureDetector(new simpleGestureListener());
        setWillNotDraw(false);
       invalidate();
       forceLayout();
       requestLayout();
    }

    public MyViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mDetector = new GestureDetector(new simpleGestureListener());//定义手势识别器
        setWillNotDraw(false);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);//请求父控件和祖宗控件不要拦截触摸事件,必须在这个触摸事件入口写
        stopFlipping();                // 点击事件后，停止自动播放
        setAutoStart(false);
        return mDetector.onTouchEvent(ev);//所以触摸事件的处理也要写在这里
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }

    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {


        //不加上onDown函数的话，onFling就不会响应
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > 100) {

                setInAnimation(context, R.anim.animation_right_in);
                setOutAnimation(context, R.anim.animation_left_out);
                if(mOnMyMoveListern != null){
                    mOnMyMoveListern.move();
                }
                showNext();
            } else if (e1.getX() - e2.getX() < -120) {

                setInAnimation(context, R.anim.animation_left_in);
                setOutAnimation(context, R.anim.animation_right_out);
                showPrevious();
            }

            return true;
        }

    }

    public void setOnMyMoveListern(OnMyMoveListern onMyMoveListern) {
        mOnMyMoveListern = onMyMoveListern;
    }

    OnMyMoveListern mOnMyMoveListern;

    public interface OnMyMoveListern{
        void move();
    }

}
