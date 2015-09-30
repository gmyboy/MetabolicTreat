package com.shwootide.metabolictreat.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.DiagnosisActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.RecordPastAdapter;
import com.shwootide.metabolictreat.entity.MHistory_Past;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 家族史
 * Created by GMY on 2015/9/8 11:09.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordFamilyFragment extends BaseFragment {
    @Bind(R.id.ns_family_zhongliu)
    NiceSpinner nsFamilyZhongliu;
    @Bind(R.id.et_family_zhongliu)
    EditText etFamilyZhongliu;
    EditText etDialogFamilyZhongliu;
    //用于存放所有选中的item对应的数据
    private List<MHistory_Past> item_selected = new ArrayList<>();
    private RecordPastAdapter adapter;
    private String illnessid = "";

    public static RecordFamilyFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        RecordFamilyFragment fragment = new RecordFamilyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_recordfamily;
    }

    @Override
    public void certainSubmit() {
        Intent intent = new Intent(getActivity(), DiagnosisActivity.class);
        intent.putExtra("IllnessID", illnessid);
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = getArguments().getString("IllnessID");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nsFamilyZhongliu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    showDialog();
                } else {
                    etFamilyZhongliu.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void lazyLoad() {
        Map<String, String> params = new WeakHashMap<>();
//        new MutiFetcher(MHistory_Past[].class).fetch(getActivity(), "GetAllMHistoryTypeInfo", "正在加载...", params);
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("GetAllMHistoryTypeInfo") && isVisible) {
            if (event.getCode().equals("200")) {

            }
        }
    }

    private void showDialog() {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit, null);
        etDialogFamilyZhongliu = (EditText) v.findViewById(R.id.et_dialog_family);
        etDialogFamilyZhongliu.setText(etFamilyZhongliu.getText() == null ? "" : etFamilyZhongliu.getText());
        new MaterialDialog.Builder(getActivity())
                .customView(v, true)
                .positiveText(R.string.dialog_positive)
                .negativeText(android.R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        CommonUtil.hideSoftInputView(getActivity(),etDialogFamilyZhongliu);
                        etFamilyZhongliu.setText(etDialogFamilyZhongliu.getText() == null ? "" : etDialogFamilyZhongliu.getText());
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        CommonUtil.hideSoftInputView(getActivity(), etDialogFamilyZhongliu);
                    }
                }).show();
    }
}
