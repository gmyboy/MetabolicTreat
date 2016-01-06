package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 所有普通adapter的基类
 * Created by GMY on 2015/9/23 13:29.
 * Contact me via email gmyboy@qq.com.
 */
public abstract class BaseCommAdapter<T extends Parcelable> extends BaseAdapter {
    private Context context;
    private List<T> datas = new ArrayList<>();
    public BaseCommAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
    }
    public BaseCommAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update() {

    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
