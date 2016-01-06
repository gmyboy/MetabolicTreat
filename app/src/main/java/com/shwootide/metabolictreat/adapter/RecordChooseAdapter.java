package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 疾病选择适配器
 * Created by GMY on 2015/8/25 09:20.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordChooseAdapter extends BaseCommAdapter<MedicalRecord> {
    public static final String SORT_FIELDS = "getSequenceNumber";//排序的字段
    private String type = null;//排序的顺序,默认升序

    public RecordChooseAdapter(Context context, List<MedicalRecord> datas) {
        super(context, datas);
        CommonUtil.sortList(datas, SORT_FIELDS, type);
    }

    public RecordChooseAdapter(Context context) {
        super(context);
    }

    public void changeType() {
        if (getType() != null && "desc".equals(getType())) {
            setType("");
        } else {
            setType(Config.SORT_DESC);
        }
        CommonUtil.sortList(getDatas(), SORT_FIELDS, type);
        notifyDataSetChanged();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MedicalRecord record;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recordchoose, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        record = getDatas().get(position);
        holder.tvItemDiagnosistime.setText(CommonUtil.parseStr(record.getRecordDate()));
        holder.tvItemDiagnosiscount.setText(record.getSequenceNumber());
        holder.tvItemDoctor.setText(record.getUSERREALNAME());
        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_recordchoose.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_diagnosistime)
        TextView tvItemDiagnosistime;
        @Bind(R.id.tv_item_diagnosiscount)
        TextView tvItemDiagnosiscount;
        @Bind(R.id.tv_item_doctor)
        TextView tvItemDoctor;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
