package com.shwootide.metabolictreat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.adapter.DiagnosisPageAdapter;
import com.shwootide.metabolictreat.adapter.RecordFullPageAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import de.greenrobot.event.EventBus;

/**
 * 诊疗tab父页
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisActivity extends BaseActivity {
    @Bind(R.id.tv_full_time)
    TextView tvFullTime;//就诊次数
    @Bind(R.id.tv_full_date)
    TextView tvFullDate;
    @Bind(R.id.tv_full_jibing)
    TextView tvFullJibing;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    private DiagnosisPageAdapter pageAdapter;
    private String illnessid = "";
    private int position;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_diagnosis);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    public void wentTo(RadioGroup group, int checkedId) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (checkedId) {
            case R.id.rb_add:
                intent.putExtra("FLAG", 0);
                startActivity(intent);
                break;
            case R.id.rb_search:
                intent.putExtra("FLAG", 1);
                startActivity(intent);
                break;
            case R.id.rb_schedule:
                intent.putExtra("FLAG", 2);
                startActivity(intent);
                break;
            case R.id.rb_remind:
                intent.putExtra("FLAG", 3);
                startActivity(intent);
                break;
            case R.id.rb_setting:
                intent.putExtra("FLAG", 4);
                startActivity(intent);
                break;
            case R.id.rb_help:
                intent.putExtra("FLAG", 5);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        super.onCreate(savedInstanceState);
        illnessid = getIntent().getStringExtra("IllnessID");
        position = getIntent().getIntExtra("Position", 0);
        switch (illnessid) {
            case "1":
                tvFullJibing.setText("糖 尿 病");
                break;
            case "2":
                tvFullJibing.setText("甲 状 腺 疾 病");
                break;
            case "3":
                tvFullJibing.setText("多 囊 卵 巢 综 合 症");
                break;
            case "9000":
                tvFullJibing.setText("其 他 疾 病");
                break;
        }
        tvFullDate.setText(CommonUtil.parseStr(SysApplication.getInstance().getMedicalRecord().getRecordDate()));
        initPage(0);
    }

    /**
     * 初始化page页面
     */
    private void initPage(int sequenceNumber) {
        pageAdapter = new DiagnosisPageAdapter(getSupportFragmentManager(), illnessid, position, sequenceNumber);
        pager.setAdapter(pageAdapter);
        pager.setOffscreenPageLimit(1);
        tabs.setViewPager(pager);
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        initTabsValue();
    }

    /**
     * mPagerSlidingTabStrip默认值配置
     */
    private void initTabsValue() {
        tabs.setExpandTabs(Arrays.asList(0, 1, 3));
        PopupMenu.OnMenuItemClickListener listener1 = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int flag_change = 2;
                switch (item.getItemId()) {
                    case R.id.luru1:
                        flag_change = 2;
                        if (pageAdapter.getChangeType() != 2) {//选择性的更新第一个tab
                            pageAdapter.setFragmentsUpdateFlag(new boolean[]{true, false, false, false});
                            //设置评价排序规则
                            pageAdapter.setChangeType(flag_change);
                            pageAdapter.notifyDataSetChanged();
                        }
                        break;
                    case R.id.paizhao1:
                        flag_change = 3;
                        if (pageAdapter.getChangeType() != 3) {//选择性的更新第一个tab
                            pageAdapter.setFragmentsUpdateFlag(new boolean[]{true, false, false, false});
                            //设置评价排序规则
                            pageAdapter.setChangeType(flag_change);
                            pageAdapter.notifyDataSetChanged();
                        }
                        break;
                }

                return false;
            }
        };
        PopupMenu.OnMenuItemClickListener listener2 = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int flag_change = 0;
                switch (item.getItemId()) {
                    case R.id.luru2:
                        flag_change = 0;
                        if (pageAdapter.getChangeType() != 0) {//选择性的更新第2个tab
                            pageAdapter.setFragmentsUpdateFlag(new boolean[]{false, true, false, false});
                            //设置评价排序规则
                            pageAdapter.setChangeType(flag_change);
                            pageAdapter.notifyDataSetChanged();
                        }
                        break;
                    case R.id.paizhao2:
                        flag_change = 1;
                        if (pageAdapter.getChangeType() != 1) {//选择性的更新第2个tab
                            pageAdapter.setFragmentsUpdateFlag(new boolean[]{false, true, false, false});
                            //设置评价排序规则
                            pageAdapter.setChangeType(flag_change);
                            pageAdapter.notifyDataSetChanged();
                        }
                        break;
                }
                return false;
            }
        };
        PopupMenu.OnMenuItemClickListener listener3 = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int flag_change = 4;
                switch (item.getItemId()) {
                    case R.id.zhidao:
                        flag_change = 4;
                        if (pageAdapter.getChangeType() != 4) {//选择性的更新第4个tab
                            pageAdapter.setFragmentsUpdateFlag(new boolean[]{false, false, false, true});
                            //设置评价排序规则
                            pageAdapter.setChangeType(flag_change);
                            pageAdapter.notifyDataSetChanged();
                        }
                        break;
                    case R.id.huanyandan:
                        flag_change = 5;
                        if (pageAdapter.getChangeType() != 5) {//选择性的更新第4个tab
                            pageAdapter.setFragmentsUpdateFlag(new boolean[]{false, false, false, true});
                            //设置评价排序规则
                            pageAdapter.setChangeType(flag_change);
                            pageAdapter.notifyDataSetChanged();
                        }
                        break;
                    case R.id.fuzhujiancha:
                        flag_change = 6;
                        if (pageAdapter.getChangeType() != 6) {//选择性的更新第4个tab
                            pageAdapter.setFragmentsUpdateFlag(new boolean[]{false, false, false, true});
                            //设置评价排序规则
                            pageAdapter.setChangeType(flag_change);
                            pageAdapter.notifyDataSetChanged();
                        }
                        break;
                }
                return false;
            }
        };
        tabs.setExpandMenuIds(Arrays.asList(R.menu.pop_diagnosis1, R.menu.pop_diagnosis2, R.menu.pop_diagnosis), Arrays.asList(listener1, listener2, listener3));
        // 底部游标颜色
        tabs.setIndicatorColor(getResources().getColor(R.color.tab_indicator));
        // tab的分割线颜色
        tabs.setDividerColor(Color.TRANSPARENT);
        // tab底线高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));
        // 游标高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        // 选中的文字颜色
        tabs.setSelectedTextColor(getResources().getColor(R.color.tab_indicator));
        // 正常文字颜色
        tabs.setTextColor(getResources().getColor(R.color.tab_txt_normal));
        tabs.setTextSize(getResources().getDimensionPixelOffset(R.dimen.tab_textsize));
        tabs.setShouldExpand(true);
    }
}
