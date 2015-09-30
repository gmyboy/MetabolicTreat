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
public class IllnessAdapter extends BaseCommAdapter<Illness> {
    private PopupWindow pop;
    //popup的三个按钮
    private Button btn1, btn2, btn3, btn4;
    private String personID = "";
    private String recordNo = "";
    private String recordName = "";

    public IllnessAdapter(Context context, List<Illness> datas, String personID, String recordNo, String recordName) {
        super(context, datas);
        initPopWindow();
        this.personID = personID;
        this.recordNo = recordNo;
        this.recordName = recordName;
    }

    private void initPopWindow() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.view_popup, null);
        btn1 = (Button) contentView.findViewById(R.id.btn_popup1);
        btn2 = (Button) contentView.findViewById(R.id.btn_popup2);
        btn3 = (Button) contentView.findViewById(R.id.btn_popup3);
        btn4 = (Button) contentView.findViewById(R.id.btn_popup4);
        pop = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setAnimationStyle(R.style.PopMenuAnimation);
        pop.setBackgroundDrawable(new BitmapDrawable());
    }

    public void showPop(View parent, int x, int y, int postion) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        pop.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] + parent.getWidth() + 6, location[1] - parent.getHeight());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.update();
        if (pop.isShowing()) {

        }
        btn1.setOnClickListener(new popItemAction(getItem(postion).getId()));
        btn2.setOnClickListener(new popItemAction(getItem(postion).getId()));
        btn3.setOnClickListener(new popItemAction(getItem(postion).getId()));
        btn4.setOnClickListener(new popItemAction(getItem(postion).getId()));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Illness illness;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_illnesschoose, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        illness = getDatas().get(position);
        holder.btnItemIllnesschoose.setText(illness.getName());
        holder.btnItemIllnesschoose.setOnClickListener(new popAction(position));
        return convertView;
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

    private class popItemAction implements View.OnClickListener {
        String illnessid;

        public popItemAction(String illnessid) {
            this.illnessid = illnessid;
        }

        @Override
        public void onClick(View v) {
            if (pop.isShowing()) {
                pop.dismiss();
            }
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btn_popup1:
                    intent.setClass(getContext(), RecordFullActivity.class);
                    intent.putExtra("Position", "1");//初诊
                    intent.putExtra("IllnessID", illnessid);
                    intent.putExtra("PersonID", personID);
                    intent.putExtra("RecordNo", recordNo);
                    intent.putExtra("RecordName", recordName);//患者姓名
                    getContext().startActivity(intent);
                    break;
                case R.id.btn_popup2:
                    intent.setClass(getContext(), RecordFullActivity.class);
                    intent.putExtra("Position", "2");//复诊
                    intent.putExtra("IllnessID", illnessid);
                    intent.putExtra("PersonID", personID);
                    intent.putExtra("RecordNo", recordNo);
                    intent.putExtra("RecordName", recordName);//患者姓名
                    getContext().startActivity(intent);
                    break;
                case R.id.btn_popup3:

                    break;
                case R.id.btn_popup4:

                    break;
            }

        }
    }
}
