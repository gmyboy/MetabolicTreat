package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.RecordFullActivity;
import com.shwootide.metabolictreat.entity.MHistory;
import com.shwootide.metabolictreat.entity.MHistory_Now;
import com.shwootide.metabolictreat.entity.UserInfo;
import com.shwootide.metabolictreat.fragment.RecordNowFragment;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.widget.ChangeButton;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 现病史适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordnowAdapter extends BaseCommAdapter<MHistory_Now> {
    private List<MHistory> datas_submit = new ArrayList<>();//解析出所有要提交的数据
    private UserInfo userInfo;

    public RecordnowAdapter(Context context, List<MHistory_Now> datas, UserInfo userInfo) {
        super(context, datas);
        setIsTwoLine(true);
        this.userInfo = userInfo;
    }

    public List<MHistory> getSubmitDatas() {
        return datas_submit;
    }

    public void addSubmitDatas(MHistory mHistory) {
        datas_submit.add(mHistory);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MHistory_Now mHistoryNow1, mHistoryNow2;
        MHistory mHistory1, mHistory2;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recordnow, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        mHistoryNow1 = getDatas().get(position * 2);
        mHistory1 = new MHistory();
        setupHistory(mHistory1);
        holder.tvItemRecordnowType1.setText(mHistoryNow1.getName());
        mHistory1.setMHTypeID(mHistoryNow1.getId());

        if (mHistoryNow1.getEditMode().equals("0")) {
            holder.llRecordnowType10.setVisibility(View.VISIBLE);
            holder.llRecordnowType11.setVisibility(View.GONE);
            holder.llRecordnowType12.setVisibility(View.GONE);
            holder.tvRecordnowNianTip1.setText(mHistoryNow1.getReference());
//            holder.etRecordnowNian1.addTextChangedListener(new MyTextChangeListener(mHistory1));
        } else if (mHistoryNow1.getEditMode().equals("1")) {
            holder.llRecordnowType11.setVisibility(View.VISIBLE);
            holder.llRecordnowType10.setVisibility(View.GONE);
            holder.llRecordnowType12.setVisibility(View.GONE);
            //          holder.cbRecordnowType1.setOnClickListener(new MyClickListener(mHistory1));
        } else if (mHistoryNow1.getEditMode().equals("2") || mHistoryNow1.getEditMode().equals("3")) {
            holder.llRecordnowType12.setVisibility(View.VISIBLE);
            holder.llRecordnowType10.setVisibility(View.GONE);
            holder.llRecordnowType11.setVisibility(View.GONE);
            holder.nsRecordnowType1.attachDataSource(CommonUtil.splitStr(mHistoryNow1.getReference()));
            //         holder.nsRecordnowType1.setOnItemSelectedListener(new MyItemSelectedListener(mHistory1));
        }
        addSubmitDatas(mHistory1);
        mHistory1 = null;
        if (position * 2 + 1 < getDatas().size()) {
            mHistoryNow2 = getDatas().get(position * 2 + 1);
            mHistory2 = new MHistory();
            setupHistory(mHistory2);
            holder.llItem2.setVisibility(View.VISIBLE);
            holder.tvItemRecordnowType2.setText(mHistoryNow2.getName());
            if (mHistoryNow2.getEditMode().equals("0")) {
                holder.llRecordnowType20.setVisibility(View.VISIBLE);
                holder.llRecordnowType21.setVisibility(View.GONE);
                holder.llRecordnowType22.setVisibility(View.GONE);
                holder.tvRecordnowNianTip2.setText(mHistoryNow2.getReference());
                //     holder.etRecordnowNian2.addTextChangedListener(new MyTextChangeListener(mHistory2));
            } else if (mHistoryNow2.getEditMode().equals("1")) {
                holder.llRecordnowType21.setVisibility(View.VISIBLE);
                holder.llRecordnowType20.setVisibility(View.GONE);
                holder.llRecordnowType22.setVisibility(View.GONE);
                //     holder.cbRecordnowType2.setOnClickListener(new MyClickListener(mHistory2));
            } else if (mHistoryNow2.getEditMode().equals("2") || mHistoryNow2.getEditMode().equals("3")) {
                holder.llRecordnowType22.setVisibility(View.VISIBLE);
                holder.llRecordnowType20.setVisibility(View.GONE);
                holder.llRecordnowType21.setVisibility(View.GONE);
                holder.nsRecordnowType2.attachDataSource(CommonUtil.splitStr(mHistoryNow2.getReference()));
                //     holder.nsRecordnowType2.setOnItemSelectedListener(new MyItemSelectedListener(mHistory2));
            }
            addSubmitDatas(mHistory2);
            mHistory2 = null;
        }else {
            holder.llItem2.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private void setupHistory(MHistory mHistory) {
        mHistory.setType(Config.TYPE_RECORD_NOW);
        mHistory.setInputUser(userInfo.getUserName());
        mHistory.setBz("");
        mHistory.setMedicalRecordID(RecordFullActivity.MedicalRecordID);
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_recordnow.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_recordnow_type1)
        TextView tvItemRecordnowType1;
        @Bind(R.id.et_recordnow_nian1)
        EditText etRecordnowNian1;
        @Bind(R.id.tv_recordnow_nian_tip1)
        TextView tvRecordnowNianTip1;
        @Bind(R.id.ll_recordnow_type1_0)
        LinearLayout llRecordnowType10;
        @Bind(R.id.cb_recordnow_type1)
        ChangeButton cbRecordnowType1;
        @Bind(R.id.ll_recordnow_type1_1)
        LinearLayout llRecordnowType11;
        @Bind(R.id.ns_recordnow_type1)
        NiceSpinner nsRecordnowType1;
        @Bind(R.id.ll_recordnow_type1_2)
        LinearLayout llRecordnowType12;
        @Bind(R.id.tv_item_recordnow_type2)
        TextView tvItemRecordnowType2;
        @Bind(R.id.et_recordnow_nian2)
        EditText etRecordnowNian2;
        @Bind(R.id.tv_recordnow_nian_tip2)
        TextView tvRecordnowNianTip2;
        @Bind(R.id.ll_recordnow_type2_0)
        LinearLayout llRecordnowType20;
        @Bind(R.id.cb_recordnow_type2)
        ChangeButton cbRecordnowType2;
        @Bind(R.id.ll_recordnow_type2_1)
        LinearLayout llRecordnowType21;
        @Bind(R.id.ns_recordnow_type2)
        NiceSpinner nsRecordnowType2;
        @Bind(R.id.ll_recordnow_type2_2)
        LinearLayout llRecordnowType22;
        @Bind(R.id.ll_item2)
        LinearLayout llItem2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 编辑框文字该表监听器
     */
    public class MyTextChangeListener implements TextWatcher {
        private MHistory mHistory;

        public MyTextChangeListener(MHistory mHistory) {
            this.mHistory = mHistory;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mHistory.setCheckValues1(s.toString());
        }
    }

    /**
     * 按钮点击实时获得文字内容
     */
    public class MyClickListener implements View.OnClickListener {
        private MHistory mHistory;

        public MyClickListener(MHistory mHistory) {
            this.mHistory = mHistory;
        }

        @Override
        public void onClick(View v) {

            mHistory.setCheckValues1(((Button) v).getText().toString());
        }
    }

    /**
     * 下拉选项选中监听
     */
    public class MyItemSelectedListener implements AdapterView.OnItemSelectedListener {
        private MHistory mHistory;

        public MyItemSelectedListener(MHistory mHistory) {
            this.mHistory = mHistory;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mHistory.setCheckValues1(parent.getAdapter().getItem(position).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
