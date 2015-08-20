package com.gmy.dragdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2015/8/18.
 */
public class DragLayout extends ViewGroup {
    private ViewDragHelper mDragHelper;
    private View mChildView;
    /**
     * 可以滑动的最大距离
     */
    private int mHeight = 0;
    private int mTop = 0;
    /**
     * 拖拽的偏移量
     */
    private float mDragOffset;

    public DragLayout(Context context) {
        super(context);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 初始化draghelper
         */
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragCallback());
    }

//    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//    }

    @Override
    protected void onFinishInflate() {
        mChildView = findViewById(R.id.tv2);
        super.onFinishInflate();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mChildView != null) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        mHeight = getHeight() - mChildView.getHeight() - paddingTop - paddingBottom;
        if (mChildView != null) {
            mChildView.layout(paddingLeft, paddingTop, paddingLeft + mChildView.getMeasuredWidth(), paddingTop + mChildView.getMeasuredHeight());
        }
        Log.d("gmyboy", "paddingLeft" + paddingLeft + "  paddingTop" + paddingTop + "  mChildView.getMeasuredWidth()" + mChildView.getMeasuredWidth() + "  mChildView.getMeasuredHeight()" + mChildView.getMeasuredHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_CANCEL || ev.getAction() == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev); //将事件传递给ViewDragHelper，让它自己处理是否需要打断。
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    /**
     * 滑到底
     */
    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private class DragCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mChildView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            int leftBound = getPaddingLeft();
            int rightBound = getWidth() - mChildView.getWidth() - leftBound;
            int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int topBound = getPaddingTop();
            int bottomBound = getHeight() - mChildView.getHeight() - topBound;
            int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }

//        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
//            mTop = top;
//
//            mDragOffset = (float) top / mHeight;
//
//            mChildView.setPivotX(mChildView.getWidth());
//            mChildView.setPivotY(mChildView.getHeight());
//            mChildView.setScaleX(1 - mDragOffset / 2);
//            mChildView.setScaleY(1 - mDragOffset / 2);
//
//            requestLayout();
//        }

        /**
         * 释放时状态
         *
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int top = getPaddingTop();
            if (yvel > 0 || (yvel == 0 && mDragOffset > 0.5f)) {
                top += mHeight;
            }
            mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
            invalidate();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mHeight;
        }
    }
}
