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
 * 主页面查询结果适配器
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class MainAdapter extends BaseAdapter {
    private List<Record> datas = new ArrayList<>();
    private Context context;


    public MainAdapter(Context context, List<Record> datas) {
        this.context = context;
        this.datas = datas;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        record = datas.get(position);
        holder.tvItemMainRecordID.setText(String.valueOf(record.getId()));
        holder.tvItemMainBirthday.setText(record.getBirthday());
        holder.tvItemMainName.setText(record.getName());
        holder.tvItemMainSex.setText(record.getSex());
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_main.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    class ViewHolder {
        @Bind(R.id.tv_item_main_name)
        TextView tvItemMainName;
        @Bind(R.id.tv_item_main_sex)
        TextView tvItemMainSex;
        @Bind(R.id.tv_item_main_birthday)
        TextView tvItemMainBirthday;
        @Bind(R.id.tv_item_main_recordID)
        TextView tvItemMainRecordID;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
