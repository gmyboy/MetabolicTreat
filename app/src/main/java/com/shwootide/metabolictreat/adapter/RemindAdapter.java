package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.Remind;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页面查询结果适配器
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class RemindAdapter extends BaseCommAdapter<Remind> {
    public static final String SORT_FIELDS = "getCounttime";//对应字段的get函数
    private String type = null;//排序的顺序,默认升序

    public RemindAdapter(Context context, List<Remind> datas) {
        super(context, datas);
    }

    public RemindAdapter(Context context) {
        super(context);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Remind item;
        Drawable drawable;
        int countTime = 0;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_remind, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        item = getDatas().get(position);
        countTime = item.getCounttime();
        if (item.getSex().equals("女")) {
            holder.tvItemRemindName.setText(" " + item.getName());
            drawable = getContext().getResources().getDrawable(R.mipmap.ic_female36dp);
        } else {
            holder.tvItemRemindName.setText(" " + item.getName());
            drawable = getContext().getResources().getDrawable(R.mipmap.ic_male36dp);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        holder.tvItemRemindName.setCompoundDrawables(drawable, null, null, null);
        if (countTime < 0) {
            countTime = -countTime;
            holder.tvItemRemindTimeout.setBackgroundResource(R.mipmap.clock_gray);
        } else {
            holder.tvItemRemindTimeout.setBackgroundResource(R.mipmap.clock_color);
        }
        holder.tvItemRemindTimeout.setText(String.valueOf(countTime));
        holder.tvItemRemindTimestart.setText(CommonUtil.parseShortStr(item.getNextDate()));
        holder.tvItemRemindTimeend.setText(CommonUtil.parseShortStr(item.getNextEndDate()));
        holder.tvItemRemindWay.setText(item.getWay());
        return convertView;
    }

    /**
     * 清除所有已有数据
     */
    public void clearAll() {
        setDatas(new ArrayList<Remind>());
        notifyDataSetChanged();
    }

    /**
     * 按照倒计时排序
     */
    public void changeType() {
        if (getType() != null && "desc".equals(getType())) {
            setType("");
        } else {
            setType(Config.SORT_DESC);
        }
        CommonUtil.sortList(getDatas(), SORT_FIELDS, type);
        notifyDataSetChanged();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_remind.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_item_remind_name)
        TextView tvItemRemindName;
        @Bind(R.id.tv_item_remind_timeout)
        TextView tvItemRemindTimeout;
        @Bind(R.id.tv_item_remind_way)
        TextView tvItemRemindWay;
        @Bind(R.id.tv_item_remind_timestart)
        TextView tvItemRemindTimestart;
        @Bind(R.id.tv_item_remind_timeend)
        TextView tvItemRemindTimeend;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
