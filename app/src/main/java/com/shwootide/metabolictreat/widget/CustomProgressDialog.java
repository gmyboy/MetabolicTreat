package com.shwootide.metabolictreat.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;


/**
 * 自定义progressdialog
 *
 * @author GMY
 * @mail 2275964276@qq.com
 * @date 2015年6月2日
 */
public class CustomProgressDialog extends Dialog {

    private static CustomProgressDialog mCustomProgressDialog = null;

    public CustomProgressDialog(Context context) {
        super(context);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * @param context
     * @return
     * @Title showCancelable
     * @Description 显示进度条
     */
    public static CustomProgressDialog showCancelable(Context context) {
        return showCancelable(context, null);
    }

    /**
     * @param context
     * @return
     * @Title show
     * @Description 显示进度条
     */
    public static CustomProgressDialog showCancelable(Context context, String message) {
        return show(context, null, message, false);
    }

    /**
     * @param context
     * @return
     * @Title show
     * @Description 显示进度条
     */
    public static CustomProgressDialog showCancelable(Context context,
                                                      int message) {
        return show(context, null, context.getString(message), false);
    }

    /**
     * @param context
     * @return
     * @Title show
     * @Description 显示进度条
     */
    public static CustomProgressDialog show(Context context) {
        return show(context, null);
    }

    /**
     * @param context
     * @return
     * @Title show
     * @Description 显示进度条
     */
    public static CustomProgressDialog show(Context context, String message) {
        return show(context, message, true);
    }

    /**
     * @param context
     * @return
     * @Title show
     * @Description 显示进度条
     */
    public static CustomProgressDialog show(Context context, String message,
                                            boolean cancelable) {
        return show(context, null, message, cancelable);
    }

    /**
     * @param context
     * @return
     * @Title show
     * @Description 显示进度条
     */
    public static CustomProgressDialog show(Context context, String title, String message, boolean cancelable) {
        CustomProgressDialog dialog = createDialog(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(cancelable);
        ImageView progressBar = (ImageView) mCustomProgressDialog.findViewById(R.id.pb_one);
        dialog.show();
        return dialog;
    }

    public static CustomProgressDialog createDialog(Context context) {
        mCustomProgressDialog = new CustomProgressDialog(context, R.style.ProgressDialog);
        mCustomProgressDialog.setContentView(R.layout.dialog_progress);
        mCustomProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        //隐藏输入框
        mCustomProgressDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return mCustomProgressDialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mCustomProgressDialog == null) {
            return;
        }
        View progressBar = (ImageView) mCustomProgressDialog.findViewById(R.id.pb_one);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * [Summary] setTitile 标题
     *
     * @param strTitle
     * @return
     */
    public CustomProgressDialog setTitile(String strTitle) {
        return mCustomProgressDialog;
    }

    /**
     * [Summary] setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public CustomProgressDialog setMessage(String strMessage) {
        if (mCustomProgressDialog == null) {
            return mCustomProgressDialog;
        }
        TextView tvMsg = (TextView) mCustomProgressDialog.findViewById(R.id.tv_one);
        if (tvMsg != null) {
            if (null != strMessage) {
                tvMsg.setText(strMessage);
                tvMsg.setVisibility(View.VISIBLE);
            } else
                tvMsg.setVisibility(View.GONE);
        }
        return mCustomProgressDialog;
    }
}