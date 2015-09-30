package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.Record;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 疾病选择适配器
 * Created by GMY on 2015/8/25 09:20.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordChooseAdapter extends BaseCommAdapter<Record> {

    public RecordChooseAdapter(Context context, List<Record> datas) {
        super(context, datas);
    }

    public RecordChooseAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Record record;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recordchoose, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        record = getDatas().get(position);
//        holder.tvItemDiagnosistime.setText(record.getDiagnosisTime());
//        holder.tvItemDiagnosiscount.setText(record.getDiagnosisCount());
//        holder.tvItemDoctor.setText(record.getDoctor());
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
