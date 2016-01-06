package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.shwootide.metabolictreat.FollowUpActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.FollowSelect;
import com.shwootide.metabolictreat.entity.MedicalRecordCheckup;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.entity.SequenceNum;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.GLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GMY on 2015/11/24 11:06.
 * Contact me via email gmyboy@qq.com.
 */
public class FollowUPAdapter extends BaseCommAdapter<MedicalRecordCheckup> {
    private List<FollowSelect> checkedItems;

    public FollowUPAdapter(Context context, List<MedicalRecordCheckup> datas) {
        super(context, datas);
    }

    public FollowUPAdapter(Context context) {
        super(context);
    }

    public FollowUPAdapter(Context context, List<MedicalRecordCheckup> datas, List<FollowSelect> checkedItems) {
        super(context, datas);
        this.checkedItems = checkedItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MedicalRecordCheckup item;
        int size = 0;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_follow, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        item = getDatas().get(position);
        size = checkedItems.size();
        holder.tvItem1.setText(item.getSequenceNumber());
        if (size == 1) {
            holder.tvItem2.setText(CommonUtil.reflect(item, checkedItems.get(0).getTitle()));
        } else if (size == 2) {
            holder.tvItem2.setText(CommonUtil.reflect(item, checkedItems.get(0).getTitle()));
            holder.tvItem3.setText(CommonUtil.reflect(item, checkedItems.get(1).getTitle()));
        } else if (size == 3) {
            holder.tvItem2.setText(CommonUtil.reflect(item, checkedItems.get(0).getTitle()));
            holder.tvItem3.setText(CommonUtil.reflect(item, checkedItems.get(1).getTitle()));
            holder.tvItem4.setText(CommonUtil.reflect(item, checkedItems.get(2).getTitle()));
        } else {
            holder.tvItem2.setText("-");
            holder.tvItem3.setText("-");
            holder.tvItem4.setText("-");
        }
        return convertView;
    }

    public void clearAll() {
        setDatas(new ArrayList<MedicalRecordCheckup>());
        notifyDataSetChanged();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_follow.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    class ViewHolder {
        @Bind(R.id.tv_follow_item1)
        TextView tvItem1;
        @Bind(R.id.tv_follow_item2)
        TextView tvItem2;
        @Bind(R.id.tv_follow_item3)
        TextView tvItem3;
        @Bind(R.id.tv_follow_item4)
        TextView tvItem4;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
