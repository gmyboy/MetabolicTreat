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
    private OnCheckedChangeListener changeListener;

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

    public OnCheckedChangeListener getChangeListener() {
        return changeListener;
    }

    /**
     * 设置切换监听
     *
     * @param changeListener
     */
    public void setChangeListener(OnCheckedChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    /**
     * 获取当前选择状态  1选中   0未选中
     *
     * @return
     */
    public String getChecked() {
        return String.valueOf(mIsChecked);
    }

    /**
     * 设置当前选中未选中状态
     *
     * @param isChecked
     */
    public void setChecked(boolean isChecked) {
        mIsChecked = isChecked;
        syncTextState();
    }

    /**
     * 切换状态
     */
    private void syncTextState() {
        if (changeListener != null)
            changeListener.checkedChange(this, mIsChecked);
        if (mIsChecked && onText != null) {
            setText(onText);
            setBackgroundResource(R.drawable.md_checked2);
        } else if (!mIsChecked && offText != null) {
            setText(offText);
            setBackgroundResource(R.drawable.md_checked);
        }
    }

    @Override
    public void onClick(View v) {
        mIsChecked = !mIsChecked;
        syncTextState();
    }

    public interface OnCheckedChangeListener {
        void checkedChange(View v, boolean isChecked);
    }
}
