package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by GMY on 2015/9/12 11:36.
 * Contact me via email gmyboy@qq.com.
 */
public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private Context context;
    private List<Map<String, Object>> datas;
    private List<Integer> position;

    public StickyListAdapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
        Map<String, Object> map0 = new HashMap();
        map0.put("0", 0);
        map0.put("1", "白蛋白");
        map0.put("2", "34-54g/L");
        datas.add(map0);
        Map<String, Object> map1 = new HashMap();
        map1.put("0", 0);
        map1.put("1", "ALT");
        map1.put("2", "0-75U/L");
        datas.add(map1);
        Map<String, Object> map2 = new HashMap();
        map1.put("0", 0);
        map2.put("1", "AST");
        map2.put("2", "10-28 U/L");
        datas.add(map2);
        Map<String, Object> map3 = new HashMap();
        map3.put("0", 0);
        map3.put("1", "γ-GT");
        map3.put("2", "7-32 U/L");
        datas.add(map3);
        Map<String, Object> map4 = new HashMap();
        map4.put("0", 0);
        map4.put("1", "直接胆红素");
        map4.put("2", "0.1-5umol/L");
        datas.add(map4);
        Map<String, Object> map5 = new HashMap();
        map5.put("0", 0);
        map5.put("1", "总胆红素");
        map5.put("2", "3.4-17.1umol/L");
        datas.add(map5);
//

        Map<String, Object> map6 = new HashMap();
        map6.put("0", 1);
        map6.put("1", "GFR");
        map6.put("2", "大于90");
        datas.add(map6);
        Map<String, Object> map7 = new HashMap();
        map7.put("0", 1);
        map7.put("1", "肌酐");
        map7.put("2", "45-104umol/L");
        datas.add(map7);
        Map<String, Object> map8 = new HashMap();
        map8.put("0", 1);
        map8.put("1", "尿酸");
        map8.put("2", "155-428umol/L");
        datas.add(map8);
        Map<String, Object> map9 = new HashMap();
        map9.put("0", 1);
        map9.put("1", "尿微量白蛋白");
        map9.put("2", "参考值和单位？");
        datas.add(map9);

//
        Map<String, Object> map10 = new HashMap();
        map10.put("0", 2);
        map10.put("1", "甘油三酯");
        map10.put("2", "<1.7mmol/L");
        datas.add(map10);
        Map<String, Object> map11 = new HashMap();
        map11.put("0", 2);
        map11.put("1", "总胆固醇");
        map11.put("2", "<5.72mmol/L");
        datas.add(map11);
        Map<String, Object> map12 = new HashMap();
        map12.put("0", 2);
        map12.put("1", "高密度脂蛋白");
        map12.put("2", "0.9-2.0mmol/L");
        datas.add(map12);
        Map<String, Object> map13 = new HashMap();
        map13.put("0", 2);
        map13.put("1", "低密度脂蛋白");
        map13.put("2", "<3.4mmol/L");
        datas.add(map13);
        Map<String, Object> map14 = new HashMap();
        map14.put("0", 2);
        map14.put("1", "脂蛋白a");
        map14.put("2", "0-300mg/L");
        datas.add(map14);
    }

    @Override
    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        HeaderViewHolder headerholder;
        if (view != null) {
            headerholder = (HeaderViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_labcheck_header, viewGroup, false);
            headerholder = new HeaderViewHolder();
            headerholder.tvLabcheckHeader = (TextView) view.findViewById(R.id.tv_labcheck_header);
            view.setTag(headerholder);
        }
        headerholder.tvLabcheckHeader.setText(String.valueOf(datas.get(i).get("0")));
        return view;
    }

    @Override
    public long getHeaderId(int i) {
        return  Long.parseLong(datas.get(i).get("0").toString());
    }

    @Override
    public int getCount() {
        return datas.size();
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
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_labcheck, parent, false);
            holder = new ViewHolder();
            holder.tvLabitem1 = (TextView) convertView.findViewById(R.id.tv_labitem_1);
            holder.tvLabitem2 = (TextView) convertView.findViewById(R.id.tv_labitem_2);
            convertView.setTag(holder);
        }
        holder.tvLabitem1.setText(String.valueOf(datas.get(position).get("1")));
        holder.tvLabitem2.setText(String.valueOf(datas.get(position).get("2")));
        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_labcheck.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    class ViewHolder {

        TextView tvLabitem1;

        TextView tvLabitem2;

    }

    class HeaderViewHolder {

        TextView tvLabcheckHeader;


    }
}
