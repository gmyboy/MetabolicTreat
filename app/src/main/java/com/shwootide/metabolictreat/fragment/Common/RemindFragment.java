package com.shwootide.metabolictreat.fragment.Common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.RemindAdapter;
import com.shwootide.metabolictreat.entity.Remind;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提醒
 * Created by GMY on 2015/12/17 16:07.
 * Contact me via email igmyboy@gmail.com.
 */
public class RemindFragment extends MenuFragment {
    @Bind(R.id.btn_remind_search)
    Button btnRemindSearch;
    @Bind(R.id.et_remind_name)
    EditText etRemindName;
    @Bind(R.id.tv_remind_time_start)
    Button tvRemindTimeStart;
    @Bind(R.id.tv_remind_time_end)
    Button tvRemindTimeEnd;
    @Bind(R.id.lv_remind)
    ListView lvRemind;
    @Bind(R.id.tv_remind_name_sort)
    TextView tvRemindNameSort;
    @Bind(R.id.tv_remind_timeout_sort)
    TextView tvRemindTimeoutSort;
    @Bind(R.id.tv_remind_way_sort)
    TextView tvRemindWaySort;
    @Bind(R.id.tv_remind_timestart_sort)
    TextView tvRemindTimestartSort;
    @Bind(R.id.tv_remind_timeend_sort)
    TextView tvRemindTimeendSort;

    private RemindAdapter adapter;
    private boolean[] descs = {false, false, false, false, false};

    @OnClick(R.id.tv_remind_name_sort)
    void sort_by_name() {
        if (descs[0]) {
            getDate("PersonInfo.name DESC");
        } else {
            getDate("PersonInfo.name");
        }
        descs[0] = !descs[0];
    }

    @OnClick(R.id.tv_remind_timeout_sort)
    void sort_by_timeout() {
        adapter.changeType();
    }

    @OnClick(R.id.tv_remind_way_sort)
    void sort_by_way() {
        if (descs[2]) {
            getDate("MedicalRecord.Online DESC");
        } else {
            getDate("MedicalRecord.Online");
        }
        descs[2] = !descs[2];
    }

    @OnClick(R.id.tv_remind_timestart_sort)
    void sort_by_timestart() {
        if (descs[3]) {
            getDate("MedicalRecord.NextDate DESC");
        } else {
            getDate("MedicalRecord.NextDate");
        }
        descs[3] = !descs[3];
    }

    @OnClick(R.id.tv_remind_timeend_sort)
    void sort_by_timeend() {
        if (descs[4]) {
            getDate("MedicalRecord.NextEndDate DESC");
        } else {
            getDate("MedicalRecord.NextEndDate");
        }
        descs[4] = !descs[4];
    }

    @OnClick({R.id.tv_remind_time_start, R.id.tv_remind_time_end})
    void showDate(View v) {
        CommonUtil.showFormatDateDialog(getActivity(), v);
    }

    @OnClick(R.id.btn_remind_search)
    void search() {
        getDate("");
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_remind;
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.getWhat().equals("RemindVisit")) {
            if (event.getCode().equals("200")) {
                adapter = new RemindAdapter(getActivity(), event.getObjects());
                lvRemind.setAdapter(adapter);
            } else {
                adapter.clearAll();
            }
        }
    }

    /**
     * 获取数据
     *
     * @param sort 排序规则
     */
    private void getDate(String sort) {
        String name = etRemindName.getText().toString().trim();
        String start = tvRemindTimeStart.getText().toString().trim();
        String end = tvRemindTimeEnd.getText().toString().trim();
        Map<String, String> params = new WeakHashMap<>();
        params.put("name", name);
        params.put("OrderByField", sort);
        params.put("startDate", start);
        params.put("endDate", end);
        new MutiFetcher(Remind[].class).fetch(getActivity(), "RemindVisit", "正在查询...", params);
    }
}
