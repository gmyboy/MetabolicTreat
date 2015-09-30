package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.MHistory_Past;
import com.shwootide.metabolictreat.entity.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GMY on 2015/9/23 13:51.
 * Contact me via email gmyboy@qq.com.
 */
public class TestAdapter extends BaseExpandableListAdapter {


    private List<MHistory_Past> datas;
    private Context context;
    private Set<String> group = new HashSet<>();
    private Map<String, Object> map;
    private String[] groups = new String[]{"糖尿病", "糖尿病微血管并发症"};
    private String[] items1 = new String[]{"糖尿病", "1型糖尿病", "2型糖尿病", "妊娠糖尿病"};
    private String[] items2 = new String[]{"糖尿病微血管并发症", "糖尿病肾病", "糖尿病肾病III期", "糖尿病肾病IV期"};
    List list;

    public TestAdapter(Context context, List<MHistory_Past> datas) {
        this.context = context;
        this.datas = datas;
        for (MHistory_Past past : datas) {
            group.add(past.getMhtype1Name());
        }
        list = new ArrayList(group);
    }


    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0 || groupPosition == 1)
            return 4;
        else
            return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (groupPosition == 0)
            return items1[childPosition];
        else
            return items2[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return Long.parseLong(datas.get(groupPosition).getMhtype1ID());
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        HeaderViewHolder headerholder;
        if (view != null) {
            headerholder = (HeaderViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_dialog_list_header, parent, false);
            headerholder = new HeaderViewHolder();
            headerholder.tvHeader = (TextView) view.findViewById(R.id.tv_dialog_list_header);
            view.setTag(headerholder);
        }
        headerholder.tvHeader.setText(String.valueOf(list.get(groupPosition)));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
            holder = new ViewHolder();
            holder.tvLabitem = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        }
        if (groupPosition == 0)
            holder.tvLabitem.setText(items1[childPosition]);
        else holder.tvLabitem.setText(items2[childPosition]);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
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
