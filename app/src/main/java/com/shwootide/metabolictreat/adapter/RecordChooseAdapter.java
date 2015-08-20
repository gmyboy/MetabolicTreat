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
 * Created by Administrator on 2015/8/19.
 */
public class RecordChooseAdapter extends BaseAdapter {
    private List<Record> datas = new ArrayList<>();
    private Context context;


    public RecordChooseAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 100; i++) {
            Record record = new Record();
            record.setDiagnosisTime("2015年3月6日" + i);
            record.setDiagnosisCount(String.valueOf(i));
            record.setDoctor("doctor" + i);
            datas.add(record);
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Record record;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recordchoose, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        record = datas.get(position);
        holder.tvItemDiagnosistime.setText(record.getDiagnosisTime());
        holder.tvItemDiagnosiscount.setText(record.getDiagnosisCount());
        holder.tvItemDoctor.setText(record.getDoctor());
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