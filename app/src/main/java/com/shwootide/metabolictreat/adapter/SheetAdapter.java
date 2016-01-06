package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.Diagnosis_Submit;
import com.shwootide.metabolictreat.entity.Sheet_Submit;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 诊断适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class SheetAdapter extends BaseAdapter {
    private Context context;
    private List<Sheet_Submit> datas = new ArrayList<>();

    public SheetAdapter(Context context) {
        this.context = context;
    }

    public SheetAdapter(Context context, List<Sheet_Submit> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Sheet_Submit getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Sheet_Submit item;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_diagnosis, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        item = datas.get(position);
        holder.tvItemDiagnosis.setText(item.getCheckValues());
        return convertView;
    }

    /**
     * 更新数据方法，如果没有则添加，如果有了就删除
     *
     * @param item
     */
    public void upate(Sheet_Submit item) {
        if (!iscontain(item)) {
            datas.add(item);
        }
        notifyDataSetChanged();
    }

    private boolean iscontain(Sheet_Submit item) {
        for (Sheet_Submit d : datas) {
            if (d.getNametype3().equals(item.getNametype3())) {
                datas.remove(d);
                return true;
            }
        }
        return false;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_diagnosis.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_diagnosis)
        TextView tvItemDiagnosis;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
