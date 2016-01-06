package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.LifeGuiding;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 个人史适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class TellChuFangAdapter extends BaseCommAdapter<LifeGuiding> {
    private int i;//用来区分是运动处方还是饮食处方

    public TellChuFangAdapter(Context context, List<LifeGuiding> datas, int i) {
        super(context, datas);
        this.i = i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LifeGuiding item;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tellchufang, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        item = getDatas().get(position);
        if (i == 1) {
            if (item.getType().equals("饮食处方"))
                holder.tvItemTellchufang.setText(item.getLifeGuidingName());
        } else if (i == 2) {
            if (item.getType().equals("运动处方"))
                holder.tvItemTellchufang.setText(item.getLifeGuidingName());
        } else {
        }
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_tellchufang.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_tellchufang)
        TextView tvItemTellchufang;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
