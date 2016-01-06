package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.shwootide.metabolictreat.FollowUpActivity;
import com.shwootide.metabolictreat.IllnessChooseActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.RecordChooseActivity;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.Illness;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.PreferenceUtil;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 疾病选择的adapter
 * Created by GMY on 2015/9/8 12:47.
 * Contact me via email gmyboy@qq.com.
 */
public class IllnessAdapter extends BaseCommAdapter<Illness> {
    private PopupWindow pop;
    //popup的三个按钮
    private Button btn1, btn2, btn3, btn4;

    public IllnessAdapter(Context context, List<Illness> datas) {
        super(context, datas);
        initPopWindow();
    }

    private void initPopWindow() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_illness, null);
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
        pop.showAtLocation(parent, Gravity.NO_GRAVITY, x + parent.getWidth() + 6, y - parent.getHeight());
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
        /**
         * 男性没有pcos
         * @time 2015-12-01
         */
        if (illness.getId().equals("3") && SysApplication.getInstance().getmRecord().getSex().equals("男")) {
            holder.btnItemIllnesschoose.setEnabled(false);
        } else {
            holder.btnItemIllnesschoose.setEnabled(true);
        }
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
                case R.id.btn_popup2:
                    getRecordNum(illnessid, v.getId());
                    break;
                case R.id.btn_popup3:
                    intent.setClass(getContext(), RecordChooseActivity.class);
                    intent.putExtra("Position", 2);//病历编辑
                    intent.putExtra("IllnessID", illnessid);
                    getContext().startActivity(intent);
                    break;
                case R.id.btn_popup4:
                    intent.setClass(getContext(), FollowUpActivity.class);
//                    intent.putExtra("Position", 2);//随访
                    intent.putExtra("IllnessID", illnessid);
                    getContext().startActivity(intent);
                    break;
            }

        }
    }

    /**
     * 获取person 医院 今天的就诊总数
     *
     * @param illnessid
     * @param id
     */
    private void getRecordNum(String illnessid, int id) {
        IllnessChooseActivity.position = id;
        IllnessChooseActivity.illnessid = illnessid;
        Map<String, String> params = new WeakHashMap<>();
        params.put("PersonID", SysApplication.getInstance().getmRecord().getId());
        params.put("HospitalID", PreferenceUtil.getUser(getContext(), "UserInfo").getHospitalID());
        params.put("IllnessID", illnessid);
        new SingleFetcher(String.class).fetch(getContext(), "FindMedicalRecordOne", null, params);
    }
}
