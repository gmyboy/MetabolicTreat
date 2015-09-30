package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.utils.ImageUtil;

import java.io.FileNotFoundException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 辅助检查图片适配器
 * Created by GMY on 2015/9/10 17:18.
 * Contact me via email gmyboy@qq.com.
 */
public class AuxiliaryPicAdapter extends BaseAdapter {
    private Context context;
    private List<String> urls;

    public AuxiliaryPicAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        String path = "";
        Bitmap bitmap = null;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_auxiliarypic, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        // 在最后添加一个加号
        if (position == getCount() - 1) {
            holder.ivItemPic.setImageResource(R.mipmap.common_phone);
            holder.tvItemDate.setText("点击即可拍照");
            holder.ivItemMore.setText("");
            convertView.setBackgroundColor(Color.parseColor("#ffffffff"));
        } else {
            path = (String) getItem(position);
            // 缩小图片的品质
            try {
                bitmap = ImageUtil.getBitmapByPath(path,
                        ImageUtil.getOptions(path), 180, 240);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (bitmap != null){
                holder.ivItemPic.setImageBitmap(bitmap);
                holder.tvItemDate.setText("2015年09月21日");
                holder.ivItemMore.setText("双侧下肢动脉超声");
            }
        }
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_auxiliarypic.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.iv_item_pic)
        ImageView ivItemPic;
        @Bind(R.id.tv_item_date)
        TextView tvItemDate;
        @Bind(R.id.iv_item_more)
        TextView ivItemMore;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
