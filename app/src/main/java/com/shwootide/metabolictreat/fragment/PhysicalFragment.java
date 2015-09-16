package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.utils.CommonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 体格检查
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class PhysicalFragment extends Fragment implements TextWatcher {

    @Bind(R.id.et_physical_tz)
    EditText etPhysicalTz;
    @Bind(R.id.et_physical_sg)
    EditText etPhysicalSg;
    @Bind(R.id.tv_physical_bmi)
    TextView tvPhysicalBmi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_physical, null);
        ButterKnife.bind(this, view);
        etPhysicalSg.addTextChangedListener(this);
        etPhysicalTz.addTextChangedListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String str_tz = etPhysicalTz.getText().toString().trim();
        String str_sg = etPhysicalSg.getText().toString().trim();
        if (!TextUtils.isEmpty(str_tz) && !TextUtils.isEmpty(str_sg)) {
            tvPhysicalBmi.setText(String.valueOf(CommonUtil.getBMI(str_tz, str_sg)));
        }
    }
}
