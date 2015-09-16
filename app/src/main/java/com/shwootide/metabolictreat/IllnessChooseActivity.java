package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.adapter.IllnessAdapter;
import com.shwootide.metabolictreat.entity.Illness;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by Administrator on 2015/8/19.
 */
public class IllnessChooseActivity extends BaseActivity {
    @Bind(R.id.lv_illnesschoose)
    ListView lvIllnesschoose;
    private IllnessAdapter adapter;


    @Override
    public void setLayout() {
        setContentView(R.layout.activcity_illnesschoose);
    }

    @Override
    public void setTitleBarOption() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
        getAllIllness();
    }

    /**
     * 从后台获得所有疾病列表
     */
    private void getAllIllness() {
        Map<String, String> params = new WeakHashMap<>();
        new MutiFetcher(Illness[].class).fetch(mContext, "GetAllIllness", "", params);
    }

    /**
     * 接收返回数据
     *
     * @param event
     */
    public void onEvent(MessageEvent event) {
        if (event.what.equals("GetAllIllness")) {
            if (event.getCode().equals("200")) {
                adapter = new IllnessAdapter(mContext, event.getObjects());
                lvIllnesschoose.setAdapter(adapter);
            }
        } else {

        }
    }
}
