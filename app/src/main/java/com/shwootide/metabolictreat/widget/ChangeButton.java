package com.shwootide.metabolictreat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.shwootide.metabolictreat.R;

/**
 * Created by GMY on 2015/9/14 16:25.
 * Contact me via email gmyboy@qq.com.
 */
public class ChangeButton extends Button implements View.OnClickListener {
    private boolean mIsChecked = false;
    private CharSequence onText;
    private CharSequence offText;
    private OnClickListener listener;

    public ChangeButton(Context context) {
        super(context);
    }

    public ChangeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.ChangeButton);
        onText = arr.getString(R.styleable.ChangeButton_onText);
        offText = arr.getString(R.styleable.ChangeButton_offText);
        mIsChecked = arr.getBoolean(R.styleable.ChangeButton_checked, false);
        syncTextState();
        arr.recycle();
        setOnClickListener(this);
    }

    /**
     * 同步text状态
     */
    private void syncTextState() {
        if (mIsChecked && onText != null) {
            setText(onText);
            setBackgroundResource(R.drawable.md_checked2);
            mIsChecked = false;
        } else if (!mIsChecked && offText != null) {
            setText(offText);
            setBackgroundResource(R.drawable.md_checked);
            mIsChecked = true;
        }
    }

    @Override
    public void onClick(View v) {
        syncTextState();
    }
}
