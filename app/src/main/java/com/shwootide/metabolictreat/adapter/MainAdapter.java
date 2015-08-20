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
public class MainAdapter extends BaseAdapter {
    private List<Record> datas = new ArrayList<>();
    private Context context;


    public MainAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 100; i++) {
            Record record = new Record();
            record.setId(String.valueOf(i));
            record.setBirthday("2015年3月6日" + i);
            record.setName("Name" + i);
            if (i % 2 == 0) record.setSex("男");
            else record.setSex("女");
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        record = datas.get(position);
        holder.tvItemMainRecordID.setText(record.getId());
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
