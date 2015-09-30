package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.MHistory_Past;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 既往史适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisAdapter extends BaseAdapter {
    private Context context;
    private List<MHistory_Past> datas=new ArrayList<>();

    public DiagnosisAdapter(Context context, List<MHistory_Past> datas) {
        this.context = context;
        this.datas = datas;
    }

    public DiagnosisAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size() / 3 + datas.size() % 3;
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
        MHistory_Past mHistoryNow1, mHistoryNow2,mHistoryNow3;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_diagnosis, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        mHistoryNow1 = datas.get(position * 3);
        holder.tvItemDiagnosis1.setText(mHistoryNow1.getName());
        if (position * 2 + 1 < datas.size()) {
            mHistoryNow2 = datas.get(position * 2 + 1);
            holder.tvItemDiagnosis2.setVisibility(View.VISIBLE);
            holder.tvItemDiagnosis2.setText(mHistoryNow2.getName());
        } else {
            holder.tvItemDiagnosis2.setVisibility(View.INVISIBLE);
        }
        if (position * 3 + 2 < datas.size()) {
            mHistoryNow3 = datas.get(position * 3 + 2);
            holder.tvItemDiagnosis3.setVisibility(View.VISIBLE);
            holder.tvItemDiagnosis3.setText(mHistoryNow3.getName());
        } else {
            holder.tvItemDiagnosis3.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_diagnosis.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_diagnosis1)
        TextView tvItemDiagnosis1;
        @Bind(R.id.tv_item_diagnosis2)
        TextView tvItemDiagnosis2;
        @Bind(R.id.tv_item_diagnosis3)
        TextView tvItemDiagnosis3;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
