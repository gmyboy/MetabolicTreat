package com.shwootide.metabolictreat.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.PicInfo;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.ImageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 辅助检查图片适配器
 * Created by GMY on 2015/9/10 17:18.
 * Contact me via email gmyboy@qq.com.
 */
public class AuxiliaryPicAdapter extends BaseAdapter {
    public static int FROM_SERVER = 0;
    public static int FROM_LOCAL = 1;
    private Context context;
    private List<PicInfo> datas = new ArrayList<>();
    private onPicClickListener picClickListener;
    private int imgType = FROM_LOCAL;//从本地获取的图片 0   从服务器获取的图片 1

    public AuxiliaryPicAdapter(Context context, List<PicInfo> urls) {
        this.context = context;
        this.datas = urls;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public List<PicInfo> getDatas() {
        return datas;
    }

    public void setDatas(List<PicInfo> datas) {
        this.datas = datas;
    }

    public onPicClickListener getPicClickListener() {
        return picClickListener;
    }

    public void setPicClickListener(onPicClickListener picClickListener) {
        this.picClickListener = picClickListener;
    }

    @Override
    public int getCount() {
        return datas.size() + 1;
    }

    @Override
    public PicInfo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        PicInfo item;
        Bitmap bitmap = null;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_auxiliarypic, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        convertView.setBackgroundColor(Color.parseColor("#ffffffff"));
        // 在最后添加一个加号
        if (position == getCount() - 1) {
            holder.ivItemPic.setImageResource(R.mipmap.common_phone);
            holder.ivItemMore.setVisibility(View.GONE);
            holder.tvItemDate.setText("点击添加图片");
            holder.ivItemPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (picClickListener != null) {
                        picClickListener.onPick(position, true);
                    }
                }
            });
        } else {
            item = getItem(position);
            if (imgType == FROM_SERVER) {
                Glide.with(context).load(Config.IMG_SERVER_PATH + item.getFileName()).override(200, 100).into(holder.ivItemPic);
                holder.tvItemDate.setText(CommonUtil.parseForminnerStr(item.getCheckDate()));
            } else {
                Glide.with(context).load(item.getLocal_path()).override(200,100).into(holder.ivItemPic);
                holder.tvItemDate.setText(item.getCheckDate());
            }
            holder.ivItemMore.setVisibility(View.VISIBLE);
            holder.ivItemMore.setText(item.getTitleName());
            holder.ivItemPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (picClickListener != null) {
                        picClickListener.onPick(position, false);
                    }
                }
            });
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

    public interface onPicClickListener {
        void onPick(int position, boolean isLast);
    }
}
