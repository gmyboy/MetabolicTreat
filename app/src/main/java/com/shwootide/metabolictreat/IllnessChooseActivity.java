package com.shwootide.metabolictreat;

import android.os.Bundle;
import android.widget.ListView;

import com.shwootide.metabolictreat.adapter.IllnessAdapter;
import com.shwootide.metabolictreat.entity.Illness;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;

/**
 * 疾病选择界面
 * Created by Administrator on 2015/8/19.
 */
public class IllnessChooseActivity extends BaseActivity {
    @Bind(R.id.lv_illnesschoose)
    ListView lvIllnesschoose;
    private IllnessAdapter adapter;
    private String personID = "";
    private String recordNo = "";
private String recordName="";
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
        personID = getIntent().getStringExtra("PersonID");
        recordNo = getIntent().getStringExtra("RecordNo");
        recordName=getIntent().getStringExtra("RecordName");
        getAllIllness();
    }

    /**
     * 从后台获得所有疾病列表
     */
    private void getAllIllness() {
        Map<String, String> params = new WeakHashMap<>();
        new MutiFetcher(Illness[].class).fetch(mContext, "GetAllIllness", "正在加载...", params);
    }

    /**
     * 接收返回数据
     *
     * @param event
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("GetAllIllness")) {
            if (event.getCode().equals("200")) {
                adapter = new IllnessAdapter(mContext, event.getObjects(), personID, recordNo,recordName);
                lvIllnesschoose.setAdapter(adapter);
            }
        }
    }
}
