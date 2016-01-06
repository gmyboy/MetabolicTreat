package com.shwootide.metabolictreat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.adapter.FollowUPAdapter;
import com.shwootide.metabolictreat.adapter.FollowUPSelectLAdapter;
import com.shwootide.metabolictreat.adapter.FollowUPSelectRAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.FollowSelect;
import com.shwootide.metabolictreat.entity.MedicalRecordCheckup;
import com.shwootide.metabolictreat.entity.SequenceNum;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 随访主界面
 * Created by gmy on 15/11/7.
 * Email me via gmyboy@qq.com
 */
public class FollowUpActivity extends BaseActivity {

    @Bind(R.id.tv_follow_item2)
    TextView tvFollowItem2;
    @Bind(R.id.tv_follow_item3)
    TextView tvFollowItem3;
    @Bind(R.id.tv_follow_item4)
    TextView tvFollowItem4;
    @Bind(R.id.btn_follow_reset)
    Button btnFollowReset;
    @Bind(R.id.lv_follow)
    ListView lvFollow;
    @Bind(R.id.btn_follow_dial)
    Button btnFollowDial;
    @Bind(R.id.tv_follow_name)
    TextView tvFollowName;
    @Bind(R.id.tv_full_jibing)
    TextView tvFullJibing;
    @Bind(R.id.spinner_follow_1)
    Button spinnerFollow1;
    @Bind(R.id.spinner_follow_2)
    Button spinnerFollow2;

    private String illnessid;
    private FollowUPAdapter mainAdapter;//主页面的adapter
    private PopupWindow pop_l, pop_r;//弹出的多选框
    private int width_l, width_r;//pop的宽度
    private ListView lv_pop_l, lv_pop_r;//弹出框中的listview
    private FollowUPSelectLAdapter adapter_l;
    private FollowUPSelectRAdapter adapter_r;
    private List<FollowSelect> checkedItems;//保存右边选中的三个

    @OnClick(R.id.btn_follow_dial)
    void dial() {
        String number = SysApplication.getInstance().getmRecord().getTel();
        if (!TextUtils.isEmpty(number)) {
            Intent mIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(mIntent);
        } else {
            showToast("他还没有号码");
        }
    }

    @OnClick(R.id.btn_follow_reset)
    void reset() {
        if (adapter_l != null)
            for (int i = 0; i < adapter_l.getCount(); i++) {
                adapter_l.setChecked(i, false);
                lv_pop_l.setItemChecked(i, false);
            }
        if (adapter_r != null)
            for (int i = 0; i < adapter_r.getCount(); i++) {
                adapter_r.setChecked(i, false);
                lv_pop_r.setItemChecked(i, false);
            }
        if (mainAdapter != null)
            mainAdapter.clearAll();
        tvFollowItem2.setText("待选择");
        tvFollowItem3.setText("待选择");
        tvFollowItem4.setText("待选择");
    }

    @OnClick(R.id.spinner_follow_1)
    void showPop_l(View v) {
        showLPopup(v);
    }

    @OnClick(R.id.spinner_follow_2)
    void showPop_R(View v) {
        showRPopup(v);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_followup);
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
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
        illnessid = getIntent().getStringExtra("IllnessID");
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
        tvFollowName.setText(SysApplication.getInstance().getmRecord().getName());
        spinnerFollow1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width_l = spinnerFollow1.getMeasuredWidth();
                spinnerFollow1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                initLPopup();
            }
        });
        spinnerFollow2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width_r = spinnerFollow2.getMeasuredWidth();
                spinnerFollow2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                initRPopup();
            }
        });
    }

    /**
     * 初始化popup
     */
    private void initLPopup() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.view_popup_followup, null);
        lv_pop_l = (ListView) contentView.findViewById(R.id.lv_followup_pop);
        lv_pop_l.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        pop_l = new PopupWindow(contentView, width_l, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop_l.setAnimationStyle(R.style.PopUpAnimation);
        pop_l.setBackgroundDrawable(new BitmapDrawable());
        initLDataNum();
    }

    /**
     * 初始化popup
     */
    private void initRPopup() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.view_popup_followup, null);
        lv_pop_r = (ListView) contentView.findViewById(R.id.lv_followup_pop);
        lv_pop_r.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        pop_r = new PopupWindow(contentView, width_r, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop_r.setAnimationStyle(R.style.PopUpAnimation);
        pop_r.setBackgroundDrawable(new BitmapDrawable());
        initRDataNum();
    }

    private void initLDataNum() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("PersonID", SysApplication.getInstance().getmRecord().getId());
        params.put("HospitalID", getmUserInfo().getHospitalID());
        params.put("IllnessID", illnessid);
        new MutiFetcher(SequenceNum[].class).fetch(mContext, "GetAllMedicalRecordSequenceNumber", "正在加载", params);
    }

    private void initRDataNum() {
        adapter_r = new FollowUPSelectRAdapter(FollowUpActivity.this, CommonUtil.generatDate(illnessid));
        lv_pop_r.setAdapter(adapter_r);
        lv_pop_r.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter_r.setChecked(position);
                changeData();
            }
        });
    }

    private void changeData() {
        int checkedItemCount = adapter_r.getCheckedItemsCount();
        if (checkedItemCount == 0) {
            tvFollowItem2.setText("待选择");
            tvFollowItem3.setText("待选择");
            tvFollowItem4.setText("待选择");
            if (mainAdapter != null)
                mainAdapter.clearAll();
        } else if (checkedItemCount == 1) {
            checkedItems = adapter_r.getCheckedItems();
            tvFollowItem2.setText(checkedItems.get(0).getName() + " (" + checkedItems.get(0).getDos() + ") ");
            tvFollowItem3.setText("待选择");
            tvFollowItem4.setText("待选择");
            getData(checkedItems.get(0), null, null);
        } else if (checkedItemCount == 2) {
            checkedItems = adapter_r.getCheckedItems();
            tvFollowItem2.setText(checkedItems.get(0).getName() + " (" + checkedItems.get(0).getDos() + ") ");
            tvFollowItem3.setText(checkedItems.get(1).getName() + " (" + checkedItems.get(1).getDos() + ") ");
            tvFollowItem4.setText("待选择");
            getData(checkedItems.get(0), checkedItems.get(1), null);
        } else if (checkedItemCount == 3) {
            checkedItems = adapter_r.getCheckedItems();
            tvFollowItem2.setText(checkedItems.get(0).getName() + " (" + checkedItems.get(0).getDos() + ") ");
            tvFollowItem3.setText(checkedItems.get(1).getName() + " (" + checkedItems.get(1).getDos() + ") ");
            tvFollowItem4.setText(checkedItems.get(2).getName() + " (" + checkedItems.get(2).getDos() + ") ");
            getData(checkedItems.get(0), checkedItems.get(1), checkedItems.get(2));
        } else {

        }
    }

    /**
     * 拼接sql 获取数据
     *
     * @param a 第一个参数
     * @param b 第2个参数
     * @param c 第三个参数
     */
    private void getData(FollowSelect a, FollowSelect b, FollowSelect c) {
        if (adapter_l == null) return;
        if (adapter_l.getCheckedItemsCount() == 0) return;
        List<SequenceNum> checkedItems = adapter_l.getCheckedItems();
        String inSql = "";
        for (int i = 0; i < checkedItems.size(); i++) {
            if (!checkedItems.get(i).getId().equals("")) {
                inSql += "'" + checkedItems.get(i).getId() + "'";
                if (i < checkedItems.size() - 1) {//去掉最后一个
                    inSql += ",";
                }
            }
        }
        String whereSql = "' AND IllnessID = '" + illnessid + "' AND MedicalRecordID IN (" + inSql + ")";
        String sql = "";
        if (a != null && b == null & c == null) {
            String selectSqla = " SELECT *  FROM  MedicalRecordCheckup WHERE type = '" + a.getType() + whereSql;
            sql = "SELECT a." + a.getTitle() + ",MedicalRecord.SequenceNumber FROM ( " + selectSqla + " ) AS a "
                    + " LEFT JOIN MedicalRecord ON MedicalRecord.id = a.MedicalRecordID "
                    + " WHERE MedicalRecord.id IN (" + inSql + ") ORDER BY MedicalRecord.SequenceNumber";
        }
        if (a != null && b != null && c == null) {
            String selectSqla = " SELECT *  FROM  MedicalRecordCheckup WHERE type = '" + a.getType() + whereSql;
            String selectSqlb = " SELECT *  FROM  MedicalRecordCheckup WHERE type = '" + b.getType() + whereSql;
            sql = "SELECT a." + a.getTitle() + ",b." + b.getTitle() + ",MedicalRecord.SequenceNumber FROM ( " + selectSqla + " ) AS a "
                    + " LEFT JOIN ( " + selectSqlb + " ) AS  b ON 1=1 "
                    + " LEFT JOIN MedicalRecord ON MedicalRecord.id = a.MedicalRecordID AND MedicalRecord.id = b.MedicalRecordID "
                    + " WHERE MedicalRecord.id IN (" + inSql + ") ORDER BY MedicalRecord.SequenceNumber";
        }
        if (a != null && b != null & c != null) {
            String selectSqla = " SELECT *  FROM  MedicalRecordCheckup WHERE type = '" + a.getType() + whereSql;
            String selectSqlb = " SELECT *  FROM  MedicalRecordCheckup WHERE type = '" + b.getType() + whereSql;
            String selectSqlc = " SELECT *  FROM  MedicalRecordCheckup WHERE type = '" + c.getType() + whereSql;
            sql = "SELECT a." + a.getTitle() + ",b." + b.getTitle() + ",c." + c.getTitle() + ",MedicalRecord.SequenceNumber FROM ( " + selectSqla + " ) AS a "
                    + " LEFT JOIN ( " + selectSqlb + " ) AS  b ON 1=1 "
                    + " LEFT JOIN ( " + selectSqlc + " ) AS  c ON 1=1 "
                    + " LEFT JOIN MedicalRecord ON MedicalRecord.id = a.MedicalRecordID AND MedicalRecord.id = b.MedicalRecordID "
                    + " AND MedicalRecord.id = c.MedicalRecordID"
                    + " WHERE MedicalRecord.id IN (" + inSql + ") ORDER BY MedicalRecord.SequenceNumber";
        }
        Map<String, String> params = new WeakHashMap<>();
        params.put("strInfo", sql);
        new MutiFetcher(MedicalRecordCheckup[].class).fetch(mContext, "GetMedicalRecordCheckupFields2", "正在查询...", params);
    }

    /**
     * 显示左边pop
     *
     * @param v
     */
    private void showLPopup(View v) {
        pop_l.showAsDropDown(v);
        pop_l.setFocusable(true);
        pop_l.setOutsideTouchable(true);
        pop_l.update();
        if (pop_l.isShowing()) {
        }
    }

    /**
     * 显示右边pop
     *
     * @param v
     */
    private void showRPopup(View v) {
        pop_r.showAsDropDown(v);
        pop_r.setFocusable(true);
        pop_r.setOutsideTouchable(true);
        pop_r.update();
        if (pop_r.isShowing()) {
        }
    }

    /**
     * 接收数据
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.getWhat().equals("GetAllMedicalRecordSequenceNumber")) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
//                lv_pop.setAdapter(new ArrayAdapter<>(FollowUpActivity.this, android.R.layout.simple_list_item_multiple_choice, event.getObjects()));
                adapter_l = new FollowUPSelectLAdapter(this, event.getObjects());
                lv_pop_l.setAdapter(adapter_l);
                lv_pop_l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        adapter_l.setChecked(position);
                        if (adapter_r.getCheckedItemsCount() != 0)
                            changeData();
                    }
                });
            }
        } else if (event.getWhat().equals("GetMedicalRecordCheckupFields2")) {
            if (event.getCode().equals("200") && event.getObjects() != null) {
                if (event.getObjects().size() == 0) {
                    showToast("本次就诊没有录入该检查内容");
                } else {
                    mainAdapter = new FollowUPAdapter(FollowUpActivity.this, event.getObjects(), checkedItems);
                    lvFollow.setAdapter(mainAdapter);
                }
            }
        }
    }
}
