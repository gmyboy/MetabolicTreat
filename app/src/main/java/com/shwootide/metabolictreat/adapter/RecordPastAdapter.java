package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.MHistory_Now;
import com.shwootide.metabolictreat.entity.MHistory_Past;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.ChangeButton;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 既往史适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordPastAdapter extends BaseCommAdapter<MHistory_Past> {

    public RecordPastAdapter(Context context, List<MHistory_Past> datas) {
        super(context, datas);
        setIsTwoLine(true);
    }

    public RecordPastAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MHistory_Past mHistoryNow1, mHistoryNow2;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recordpast, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        mHistoryNow1 = getDatas().get(position * 2);
        holder.tvItemRecordnowType1.setText(mHistoryNow1.getName());
        if (mHistoryNow1.getEditMode().equals("0")) {
            holder.llRecordnowType10.setVisibility(View.VISIBLE);
            holder.llRecordnowType11.setVisibility(View.GONE);
            holder.llRecordnowType12.setVisibility(View.GONE);
            holder.tvRecordnowNianTip1.setText(mHistoryNow1.getReference());
        } else if (mHistoryNow1.getEditMode().equals("1")) {
            holder.llRecordnowType11.setVisibility(View.VISIBLE);
            holder.llRecordnowType10.setVisibility(View.GONE);
            holder.llRecordnowType12.setVisibility(View.GONE);
        } else if (mHistoryNow1.getEditMode().equals("2") || mHistoryNow1.getEditMode().equals("3")) {
            holder.llRecordnowType12.setVisibility(View.VISIBLE);
            holder.llRecordnowType10.setVisibility(View.GONE);
            holder.llRecordnowType11.setVisibility(View.GONE);
            holder.nsRecordnowType1.attachDataSource(CommonUtil.splitStr(mHistoryNow1.getReference()));
        }

        if (position * 2 + 1 < getDatas().size()) {
            mHistoryNow2 = getDatas().get(position * 2 + 1);
            holder.llItem2.setVisibility(View.VISIBLE);
            holder.tvItemRecordnowType2.setText(mHistoryNow2.getName());
            if (mHistoryNow2.getEditMode().equals("0")) {
                holder.llRecordnowType20.setVisibility(View.VISIBLE);
                holder.llRecordnowType21.setVisibility(View.GONE);
                holder.llRecordnowType22.setVisibility(View.GONE);
                holder.tvRecordnowNianTip2.setText(mHistoryNow2.getReference());
            } else if (mHistoryNow2.getEditMode().equals("1")) {
                holder.llRecordnowType21.setVisibility(View.VISIBLE);
                holder.llRecordnowType20.setVisibility(View.GONE);
                holder.llRecordnowType22.setVisibility(View.GONE);
            } else if (mHistoryNow2.getEditMode().equals("2") || mHistoryNow2.getEditMode().equals("3")) {
                holder.llRecordnowType22.setVisibility(View.VISIBLE);
                holder.llRecordnowType20.setVisibility(View.GONE);
                holder.llRecordnowType21.setVisibility(View.GONE);
                holder.nsRecordnowType2.attachDataSource(CommonUtil.splitStr(mHistoryNow2.getReference()));
            }
        }else {
            holder.llItem2.setVisibility(View.INVISIBLE);
        }
        return convertView;
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
}
