package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.LabCheck;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * 血糖监测适配器
 * Created by GMY on 2015/9/18 16:41.
 * Contact me via email gmyboy@qq.com.
 */
public class LabCheckAdapter extends BaseCommAdapter<LabCheck> implements StickyListHeadersAdapter {

    public LabCheckAdapter(Context context, List<LabCheck> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LabCheck mHistoryNow1;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_labcheck, parent, false);
            holder = new ViewHolder();
            holder.tvLabItem1 = (TextView) convertView.findViewById(R.id.tv_lab_item_1);
            holder.etLabItem2 = (EditText) convertView.findViewById(R.id.et_lab_item_2);
            holder.tvLabItem3 = (TextView) convertView.findViewById(R.id.tv_lab_item_3);
            convertView.setTag(holder);
        }
        mHistoryNow1 = getDatas().get(position);
        holder.tvLabItem1.setText(mHistoryNow1.getName());
        holder.etLabItem2.setText(String.valueOf(mHistoryNow1.getScore()));
        holder.tvLabItem3.setText(mHistoryNow1.getReference());
        return convertView;
    }

    @Override
    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        HeaderViewHolder headerholder;
        if (view != null) {
            headerholder = (HeaderViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_labcheck_list_header, viewGroup, false);
            headerholder = new HeaderViewHolder();
            headerholder.tvHeader = (TextView) view.findViewById(R.id.tv_dialog_list_header);
            view.setTag(headerholder);
        }
        headerholder.tvHeader.setText(CommonUtil.replaceBlank(getDatas().get(i).getNametype2()));
        return view;
    }

    @Override
    public long getHeaderId(int i) {
        return Long.parseLong(getDatas().get(i).getNametype2id());
    }

    static class ViewHolder {
        TextView tvLabItem1;
        EditText etLabItem2;
        TextView tvLabItem3;
    }

    static class HeaderViewHolder {
        TextView tvHeader;
    }
}
