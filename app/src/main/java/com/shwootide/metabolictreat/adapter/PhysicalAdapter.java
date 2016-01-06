package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.Physical;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.ChangeButton;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 体格检查适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class PhysicalAdapter extends BaseCommAdapter<Physical> {

    public PhysicalAdapter(Context context, List<Physical> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Physical mHistoryNow1, mHistoryNow2;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_physical, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        mHistoryNow1 = getDatas().get(position * 2);
        holder.tvItemPhysicalName1.setText(mHistoryNow1.getName());
        if (mHistoryNow1.getEditMode().equals("0")) {
            holder.llItemPhysical11.setVisibility(View.VISIBLE);
            holder.llItemPhysical12.setVisibility(View.GONE);
            holder.llItemPhysical13.setVisibility(View.GONE);
            holder.etItemPhysicalScore1.setText(String.valueOf(mHistoryNow1.getScore()));
            holder.tvItemPhysicalRefrence1.setText(mHistoryNow1.getReference());
        } else if (mHistoryNow1.getEditMode().equals("1")) {
            holder.llItemPhysical13.setVisibility(View.VISIBLE);
            holder.llItemPhysical11.setVisibility(View.GONE);
            holder.llItemPhysical12.setVisibility(View.GONE);
        } else if (mHistoryNow1.getEditMode().equals("2") || mHistoryNow1.getEditMode().equals("3")) {
            holder.llItemPhysical12.setVisibility(View.VISIBLE);
            holder.llItemPhysical11.setVisibility(View.GONE);
            holder.llItemPhysical13.setVisibility(View.GONE);
            holder.nsItemPhysical1.attachDataSource(CommonUtil.splitStr(mHistoryNow1.getReference()));
        }

        if (position * 2 + 1 < getDatas().size()) {
            mHistoryNow2 = getDatas().get(position * 2 + 1);
            holder.llItem2.setVisibility(View.VISIBLE);
            holder.tvItemPhysicalName2.setText(mHistoryNow2.getName());
            if (mHistoryNow2.getEditMode().equals("0")) {
                holder.llItemPhysical21.setVisibility(View.VISIBLE);
                holder.llItemPhysical22.setVisibility(View.GONE);
                holder.llItemPhysical23.setVisibility(View.GONE);
                holder.etItemPhysicalScore2.setText(String.valueOf(mHistoryNow2.getScore()));
                holder.tvItemPhysicalRefrence2.setText(mHistoryNow2.getReference());
            } else if (mHistoryNow2.getEditMode().equals("1")) {
                holder.llItemPhysical23.setVisibility(View.VISIBLE);
                holder.llItemPhysical21.setVisibility(View.GONE);
                holder.llItemPhysical22.setVisibility(View.GONE);
            } else if (mHistoryNow2.getEditMode().equals("2") || mHistoryNow2.getEditMode().equals("3")) {
                holder.llItemPhysical22.setVisibility(View.VISIBLE);
                holder.llItemPhysical21.setVisibility(View.GONE);
                holder.llItemPhysical23.setVisibility(View.GONE);
                holder.nsItemPhysical2.attachDataSource(CommonUtil.splitStr(mHistoryNow2.getReference()));
            }
        } else {
            holder.llItem2.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_physical.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_physical_name1)
        TextView tvItemPhysicalName1;
        @Bind(R.id.et_item_physical_score1)
        EditText etItemPhysicalScore1;
        @Bind(R.id.tv_item_physical_refrence1)
        TextView tvItemPhysicalRefrence1;
        @Bind(R.id.ll_item_physical11)
        LinearLayout llItemPhysical11;
        @Bind(R.id.ns_item_physical1)
        NiceSpinner nsItemPhysical1;
        @Bind(R.id.ll_item_physical12)
        LinearLayout llItemPhysical12;
        @Bind(R.id.cb_item_physical1)
        ChangeButton cbItemPhysical1;
        @Bind(R.id.ll_item_physical13)
        LinearLayout llItemPhysical13;
        @Bind(R.id.tv_item_physical_name2)
        TextView tvItemPhysicalName2;
        @Bind(R.id.et_item_physical_score2)
        EditText etItemPhysicalScore2;
        @Bind(R.id.tv_item_physical_refrence2)
        TextView tvItemPhysicalRefrence2;
        @Bind(R.id.ll_item_physical21)
        LinearLayout llItemPhysical21;
        @Bind(R.id.ns_item_physical2)
        NiceSpinner nsItemPhysical2;
        @Bind(R.id.ll_item_physical22)
        LinearLayout llItemPhysical22;
        @Bind(R.id.cb_item_physical2)
        ChangeButton cbItemPhysical2;
        @Bind(R.id.ll_item_physical23)
        LinearLayout llItemPhysical23;
        @Bind(R.id.ll_item2)
        LinearLayout llItem2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
