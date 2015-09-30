package com.shwootide.metabolictreat;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.shwootide.metabolictreat.adapter.RecordFullPageAdapter;
import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.entity.UserInfo;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.PreferenceUtil;
import com.shwootide.metabolictreat.widget.PagerSlidingTabStrip;

import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

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
    private String personID = "";//患者id
    private String recordNo = "";//病历编号
    private String recordName = "";//患者姓名
    private String position = "1";//就诊次数  默认为初诊1
    private UserInfo userInfo;//登陆医师信息

    public static String MedicalRecordID = "";

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_recordfull);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
        userInfo = PreferenceUtil.getUser(mContext, "UserInfo");
        illnessid = getIntent().getStringExtra("IllnessID");
        personID = getIntent().getStringExtra("PersonID");
        recordNo = getIntent().getStringExtra("RecordNo");
        recordName = getIntent().getStringExtra("RecordName");
        position = getIntent().getStringExtra("Position");
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
        tvFullTime.setText(position);
        initPage();
        if (position.equals("1")) {

//            addMedicalRecord();
        } else {
//            searchMedicalRecord();
        }

    }

    /**
     * 获取基本信息主表 获取()
     */
    private void searchMedicalRecord() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("PersonID", personID);
        params.put("HospitalID", userInfo.getHospitalID());
        params.put("IllnessID", illnessid);
        new MutiFetcher(MedicalRecord[].class).fetch(mContext, "FindMedicalRecord", "正在加载...", params);
    }

    /**
     * 添加一条就诊基本信息记录
     */
    private void addMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(CommonUtil.generateGUID());
        medicalRecord.setPersonID(personID);
        medicalRecord.setRecordDate(CommonUtil.getSysDate());
        medicalRecord.setRecordNo(recordNo);
        medicalRecord.setHospitalID(userInfo.getHospitalID());
        medicalRecord.setDepartmentID(userInfo.getDepartmentID());
        medicalRecord.setInputDate(CommonUtil.getSysDate());
        medicalRecord.setInputUser(userInfo.getUserName());
        medicalRecord.setBz("");
        medicalRecord.setStaffID(userInfo.getStaffID());
        medicalRecord.setDiagnose("");
        medicalRecord.setComplication("");
        medicalRecord.setSequenceNumber(position);
        medicalRecord.setIllnessID(illnessid);
        medicalRecord.setNextDate(CommonUtil.getSysDate());
        MedicalRecordID = medicalRecord.getId();
        new SingleFetcher(String.class).addMedicalRecord(mContext, null, medicalRecord);
    }

    /**
     * 初始化page页面
     */
    private void initPage() {
        pageAdapter = new RecordFullPageAdapter(getSupportFragmentManager(), illnessid, recordName, position);
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
        if (event.what.equals("AddMedicalRecord")) {
            if (event.getCode().equals("200")) {
                showToast("添加基本信息主表成功");
            } else {
                showToast("添加基本信息主表失败");
                MedicalRecordID = "";
            }
        } else if (event.what.equals("FindMedicalRecord")) {
            if (event.getCode().equals("200")) {
                MedicalRecord temp = (MedicalRecord) event.getObjects().get(0);
                tvFullDate.setText(CommonUtil.getSysDate2());
                MedicalRecordID = temp.getId();
                tvFullTime.setText(String.valueOf(Integer.parseInt(temp.getSequenceNumber()) + 1));
            }
        }
    }

    /**
     * mPagerSlidingTabStrip默认值配置
     */
    private void initTabsValue() {
        tabs.setExpandTabs(Arrays.asList(0));
        tabs.setExpandMenuId(4, R.menu.pop_record_full);
        tabs.setExpandMenuIds(Arrays.asList(R.menu.pop_record_full), Arrays.<PopupMenu.OnMenuItemClickListener>asList(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int flag_change = 0;
                switch (item.getItemId()) {
                    case R.id.good:
                        flag_change = 0;
                        break;
                    case R.id.mid:
                        flag_change = 1;
                        break;
                    case R.id.bad:
                        flag_change = 2;
                        break;
                }
                //选择性的更新第一个tab
                pageAdapter.setFragmentsUpdateFlag(new boolean[]{true, false, false, false});
                //设置评价排序规则
                pageAdapter.setChangeType(flag_change);
                pageAdapter.notifyDataSetChanged();
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
