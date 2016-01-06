package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.Diagnosis;
import com.shwootide.metabolictreat.entity.Diagnosis_Submit;
import com.shwootide.metabolictreat.entity.MHistory_Past;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 诊断适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisAdapter extends BaseAdapter {
    private Context context;
    private List<Diagnosis_Submit> datas = new ArrayList<>();

    public DiagnosisAdapter(Context context) {
        this.context = context;
    }

    public DiagnosisAdapter(Context context, List<Diagnosis_Submit> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Diagnosis_Submit getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Diagnosis_Submit item;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_diagnosis, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        item = datas.get(position);
        holder.tvItemDiagnosis.setText(item.getMinTypeName());
        return convertView;
    }

    /**
     * 更新数据方法，如果没有则添加，如果有了就删除
     *
     * @param item
     */
    public void upate(Diagnosis_Submit item) {
        if (!iscontain(item)) {
            datas.add(item);
        }
        notifyDataSetChanged();
    }

    private boolean iscontain(Diagnosis_Submit item) {
        for (Diagnosis_Submit d : datas) {
            if (d.getMinTypeName().equals(item.getMinTypeName())) {
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
