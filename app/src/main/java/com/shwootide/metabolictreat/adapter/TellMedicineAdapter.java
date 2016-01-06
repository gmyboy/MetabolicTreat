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
public class TellMedicineAdapter extends BaseCommAdapter<IllnessSubmit> {

    public TellMedicineAdapter(Context context, List<IllnessSubmit> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        IllnessSubmit item;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tellmedicine, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        item = getDatas().get(position);
        holder.tvItemTellmedicine1.setText(item.getMTypeParentName());
        holder.tvItemTellmedicine2.setText(item.getMTTradeName());
        holder.tvItemTellmedicine3.setText(item.getDosage());
        holder.tvItemTellmedicine4.setText(item.getTimesaDay());
        holder.tvItemTellmedicine5.setText(item.getWay());
        //实际上是其他的
        holder.tvItemTellmedicine6.setText(item.getBox());
        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_tellmedicine.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_tellmedicine1)
        TextView tvItemTellmedicine1;
        @Bind(R.id.tv_item_tellmedicine2)
        TextView tvItemTellmedicine2;
        @Bind(R.id.tv_item_tellmedicine3)
        TextView tvItemTellmedicine3;
        @Bind(R.id.tv_item_tellmedicine4)
        TextView tvItemTellmedicine4;
        @Bind(R.id.tv_item_tellmedicine5)
        TextView tvItemTellmedicine5;
        @Bind(R.id.tv_item_tellmedicine6)
        TextView tvItemTellmedicine6;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
