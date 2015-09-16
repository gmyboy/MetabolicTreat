package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 现病史
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordNowFragment extends Fragment {

    @Bind(R.id.ll_now1)
    LinearLayout llNow1;
    @Bind(R.id.ll_now2)
    LinearLayout llNow2;
    @Bind(R.id.ll_now3)
    LinearLayout llNow3;

    @Bind(R.id.spinner_now1)
    NiceSpinner spinnerNow1;
    @Bind(R.id.spinner_now2)
    NiceSpinner spinnerNow2;
    @Bind(R.id.spinner_now3)
    NiceSpinner spinnerNow3;
    @Bind(R.id.ns_recordnow)
    NiceSpinner nsRecordnow;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recordnow, null);
        ButterKnife.bind(this, view);
        List<String> dataset = new LinkedList<>(Arrays.asList("佳", "不佳", "失眠"));
        nsRecordnow.attachDataSource(dataset);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("降糖类", "降压类", "降脂类"));
        spinnerNow1.attachDataSource(dataset1);
        spinnerNow1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> dataset2;
                switch (position) {
                    case 0:
                        dataset2 = new LinkedList<>(Arrays.asList("格列吡嗪", "格列吡嗪控释片", "格列本脲"));
                        spinnerNow2.attachDataSource(dataset2);
                        spinnerNow2.showDropDown();
                        break;
                    case 1:
                        dataset2 = new LinkedList<>(Arrays.asList("降压宝"));
                        spinnerNow2.attachDataSource(dataset2);
                        spinnerNow2.showDropDown();
                        break;
                    case 2:
                        dataset2 = new LinkedList<>(Arrays.asList("降脂宝"));
                        spinnerNow2.attachDataSource(dataset2);
                        spinnerNow2.showDropDown();
                        break;
                }
                spinnerNow2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        List<String> dataset3 = new LinkedList<>(Arrays.asList("格列本脲", "格列波脲", "瑞格列奈", "米格列奈", "阿格列汀"));
                        spinnerNow3.attachDataSource(dataset3);
                        spinnerNow3.showDropDown();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("RecordNowFragment")) {
            switch (event.getMeta()) {
                case 0:
                    llNow1.setVisibility(View.VISIBLE);
                    llNow2.setVisibility(View.GONE);
                    llNow3.setVisibility(View.GONE);
                    break;
                case 1:
                    llNow2.setVisibility(View.VISIBLE);
                    llNow1.setVisibility(View.GONE);
                    llNow3.setVisibility(View.GONE);
                    break;
                case 2:
                    llNow3.setVisibility(View.VISIBLE);
                    llNow1.setVisibility(View.GONE);
                    llNow2.setVisibility(View.GONE);
                    break;
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
