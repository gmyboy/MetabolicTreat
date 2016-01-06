package com.shwootide.metabolictreat.widget.nicespinner;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.utils.GLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author angelo.marchesin
 */
@SuppressWarnings("unused")
public class NiceSpinner extends TextView {

    private static final int MAX_LEVEL = 10000;
    private static final int DEFAULT_ELEVATION = 16;
    private static final String INSTANCE_STATE = "instance_state";
    private static final String SELECTED_INDEX = "selected_index";
    private static final String IS_POPUP_SHOWING = "is_popup_showing";

    private int mSelectedIndex;
    private Drawable mDrawable;
    private PopupWindow mPopup;
    private ListView mListView;
    private NiceSpinnerBaseAdapter mAdapter;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private boolean mHideArrow;
    private boolean mArrowUp;
    private CharSequence[] mDefaultData;
    private int mDefaultDataId = 0;
    private Drawable txtBackground;//文字背景

    @SuppressWarnings("ConstantConditions")
    public NiceSpinner(Context context) {
        super(context);
        init(context, null, 0);
    }

    public NiceSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public NiceSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(SELECTED_INDEX, mSelectedIndex);

        if (mPopup != null) {
            bundle.putBoolean(IS_POPUP_SHOWING, mPopup.isShowing());
            dismissDropDown();
        }

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable savedState) {
        if (savedState instanceof Bundle) {
            Bundle bundle = (Bundle) savedState;

            mSelectedIndex = bundle.getInt(SELECTED_INDEX);

            if (mAdapter != null) {
                setText(mAdapter.getItemInDataset(mSelectedIndex).toString());
                mAdapter.notifyItemSelected(mSelectedIndex);
            }

            if (bundle.getBoolean(IS_POPUP_SHOWING)) {
                if (mPopup != null) {
                    // Post the show request into the looper to avoid bad token exception
                    post(new Runnable() {
                        @Override
                        public void run() {
                            showDropDown();
                        }
                    });
                }
            }

            savedState = bundle.getParcelable(INSTANCE_STATE);
        }

        super.onRestoreInstanceState(savedState);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        Resources resources = getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NiceSpinner, defStyleAttr, 0);
        int defaultPadding = resources.getDimensionPixelSize(R.dimen.one_and_a_half_grid_unit);

        setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER);
        setPadding(resources.getDimensionPixelSize(R.dimen.three_grid_unit), defaultPadding, defaultPadding, defaultPadding);
        setClickable(true);
        setBackgroundResource(R.drawable.selector);

        mListView = new ListView(context);
        // Set the spinner's id into the listview to make it pretend to be the right parent in
        // onItemClick
        mListView.setId(getId());
        mListView.setDivider(null);
        mListView.setItemsCanFocus(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= mSelectedIndex && position < mAdapter.getCount()) {
                    position++;
                }

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(parent, view, position, id);
                }

                if (mOnItemSelectedListener != null) {
                    mOnItemSelectedListener.onItemSelected(parent, view, position, id);
                }

                mAdapter.notifyItemSelected(position);
                mSelectedIndex = position;
                setText(mAdapter.getItemInDataset(position).toString());
                dismissDropDown();
            }
        });

        mPopup = new PopupWindow(context);
        mPopup.setContentView(mListView);
        mPopup.setOutsideTouchable(true);
        mPopup.setFocusable(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPopup.setElevation(DEFAULT_ELEVATION);
            mPopup.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.spinner_drawable));
        } else {
            mPopup.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drop_down_shadow));
        }

        mPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!mHideArrow) {
                    animateArrow(false);
                }
            }
        });

        mHideArrow = typedArray.getBoolean(R.styleable.NiceSpinner_hideArrow, false);
        if (!mHideArrow) {
            Drawable basicDrawable = ContextCompat.getDrawable(context, R.drawable.arrow);
            int resId = typedArray.getColor(R.styleable.NiceSpinner_arrowTint, -1);

            if (basicDrawable != null) {
                mDrawable = DrawableCompat.wrap(basicDrawable);

                if (resId != -1) {
                    DrawableCompat.setTint(mDrawable, resId);
                }
            }
            setCompoundDrawablesWithIntrinsicBounds(null, null, mDrawable, null);
        }

        txtBackground = typedArray.getDrawable(R.styleable.NiceSpinner_textBackground);
        if (txtBackground != null) {
            setBackgroundDrawable(txtBackground);
        }
        mArrowUp = typedArray.getBoolean(R.styleable.NiceSpinner_arrowUp, false);

        mDefaultDataId = typedArray.getResourceId(R.styleable.NiceSpinner_niceEntries, R.array.NianYue);
        if (mDefaultDataId != 0) {
            mDefaultData = context.getResources().getStringArray(mDefaultDataId);
            if (mDefaultData != null) {
                attachDataSource(Arrays.asList(mDefaultData));
            }
        }
        typedArray.recycle();
    }

    public String getSelectedIndex() {
        return String.valueOf(mSelectedIndex);
    }

    /**
     * Set the default spinner item using its index
     *
     * @param position the item's position
     */
    public void setSelectedIndex(int position) {
        if (mAdapter != null) {
            if (position >= 0 && position <= mAdapter.getCount()) {
                mAdapter.notifyItemSelected(position);
                mSelectedIndex = position;
                setText(mAdapter.getItemInDataset(position).toString());
                if (mOnItemSelectedListener != null) {
                    mOnItemSelectedListener.onItemSelected(mListView, mListView.getSelectedView(), position, mListView.getSelectedItemId());
                }
            } else {
                throw new IllegalArgumentException("Position must be lower than adapter count!");
            }
        }
    }

    /**
     * 获取选中item
     */
    public String getSelectedItem() {
        return String.valueOf(getAdapter().getItemInDataset(mSelectedIndex));
    }

    public void addOnItemClickListener(@NonNull AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(@NonNull AdapterView.OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public <T> void attachDataSource(@NonNull List<T> dataset) {
        if (dataset != null && dataset.size() != 0) {
            mAdapter = new NiceSpinnerAdapter<>(getContext(), dataset);
            setAdapterInternal(mAdapter);
        } else {
            dataset = new ArrayList<>();
            mAdapter = new NiceSpinnerAdapter<>(getContext(), dataset);
            setAdapterInternal(mAdapter);
        }
    }

    public void setAdapter(@NonNull ListAdapter adapter) {
        mAdapter = new NiceSpinnerAdapterWrapper(getContext(), adapter);
        setAdapterInternal(mAdapter);
    }

    public NiceSpinnerBaseAdapter getAdapter() {
        if (mAdapter != null)
            return mAdapter;
        else
            return null;
    }

    private void setAdapterInternal(@NonNull NiceSpinnerBaseAdapter adapter) {
        mListView.setAdapter(adapter);
        setText(adapter.getItemInDataset(mSelectedIndex).toString());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mPopup.setWidth(MeasureSpec.getSize(widthMeasureSpec));
        mPopup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (!mPopup.isShowing()) {
                showDropDown();
            } else {
                dismissDropDown();
            }
        }

        return super.onTouchEvent(event);
    }

    private void animateArrow(boolean shouldRotateUp) {
        int start = shouldRotateUp ? 0 : MAX_LEVEL;
        int end = shouldRotateUp ? MAX_LEVEL : 0;
        ObjectAnimator animator = ObjectAnimator.ofInt(mDrawable, "level", start, end);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.start();
    }

    public void dismissDropDown() {
        if (!mHideArrow) {
            animateArrow(false);
        }
        mPopup.dismiss();
    }

    public void showDropDown() {
        if (!mHideArrow) {
            animateArrow(true);
        }
        if (mArrowUp) {
            int[] arrayOfInt = new int[2];
            getLocationOnScreen(arrayOfInt);
            int x = arrayOfInt[0];
            int y = arrayOfInt[1];
            mPopup.showAtLocation(this, Gravity.NO_GRAVITY, x, y - getHeight());
        } else {
            mPopup.showAsDropDown(this);
        }
    }

    public void setTintColor(@ColorRes int resId) {
        if (mDrawable != null && !mHideArrow) {
            DrawableCompat.setTint(mDrawable, getResources().getColor(resId));
        }
    }
}
