package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class MainAdapter extends BaseCommAdapter<Record> {

    public MainAdapter(Context context, List<Record> datas) {
        super(context, datas);
    }

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Record record;
        Drawable drawable;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        record = getDatas().get(position);
        holder.tvItemMainRecordID.setText(String.valueOf(record.getMedicalRecordNo()));
        holder.tvItemMainBirthday.setText(record.getBirth());
        if (record.getSex().equals("女")) {
            holder.tvItemMainName.setText(" "+record.getName());
            drawable = getContext().getResources().getDrawable(R.mipmap.ic_female36dp);
        } else {
            holder.tvItemMainName.setText(" "+record.getName());
            drawable = getContext().getResources().getDrawable(R.mipmap.ic_male36dp);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        holder.tvItemMainName.setCompoundDrawables(drawable, null, null, null);
        if (!TextUtils.isEmpty(record.getIllnessID())) {
            switch (record.getIllnessID()) {
                case "1":
                    holder.tvItemMainFirstDis.setText("糖尿病");
                    break;
                case "2":
                    holder.tvItemMainFirstDis.setText("甲状腺");
                    break;
                case "3":
                    holder.tvItemMainFirstDis.setText("PCOS");
                    break;
                case "9000":
                    holder.tvItemMainFirstDis.setText("其他疾病");
                    break;
            }
        }

        holder.tvItemMainFirstHis.setText(TextUtils.isEmpty(record.getHospitalName()) ? "" : record.getHospitalName());
        holder.tvItemMainFirstDoc.setText(TextUtils.isEmpty(record.getUSERREALNAME()) ? "" : record.getUSERREALNAME());
        return convertView;
    }

    /**
     * 清除所有已有数据
     */
    public void clearAll() {
        setDatas(new ArrayList<Record>());
        notifyDataSetChanged();
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_main.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_main_name)
        TextView tvItemMainName;
        @Bind(R.id.tv_item_main_birthday)
        TextView tvItemMainBirthday;
        @Bind(R.id.tv_item_main_first_his)
        TextView tvItemMainFirstHis;
        @Bind(R.id.tv_item_main_first_dis)
        TextView tvItemMainFirstDis;
        @Bind(R.id.tv_item_main_first_doc)
        TextView tvItemMainFirstDoc;
        @Bind(R.id.tv_item_main_recordID)
        TextView tvItemMainRecordID;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
