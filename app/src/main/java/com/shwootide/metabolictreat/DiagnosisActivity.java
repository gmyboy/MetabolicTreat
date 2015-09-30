package com.shwootide.metabolictreat;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TextView;

import com.shwootide.metabolictreat.adapter.DiagnosisPageAdapter;
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

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_diagnosis);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = getIntent().getStringExtra("IllnessID");
        switch (illnessid) {
            case "1":
                tvFullJibing.setText("糖尿病");
                break;
            case "2":
                tvFullJibing.setText("甲状腺疾病");
                break;
            case "3":
                tvFullJibing.setText("多囊卵巢综合症");
                break;
            case "9000":
                tvFullJibing.setText("其他疾病");
                break;
        }
        tvFullDate.setText(CommonUtil.getSysDate2());
        pageAdapter = new DiagnosisPageAdapter(getSupportFragmentManager(), illnessid);
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
        tabs.setExpandTabs(Arrays.asList(2, 4));
        tabs.setExpandMenuIds(Arrays.asList(R.menu.pop_diagnosis2, R.menu.pop_diagnosis), Arrays.asList(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int flag_change = 0;
                switch (item.getItemId()) {
                    case R.id.paizhao:
                        flag_change = 0;
                        break;
                    case R.id.luru:
                        flag_change = 1;
                        break;
                }
                //选择性的更新第一个tab
                pageAdapter.setFragmentsUpdateFlag(new boolean[]{false, false, true, false, false});
                //设置评价排序规则
                pageAdapter.setChangeType(flag_change);
                pageAdapter.notifyDataSetChanged();
                return false;
            }
        }, new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int position = 0;
                switch (item.getItemId()) {
                    case R.id.zhidao:
                        position = 0;
                        break;
                    case R.id.yaowu:
                        position = 1;
                        break;
                    case R.id.huayandan:
                        position = 2;
                        break;
                    case R.id.xiaci:
                        position = 3;
                        break;
                }
                EventBus.getDefault().post(new MessageEvent<>("TellFragment", position));
                return false;
            }
        }));
        // 底部游标颜色
        tabs.setIndicatorColor(getResources().getColor(R.color.tab_indicator));
        // tab的分割线颜色
        tabs.setDividerColor(Color.TRANSPARENT);
        // tab背景
        tabs.setBackgroundColor(getResources().getColor(R.color.tab_bg));
        // tab底线高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));
        // 游标高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        // 选中的文字颜色
        tabs.setSelectedTextColor(getResources().getColor(R.color.tab_txt_selected));
        // 正常文字颜色
        tabs.setTextColor(getResources().getColor(R.color.tab_txt_normal));
        tabs.setTextSize(getResources().getDimensionPixelOffset(R.dimen.tab_textsize));
        tabs.setShouldExpand(true);
    }
}
