package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/19.
 */
public class DepartmentChooseActivity extends BaseActivity {
    @Bind(R.id.btn_departmentchoose_dm)
    Button btnDepartmentchooseNfm;

    @OnClick(R.id.btn_departmentchoose_dm)
    void choose() {
        new MaterialDialog.Builder(mContext)
                .title(R.string.departmentchoose_Diagnosis)
                .items(R.array.Diagnosis)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        Intent intent = new Intent();
                        if (which == 2) {
                            intent.setClass(mContext, RecordChooseActivity.class);
                        } else {
                            intent.setClass(mContext, RecordFullActivity.class);
                        }
                        startActivity(intent);
                    }
                })
                .positiveText(android.R.string.cancel)
                .show();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activcity_departmentchoose);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
