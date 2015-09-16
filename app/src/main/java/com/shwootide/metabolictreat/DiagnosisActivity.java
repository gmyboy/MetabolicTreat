package com.shwootide.metabolictreat;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.TypedValue;
import android.view.MenuItem;

import com.shwootide.metabolictreat.adapter.DiagnosisPageAdapter;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.widget.PagerSlidingTabStrip;

import butterknife.Bind;
import de.greenrobot.event.EventBus;

/**
 * 诊疗tab父页
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisActivity extends BaseActivity {
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    DiagnosisPageAdapter pageAdapter;

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
        pageAdapter = new DiagnosisPageAdapter(getSupportFragmentManager());
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
        tabs.setExpandTab(4);
        tabs.setExpandMenuId(R.menu.pop_diagnosis);
        tabs.setMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
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
        });
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
