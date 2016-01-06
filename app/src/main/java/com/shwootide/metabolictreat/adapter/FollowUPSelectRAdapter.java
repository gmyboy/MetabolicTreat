package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.shwootide.metabolictreat.entity.FollowSelect;
import com.shwootide.metabolictreat.utils.GLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GMY on 2015/11/24 11:06.
 * Contact me via email gmyboy@qq.com.
 */
public class FollowUPSelectRAdapter extends BaseAdapter {
    public static int MAX_ITEM_COUNT = 3;
    // 填充数据的list
    private List<FollowSelect> datas;
    // 用来控制CheckBox的选中状况
    private static SparseBooleanArray checkedPositions;
    // 上下文
    private Context context;

    public FollowUPSelectRAdapter(Context context, List<FollowSelect> datas) {
        this.datas = datas;
        this.context = context;
        checkedPositions = new SparseBooleanArray();
    }

    public static SparseBooleanArray getCheckedPositions() {
        return checkedPositions;
    }

    public static void setCheckedPositions(SparseBooleanArray checkedPositions) {
        FollowUPSelectRAdapter.checkedPositions = checkedPositions;
    }

    public void resetAll() {
        for (int i = 0; i < checkedPositions.size(); i++) {
            checkedPositions.put(i, false);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public FollowSelect getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
        }
        ((CheckedTextView) convertView).setText(getItem(position).getName());
        boolean isChecked = checkedPositions.get(position);
        ((CheckedTextView) convertView).setChecked(isChecked);
        return convertView;
    }

    /**
     * Update the list of the positions checked and update the view
     *
     * @param position The position of the view which has been checked
     */
    public void setChecked(int position) {
        boolean ischeck = checkedPositions.get(position);
        checkedPositions.put(position, !ischeck);
        notifyDataSetChanged();
//        if (ischeck) {
//            checkedPositions.put(position, !ischeck);
//        notifyDataSetChanged();
//        } else {
//            if (getCheckedItemsCount() < MAX_ITEM_COUNT) {
//                checkedPositions.put(position, !ischeck);
//                notifyDataSetChanged();
//            }
//        }
    }

    public void setChecked(int position, boolean ischeck) {
        checkedPositions.put(position, ischeck);
        notifyDataSetChanged();
    }

    /**
     * get the checked item count of the list
     *
     * @return
     */
    public int getCheckedItemsCount() {
        int count = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (checkedPositions.get(i)) count++;
        }
        GLog.w("右边选中的item总数是：" + count);
        return count;
    }

    /**
     * get the checked count of item id
     *
     * @return
     */
    public List<Integer> getCheckedItemIds() {
        List<Integer> count = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (checkedPositions.get(i)) {
                count.add(i);
            }
        }
        return count;
    }

    /**
     * get the checked item count of the list
     *
     * @return
     */
    public List<FollowSelect> getCheckedItems() {
        List<FollowSelect> count = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (checkedPositions.get(i)) {
                count.add(getItem(i));
                GLog.w("右边选中的是第" + i + "个item：" + getItem(i).getName());
            }
        }
        return count;
    }
}
