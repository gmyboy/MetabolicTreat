package com.shwootide.metabolictreat.fragment.Common;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gmy.segmentedgroup.SegmentedGroup;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.RecordActivity;
import com.shwootide.metabolictreat.adapter.MainAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 查询患者
 * Created by GMY on 2015/12/17 16:07.
 * Contact me via email igmyboy@gmail.com.
 */
public class SearchFragment extends MenuFragment {
    @Bind(R.id.et_main_name)
    EditText etMainName;
    @Bind(R.id.tv_main_nian)
    EditText tvMainNian;
    @Bind(R.id.tv_main_yue)
    EditText tvMainYue;
    @Bind(R.id.btn_main_search)
    Button btnMainSearch;
    @Bind(R.id.lv_main)
    ListView lvMain;
    @Bind(R.id.sg_main_sex)
    SegmentedGroup sgMainSex;
    @Bind(R.id.tv_main_name_tip)
    TextView tvMainNameTip;
    @Bind(R.id.tv_header_main_name)
    TextView tvHeaderMainName;
//    @Bind(R.id.tv_header_main_sex)
//    TextView tvHeaderMainSex;
    @Bind(R.id.tv_header_main_birthday)
    TextView tvHeaderMainBirthday;
    @Bind(R.id.tv_header_main_recordid)
    TextView tvHeaderMainRecordid;

    private MainAdapter adapter;
    private boolean[] descs = {false, false, false, false};

    @OnClick(R.id.btn_main_search)
    void search() {
        getDate("");
    }

    @OnClick(R.id.tv_header_main_name)
    void search_by_name() {
        if (descs[0]) {
            getDate("PersonInfo.name DESC");
        } else {
            getDate("PersonInfo.name");
        }
        descs[0] = !descs[0];
    }

//    @OnClick(R.id.tv_header_main_sex)
//    void search_by_sex() {
//        if (descs[1]) {
//            getDate("PersonInfo.Sex DESC");
//        } else {
//            getDate("PersonInfo.Sex");
//        }
//        descs[1] = !descs[1];
//    }

    @OnClick(R.id.tv_header_main_birthday)
    void search_by_birthday() {
        if (descs[2]) {
            getDate("PersonInfo.birth DESC");
        } else {
            getDate("PersonInfo.birth");
        }
        descs[2] = !descs[2];
    }

    @OnClick(R.id.tv_header_main_recordid)
    void search_by_recordno() {
        if (descs[3]) {
            getDate("PersonInfo.MedicalRecordNo DESC");
        } else {
            getDate("PersonInfo.MedicalRecordNo");
        }
        descs[3] = !descs[3];
    }

    @OnItemClick(R.id.lv_main)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), RecordActivity.class);
        //保存record信息
        SysApplication.getInstance().setmRecord((Record) lvMain.getAdapter().getItem(position));
        intent.putExtra("TYPE", 1);//0 新建   1  点击
        startActivity(intent);
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_search;
    }

    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.getWhat().equals("PatientQuery")) {
            if (event.getCode().equals("200")) {
                adapter = new MainAdapter(getActivity(), event.getObjects());
                lvMain.setAdapter(adapter);
            } else {
                adapter.clearAll();
            }
        }
    }

    /**
     * 获取数据
     */
    private void getDate(String order) {
        String name = etMainName.getText().toString().trim();
        String sex = sgMainSex.getCheckedRadioButtonId() == R.id.rb_main_man ? "男" : "女";
        String nian = tvMainNian.getText().toString().trim();
        String yue = tvMainYue.getText().toString().trim();
        Map<String, String> params = new WeakHashMap<>();
        params.put("name", name);
        params.put("sex", sex);
        params.put("OrderByField", order);
        params.put("birth", TextUtils.isEmpty(nian) ? (TextUtils.isEmpty(yue) ? "" : yue + "月") : (TextUtils.isEmpty(yue) ? nian + "年" : nian + "年" + yue + "月"));
        new MutiFetcher(Record[].class).fetch(getActivity(), "PatientQuery", "正在查询...", params);
    }
}
