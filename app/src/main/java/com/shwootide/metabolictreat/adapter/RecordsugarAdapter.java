package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.MHistory_sugar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 血糖监测适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordsugarAdapter extends BaseCommAdapter<MHistory_sugar> {

    public RecordsugarAdapter(Context context, List<MHistory_sugar> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MHistory_sugar mHistoryNow1;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recordsugar, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        mHistoryNow1 = getDatas().get(position);
        holder.tvItemSugarName.setText(mHistoryNow1.getName());
        holder.etItemSugarData.setText(String.valueOf(mHistoryNow1.getScore()));
        holder.tvItemSugarRefrence.setText(mHistoryNow1.getReference());
        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_recordsugar.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_sugar_name)
        TextView tvItemSugarName;
        @Bind(R.id.et_item_sugar_data)
        EditText etItemSugarData;
        @Bind(R.id.tv_item_sugar_refrence)
        TextView tvItemSugarRefrence;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
