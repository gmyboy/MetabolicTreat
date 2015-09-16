package com.shwootide.metabolictreat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.shwootide.metabolictreat.adapter.DiagnosisPageAdapter;
import com.shwootide.metabolictreat.adapter.RecordFullPageAdapter;
import com.shwootide.metabolictreat.entity.UserInfo;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.PreferenceUtil;
import com.shwootide.metabolictreat.widget.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 病历完整信息页
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordFullActivity extends BaseActivity {
    @Bind(R.id.btn_full_certain)
    Button btnFullCertain;
    @Bind(R.id.btn_full_back)
    Button btnFullBack;
    @Bind(R.id.btn_fuul_title)
    Button btnFuulTitle;
    @Bind(R.id.tv_full_time)
    TextView tvFullTime;//就诊次数
    @Bind(R.id.tv_full_doctor)
    TextView tvFullDoctor;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    RecordFullPageAdapter pageAdapter;

    @OnClick(R.id.btn_full_certain)
    void certina() {
        Intent intent = new Intent(mContext, DiagnosisActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_full_back)
    void back() {
        finish();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_recordfull);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterStickyEvent(true);
        super.onCreate(savedInstanceState);
        UserInfo userInfo = PreferenceUtil.getUser(mContext, "UserInfo");
        String[] disease = getResources().getStringArray(R.array.Disease);
//        btnFuulTitle.setText(disease[getIntent().getIntExtra("POSITION", 0)]);
        tvFullDoctor.setText(userInfo.getUserName());

        pageAdapter = new RecordFullPageAdapter(getSupportFragmentManager());
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
     * 接收数据
     */
    public void onEventMainThread(MessageEvent event) {

    }

    /**
     * mPagerSlidingTabStrip默认值配置
     */
    private void initTabsValue() {
        tabs.setExpandTab(0);
        tabs.setExpandMenuId(R.menu.pop_record_full);
        tabs.setMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int position = 0;
                switch (item.getItemId()) {
                    case R.id.good:
                        position = 0;
                        break;
                    case R.id.mid:
                        position = 1;
                        break;
                    case R.id.bad:
                        position = 2;
                        break;
                }
                EventBus.getDefault().post(new MessageEvent<>("RecordNowFragment",position));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_certain) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
