package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.MHistory_Past;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * 既往史，家族史，诊断的dialog内容适配器
 * Created by GMY on 2015/9/12 11:36.
 * Contact me via email gmyboy@qq.com.
 */
public class StickyListAdapter extends BaseCommAdapter<MHistory_Past> implements StickyListHeadersAdapter {

    public StickyListAdapter(Context context, List<MHistory_Past> datas) {
       super(context, datas);
    }

    @Override
    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        HeaderViewHolder headerholder;
        if (view != null) {
            headerholder = (HeaderViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_dialog_list_header, viewGroup, false);
            headerholder = new HeaderViewHolder();
            headerholder.tvHeader = (TextView) view.findViewById(R.id.tv_dialog_list_header);
            view.setTag(headerholder);
        }
        headerholder.tvHeader.setText(getDatas().get(i).getIllnessName());
        return view;
    }

    @Override
    public long getHeaderId(int i) {
        return Long.parseLong(getDatas().get(i).getIllnessName());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
            holder = new ViewHolder();
            holder.tvLabitem = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        }
        holder.tvLabitem.setText(getDatas().get(position).getName());
        return convertView;
    }

    /**
     * public long[] getCheckedItemIds ()
     * Added in API level 8
     * Returns the set of checked items ids. The result is only valid if the choice mode has not been set to CHOICE_MODE_NONE and the adapter has stable IDs. (hasStableIds() == true)
     * Returns
     * A new array which contains the id of each checked item in the list.
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_labcheck.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    class ViewHolder {
        TextView tvLabitem;
    }

    class HeaderViewHolder {
        TextView tvHeader;
    }
}
