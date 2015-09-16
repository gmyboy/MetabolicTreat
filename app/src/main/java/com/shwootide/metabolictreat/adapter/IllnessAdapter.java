package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.RecordChooseActivity;
import com.shwootide.metabolictreat.RecordFullActivity;
import com.shwootide.metabolictreat.entity.Illness;
import com.shwootide.metabolictreat.entity.Record;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 疾病选择的adapter
 * Created by GMY on 2015/9/8 12:47.
 * Contact me via email gmyboy@qq.com.
 */
public class IllnessAdapter extends BaseAdapter implements View.OnClickListener {
    private List<Illness> datas = new ArrayList<>();
    private Context context;
    private PopupWindow pop;
    private LayoutInflater inflater = null;

    public IllnessAdapter(Context context, List<Illness> datas) {
        inflater = LayoutInflater.from(context);
        initPopWindow();
        this.context = context;
        this.datas = datas;
    }

    private void initPopWindow() {
        View contentView = inflater.inflate(R.layout.view_popup, null);
        Button btn1, btn2, btn3;
        btn1 = (Button) contentView.findViewById(R.id.btn_popup1);
        btn2 = (Button) contentView.findViewById(R.id.btn_popup2);
        btn3 = (Button) contentView.findViewById(R.id.btn_popup3);
        pop = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setAnimationStyle(R.style.PopMenuAnimation);
//        pop.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popup));
        pop.setBackgroundDrawable(new BitmapDrawable());
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    public void showPop(View parent, int x, int y, int postion) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        pop.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] + parent.getWidth()+6, location[1]-parent.getHeight()/2);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.update();
        if (pop.isShowing()) {

        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        Illness illness;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.item_illnesschoose, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        illness = datas.get(position);
        holder.btnItemIllnesschoose.setText(illness.getName());
        holder.btnItemIllnesschoose.setOnClickListener(new popAction(position));

        return convertView;
    }

    @Override
    public void onClick(View v) {
        if (pop.isShowing()) {
            pop.dismiss();
        }
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_popup1:
                intent.setClass(context, RecordFullActivity.class);
                break;
            case R.id.btn_popup2:
                intent.setClass(context, RecordFullActivity.class);
                break;
            case R.id.btn_popup3:
                intent.setClass(context, RecordChooseActivity.class);
                break;
        }
        context.startActivity(intent);
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_illnesschoose.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.btn_item_illnesschoose)
        Button btnItemIllnesschoose;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class popAction implements View.OnClickListener {
        int position;

        public popAction(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            int[] arrayOfInt = new int[2];
            v.getLocationOnScreen(arrayOfInt);
            int x = arrayOfInt[0];
            int y = arrayOfInt[1];
            showPop(v, x, y, position);
        }
    }
}
