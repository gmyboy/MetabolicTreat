package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.IllnessSubmit;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 个人史适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordMedicineAdapter extends BaseCommAdapter<IllnessSubmit> {

    public RecordMedicineAdapter(Context context, List<IllnessSubmit> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        IllnessSubmit item;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recordmedicine, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        item = getDatas().get(position);
        holder.tvItemRecordmedicine1.setText(item.getMTypeParentName());
        holder.tvItemRecordmedicine2.setText(item.getMTTradeName());
        holder.tvItemRecordmedicine3.setText(item.getDosage());
        holder.tvItemRecordmedicine4.setText(item.getTimesaDay());
        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_recordmedicine.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_recordmedicine1)
        TextView tvItemRecordmedicine1;
        @Bind(R.id.tv_item_recordmedicine2)
        TextView tvItemRecordmedicine2;
        @Bind(R.id.tv_item_recordmedicine3)
        TextView tvItemRecordmedicine3;
        @Bind(R.id.tv_item_recordmedicine4)
        TextView tvItemRecordmedicine4;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
