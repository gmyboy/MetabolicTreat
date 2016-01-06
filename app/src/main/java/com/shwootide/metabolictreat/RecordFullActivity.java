package com.shwootide.metabolictreat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.adapter.RecordFullPageAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.PagerSlidingTabStrip;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * 病历完整信息页
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordFullActivity extends BaseActivity {

    @Bind(R.id.tv_full_time)
    TextView tvFullTime;//就诊次数
    @Bind(R.id.tv_full_date)
    TextView tvFullDate;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.tv_full_jibing)
    TextView tvFullJibing;
    private RecordFullPageAdapter pageAdapter;

    private String illnessid = "";//疾病id
    private int position;//默认为初诊
    private MedicalRecord medicalRecord;//保存就诊其本主表信息
    private int flag_change = 0;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_recordfull);
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

    /**
     * 返回时清空临时数据
     */
    @Override
    protected void onDestroy() {
        SysApplication.getInstance().setMedicalRecord(null);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterEvent(true);
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
        tvFullTime.setText("1");
        if (position == 0) {//初诊 0
            tvFullDate.setText(CommonUtil.getSysDate2());
        } else {//病历编辑 2
            medicalRecord = getIntent().getParcelableExtra("MedicalRecord");
            tvFullDate.setText(CommonUtil.parseStr(medicalRecord.getRecordDate()));
            saveMedicalRecord();
//            showToast("病历编辑 id=" + SysApplication.getInstance().getMedicalRecord().getId());
        }
        initPage();
    }

    /**
     * 写入app
     */
    private void saveMedicalRecord() {
        SysApplication.getInstance().setMedicalRecord(medicalRecord);
    }

    /**
     * 初始化page页面
     */
    private void initPage() {
        pageAdapter = new RecordFullPageAdapter(getSupportFragmentManager(), illnessid, position, 0);
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
        List<Integer> integers;
        tabs.setExpandTabs(Arrays.asList(0));
        if (illnessid.equals("2")) {
            integers = Arrays.asList(R.menu.pop_record_full2);
        } else if (illnessid.equals("1")) {
            integers = Arrays.asList(R.menu.pop_record_full);
        } else {
            integers = Arrays.asList(R.menu.pop_record_full3);
        }
        tabs.setExpandMenuIds(integers, Arrays.<PopupMenu.OnMenuItemClickListener>asList(new PopupMenu.OnMenuItemClickListener() {
                                                                                             @Override
                                                                                             public boolean onMenuItemClick(MenuItem item) {

                                                                                                 switch (item.getItemId()) {
                                                                                                     case R.id.good:
                                                                                                         flag_change = 0;
                                                                                                         if (pageAdapter.getChangeType() != 0) {
                                                                                                             //选择性的更新第一个tab
                                                                                                             pageAdapter.setFragmentsUpdateFlag(new boolean[]{true, false, false, false, false});
                                                                                                             //设置评价排序规则
                                                                                                             pageAdapter.setChangeType(flag_change);
                                                                                                             pageAdapter.notifyDataSetChanged();
//                            RecordMedicineFragment fragment = (RecordMedicineFragment) pageAdapter.getItem(0);
//                            if (fragment.getCheckup() != null && fragment.position != 2){
//                                new MaterialDialog.Builder(mContext)
//                                        .title("提示")
//                                        .content("您该页面还有未提交的数据")
//                                        .positiveText("继续跳转")
//                                        .negativeText("留在该页面")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                //选择性的更新第一个tab
//                                                pageAdapter.setFragmentsUpdateFlag(new boolean[]{true, false, false, false, false});
//                                                //设置评价排序规则
//                                                pageAdapter.setChangeType(flag_change);
//                                                pageAdapter.notifyDataSetChanged();
//                                            }
//                                        })
//                                        .show();
                                                                                                         }
                                                                                                         break;
                                                                                                     case R.id.mid:
                                                                                                         flag_change = 1;
                                                                                                         if (pageAdapter.getChangeType() != 1) {
                                                                                                             //选择性的更新第一个tab
                                                                                                             pageAdapter.setFragmentsUpdateFlag(new boolean[]{true, false, false, false, false});
                                                                                                             //设置评价排序规则
                                                                                                             pageAdapter.setChangeType(flag_change);
                                                                                                             pageAdapter.notifyDataSetChanged();
//                            RecordNowFragment fragment= (RecordNowFragment) pageAdapter.getItem(0);
//                            if (fragment.getmHistory_now() != null && fragment.position != 2){
//                                new MaterialDialog.Builder(mContext)
//                                        .title("提示")
//                                        .content("您该页面还有未提交的数据")
//                                        .positiveText("继续跳转")
//                                        .negativeText("留在该页面")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                //选择性的更新第一个tab
//                                                pageAdapter.setFragmentsUpdateFlag(new boolean[]{true, false, false, false, false});
//                                                //设置评价排序规则
//                                                pageAdapter.setChangeType(flag_change);
//                                                pageAdapter.notifyDataSetChanged();
//                                            }
//                                        })
//                                        .show();
                                                                                                         }
                                                                                                         break;
                                                                                                 }
                                                                                                 return false;
                                                                                             }
                                                                                         }
        ));

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
