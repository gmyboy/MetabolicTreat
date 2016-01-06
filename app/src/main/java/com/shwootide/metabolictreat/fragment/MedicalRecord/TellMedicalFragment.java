package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;
import com.shwootide.metabolictreat.ImagesShowActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.TellChuFangAdapter;
import com.shwootide.metabolictreat.adapter.TellMedicineAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.IllnessInfo;
import com.shwootide.metabolictreat.entity.IllnessSubmit;
import com.shwootide.metabolictreat.entity.LifeGuiding;
import com.shwootide.metabolictreat.entity.LifeGuiding_Submit;
import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 医嘱->治疗用药
 * Created by GMY on 2015/10/12 14:58.
 * Contact me via email gmyboy@qq.com.
 */
public class TellMedicalFragment extends BaseFragment {
    public static List<String> defaultDost = new ArrayList<>();

    static {
        defaultDost.add("g");
        defaultDost.add("mg");
        defaultDost.add("ug");
        defaultDost.add("片");
        defaultDost.add("粒");
    }

    //处方
    @Bind(R.id.lv_tellchufang1)
    ListView lvTellchufang1;
    @Bind(R.id.lv_tellchufang2)
    ListView lvTellchufang2;
    @Bind(R.id.spinner_tell_medical10)
    Spinner spinnerTellMedical10;
    @Bind(R.id.spinner_tell_medical11)
    Spinner spinnerTellMedical11;
    //用药
    @Bind(R.id.lv_tellmedical)
    ListView lvTellmedical;
    @Bind(R.id.spinner_tell_medical1)
    Spinner spinnerTellMedical1;
    @Bind(R.id.spinner_tell_medical2)
    Spinner spinnerTellMedical2;
    @Bind(R.id.spinner_tell_medical3)
    Spinner spinnerTellMedical3;
    @Bind(R.id.et_tell_medical4)
    TextView etTellMedical4;
    @Bind(R.id.tv_tell_medical5)
    TextView tvTellMedical5;
    @Bind(R.id.spinner_tell_medical6)
    Spinner spinnerTellMedical6;
    @Bind(R.id.spinner_tell_medical7)
    Spinner spinnerTellMedical7;
    @Bind(R.id.tv_tell_medical8)
    TextView tvTellMedical8;
    @Bind(R.id.et_tell_medical9)
    TextView etTellMedical9;
    @Bind(R.id.btn_tell_medical_next_date)
    Button btnTellMedicalNextDate;

    @OnClick({R.id.et_tell_medical4, R.id.tv_tell_medical5, R.id.tv_tell_medical8, R.id.et_tell_medical9})
    void editDialog() {
        showEditDialog(etTellMedical4, tvTellMedical5, etTellMedical9, tvTellMedical8, spinnerTellMedical6);
    }

    @OnClick(R.id.btn_tell_medical_next_date)
    void date(View v) {
        CommonUtil.showMaterialCalendarDialog(getActivity(), v);
    }

    public static TellMedicalFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        TellMedicalFragment fragment = new TellMedicalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerTellMedical10.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, getResources().getStringArray(R.array.medic_tell_zhidao_yinshi)));
        spinnerTellMedical11.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, getResources().getStringArray(R.array.medic_tell_zhidao_tundong)));
        spinnerTellMedical6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    spinnerTellMedical7.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, getResources().getStringArray(R.array.medic_syff)));
                    spinnerTellMedical7.performClick();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTellMedical7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0 && !TextUtils.isEmpty(etTellMedical4.getText().toString()) && !TextUtils.isEmpty(etTellMedical9.getText().toString())) {
                    CommonUtil.showToast(getActivity(), "点击确认就可以提交啦!!!");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void lazyLoad() {
        //获取所有疾病大类
        Map<String, String> params = new WeakHashMap<>();
//        params.put("IllnessID", illnessid);
        new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineMaxTypeNew", null, params);

        //获取饮食处方分类
        params = new WeakHashMap<>();
        new MutiFetcher(LifeGuiding[].class).fetch(getActivity(), "GetLifeGuiding1", null, params);

        //获取运动处方分类
        params = new WeakHashMap<>();
        new MutiFetcher(LifeGuiding[].class).fetch(getActivity(), "GetLifeGuiding2", null, params);

        if (position == 2) {//编辑
            //获取已有用药
            getMedicine();
            getChuFang1();
            getChuFang2();
            btnTellMedicalNextDate.setText(CommonUtil.parseForminnerStr(SysApplication.getInstance().getMedicalRecord().getNextDate()));
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("GetMedicineMaxTypeNew") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                spinnerTellMedical1.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                spinnerTellMedical1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            //获取所选疾病大类对应疾病小类
                            Map<String, String> params = new WeakHashMap<>();
//                            params.put("IllnessID", illnessid);
                            params.put("pid1", ((IllnessInfo) spinnerTellMedical1.getAdapter().getItem(position)).getId());
                            new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineMinTypeNew", "正在加载...", params);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        } else if (event.what.equals("GetMedicineMinTypeNew") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                spinnerTellMedical2.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                spinnerTellMedical2.performClick();
                spinnerTellMedical2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            //获取所选小类之后的药物名称
                            Map<String, String> params = new WeakHashMap<>();
//                            params.put("IllnessID", illnessid);
                            params.put("pid2", ((IllnessInfo) spinnerTellMedical2.getAdapter().getItem(position)).getId());
                            new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineTypeTradeNew", "正在加载...", params);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        } else if (event.what.equals("GetMedicineTypeTradeNew") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                mHasLoadedOnce = true;
                spinnerTellMedical3.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                spinnerTellMedical3.performClick();
                spinnerTellMedical3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            tvTellMedical5.setText(((IllnessInfo) spinnerTellMedical3.getAdapter().getItem(position)).getUnit());
                            tvTellMedical8.setText(((IllnessInfo) spinnerTellMedical3.getAdapter().getItem(position)).getDosage() + "*");
                            etTellMedical4.requestFocus();
                            //弹出同时输入剂量和guige *盒数的对话框
                            showEditDialog(etTellMedical4, tvTellMedical5, etTellMedical9, tvTellMedical8, spinnerTellMedical6);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        } else if (event.what.equals("GetLifeGuiding1") && isVisible) {//获取饮食处方分类
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                ArrayList<LifeGuiding> objects = new ArrayList(event.getObjects());
                objects.add(0, new LifeGuiding(CommonUtil.generateGUID(), "饮食处方"));
                spinnerTellMedical10.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, objects));
            }
        } else if (event.what.equals("GetLifeGuiding2") && isVisible) {//获取运动处方分类
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                ArrayList<LifeGuiding> objects = new ArrayList(event.getObjects());
                objects.add(0, new LifeGuiding(CommonUtil.generateGUID(), "运动处方"));
                spinnerTellMedical11.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, objects));
            }
        } else if (event.what.equals("GetMedicalRecordCure3") && isVisible) {//获取用药数据
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                TellMedicineAdapter adapter = new TellMedicineAdapter(getActivity(), event.getObjects());
                lvTellmedical.setAdapter(adapter);
            }
        } else if (event.what.equals("GetMedicalRecordLifeGuiding1") && isVisible) {//获取饮食处方数据
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                TellChuFangAdapter adapter = new TellChuFangAdapter(getActivity(), event.getObjects(), 1);
                lvTellchufang1.setAdapter(adapter);
                lvTellchufang1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        show();
                    }
                });
            }
        } else if (event.what.equals("GetMedicalRecordLifeGuiding2") && isVisible) {//获取运动处方数据
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                TellChuFangAdapter adapter = new TellChuFangAdapter(getActivity(), event.getObjects(), 2);
                lvTellchufang2.setAdapter(adapter);
                lvTellchufang2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        show();
                    }
                });
            }
        } else if (event.what.equals("AddMedicalRecordLifeGuiding") && isVisible) {//提交处方
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
                getChuFang1();
                getChuFang2();
                spinnerTellMedical10.setSelection(0);
                spinnerTellMedical11.setSelection(0);
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        } else if (event.what.equals("AddMedicalRecordCure") && isVisible) {//提交用药数据
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
                //刷新显示数据
                getMedicine();
                //清空已选数据
                etTellMedical4.setText("");
                etTellMedical9.setText("");
                //重置spinner
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        } else if (event.getWhat().equals("UpdateMedicalRecord") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        }
    }

    /**
     * 给spinner添加默认的数据
     *
     * @param event
     * @return
     */
    private ArrayList<IllnessInfo> addTitle(List event) {
        IllnessInfo illnessInfo = new IllnessInfo();
        illnessInfo.setName("点击选择");
        ArrayList<IllnessInfo> objects = new ArrayList(event);
        objects.add(0, illnessInfo);
        return objects;
    }

    /**
     * 跳转单独显示图片的界面
     */
    private void show() {
        Intent intent = new Intent(getActivity(), ImagesShowActivity.class);
        ArrayList<String> mList = new ArrayList<>();
        mList.add("http://test11.mypatroller.com/FilesInfo/fat.png");
        mList.add("http://test11.mypatroller.com/FilesInfo/LoseFlesh.bmp");
        mList.add("http://test11.mypatroller.com/FilesInfo/normal1.jpg");
        mList.add("http://test11.mypatroller.com/FilesInfo/normal2.gif");
        intent.putStringArrayListExtra("mlist", mList);
        intent.putExtra("pos", "0");
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.scale_in, android.R.anim.fade_out);
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_tell_medical;
    }

    @Override
    public void certainSubmit() {
        addNewChuFang();//提交处方
        addNewMedicine();//提交用药
        updateNextDate();//更新下次随访时间
    }

    /**
     * 更新下次随访时间
     */
    private void updateNextDate() {
        MedicalRecord medicalRecord = SysApplication.getInstance().getMedicalRecord();
        medicalRecord.setNextDate(btnTellMedicalNextDate.getText().toString());//开始时间
        medicalRecord.setNextEndDate(btnTellMedicalNextDate.getText().toString());//结束时间
        medicalRecord.setUSERREALNAME(null);
        new SingleFetcher(String.class).updateMedicalRecord(getActivity(), "正在提交...", medicalRecord);
    }

    /**
     * 添加一条处方信息
     */
    private void addNewChuFang() {
        if (spinnerTellMedical10.getSelectedItemId() != 0) {
            LifeGuiding_Submit guiding = new LifeGuiding_Submit();
            guiding.setId(CommonUtil.generateGUID());
            guiding.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            guiding.setLifeGuidingID(((LifeGuiding) spinnerTellMedical10.getSelectedItem()).getId());
            new SingleFetcher(String.class).addMedicalRecord_lifeguiding(getActivity(), "正在提交...", guiding);
        }
        if (spinnerTellMedical11.getSelectedItemId() != 0) {
            LifeGuiding_Submit guiding = new LifeGuiding_Submit();
            guiding.setId(CommonUtil.generateGUID());
            guiding.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            guiding.setLifeGuidingID(((LifeGuiding) spinnerTellMedical11.getSelectedItem()).getId());
            new SingleFetcher(String.class).addMedicalRecord_lifeguiding(getActivity(), "正在提交...", guiding);
        }
    }

    /**
     * 添加一条用药记录
     */
    private void addNewMedicine() {
        if (!TextUtils.isEmpty(etTellMedical4.getText().toString()) && !TextUtils.isEmpty(tvTellMedical8.getText().toString())) {
            IllnessSubmit illnessSubmit = new IllnessSubmit();
            illnessSubmit.setId(CommonUtil.generateGUID());
            illnessSubmit.setFlg("2");
            illnessSubmit.setMTypeParentID(((IllnessInfo) spinnerTellMedical1.getSelectedItem()).getId());
            illnessSubmit.setMTypeID(((IllnessInfo) spinnerTellMedical2.getSelectedItem()).getId());
            illnessSubmit.setMTTradeID(((IllnessInfo) spinnerTellMedical3.getSelectedItem()).getId());
            illnessSubmit.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            //加上了单位
            illnessSubmit.setDosage(etTellMedical4.getText().toString() + tvTellMedical5.getText().toString());
            //缺少规格*盒数
            illnessSubmit.setBox(tvTellMedical8.getText().toString() + etTellMedical9.getText().toString());
            illnessSubmit.setTimesaDay(spinnerTellMedical6.getSelectedItem().toString());
            illnessSubmit.setWay(spinnerTellMedical7.getSelectedItem().toString());
            new SingleFetcher(String.class).addMedicalRecord_cure(getActivity(), "正在提交...", illnessSubmit);
        }
    }

    /**
     * 获取已有饮食处方
     */
    public void getChuFang1() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
        new MutiFetcher(LifeGuiding[].class).fetch(getActivity(), "GetMedicalRecordLifeGuiding1", null, params);
    }

    /**
     * 获取已有运动处方
     */
    public void getChuFang2() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
        new MutiFetcher(LifeGuiding[].class).fetch(getActivity(), "GetMedicalRecordLifeGuiding2", null, params);
    }

    /**
     * 获取已有用药
     */
    public void getMedicine() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
        params.put("flg", "2");
        new MutiFetcher(IllnessSubmit[].class).fetch(getActivity(), "GetMedicalRecordCure3", null, params);
    }

    /**
     * 显示剂量编辑框的dialog
     *
     * @param view1      显示剂量编辑框
     * @param viewheshu1 显示剂量单位编辑框
     * @param view2      显示盒数的编辑框
     * @param viewheshu2 显示规格的编辑框
     * @param nextView   下一个要弹出的spinner
     */
    public void showEditDialog(final View view1, final View viewheshu1, final View view2, final View viewheshu2, final View nextView) {
        if (!(view1 instanceof TextView) || !(viewheshu1 instanceof TextView)) {
            return;
        }
        if (!(view2 instanceof TextView) || !(viewheshu2 instanceof TextView)) {
            return;
        }
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_number_complex, null);
        final NumberPicker np1 = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex1);
        final NumberPicker np2 = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex2);
        final NumberPicker np3 = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex3);
        final NumberPicker np4 = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex4);
        final TextView tvDian = (TextView) rootView.findViewById(R.id.tv_dialog_complex_dian);
        final NumberPicker little = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex_little);
        final EditText et = (EditText) rootView.findViewById(R.id.et_dialog_complex);
        IllnessInfo illnessInfo = (IllnessInfo) spinnerTellMedical3.getSelectedItem();
        //诺和力剂量需要小数点
        if (illnessInfo.getId().equals("1022")) {
            tvDian.setVisibility(View.VISIBLE);
            little.setVisibility(View.VISIBLE);
        }

        final TextView tv = (TextView) rootView.findViewById(R.id.tv_dialog_dost);
        final NumberPicker np5 = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex5);
        final NumberPicker np6 = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex6);
        final NumberPicker np7 = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex7);
        np1.setMaxValue(5);
        np1.setMinValue(0);
        np2.setMaxValue(9);
        np2.setMinValue(0);
        np3.setMaxValue(9);
        np3.setMinValue(0);
        little.setMinValue(0);
        little.setMaxValue(9);
        np5.setMaxValue(5);
        np5.setMinValue(0);
        np6.setMaxValue(9);
        np6.setMinValue(0);
        np7.setMaxValue(9);
        np7.setMinValue(0);
        //设置规格的数据
        if (!TextUtils.isEmpty(((TextView) viewheshu2).getText())) {
            tv.setText(((TextView) viewheshu2).getText());
        }
        //如果剂量数值是double类型
        if (CommonUtil.isStr2Num(((TextView) view1).getText().toString())) {
            final double defaultValues1 = TextUtils.isEmpty(((TextView) view1).getText()) ? 000.00 : Double.parseDouble(((TextView) view1).getText().toString());
            np1.setValue((int) (defaultValues1 / 100));
            np2.setValue((int) (defaultValues1 * 100 % 10000 / 1000));
            np3.setValue((int) (defaultValues1 * 100 % 1000 / 100));
            little.setValue((int) (defaultValues1 * 100 % 100 / 10));
        } else {
            et.setText(((TextView) view1).getText());
        }
        //如果固定单位数组中没有新的，则添加
        if (!defaultDost.contains(((TextView) viewheshu1).getText())) {
            defaultDost.add(((TextView) viewheshu1).getText().toString());
        }
        //默认的索引
        int index = defaultDost.indexOf(((TextView) viewheshu1).getText().toString());
        np4.setDisplayedValues(defaultDost.toArray(new String[defaultDost.size()]));
        np4.setMinValue(0);
        np4.setMaxValue(defaultDost.size() - 1);
        np4.setValue(index);

        final double defaultValues2 = TextUtils.isEmpty(((TextView) view2).getText()) ? 000.00 : Double.parseDouble(((TextView) view2).getText().toString());
        np5.setValue((int) (defaultValues2 / 100));
        np6.setValue((int) (defaultValues2 * 100 % 10000 / 1000));
        np7.setValue((int) (defaultValues2 * 100 % 1000 / 100));
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .customView(rootView, false)
                .autoDismiss(false)
                .cancelable(false)
                .positiveText("确定")
                .negativeText("取消")
                .neutralText("重置")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        String aValue1, aValue1_dian = "", aValue2;
                        if (view1 instanceof TextView && view2 instanceof TextView) {
                            //格式化小数点之前的数
                            if (np1.getValue() == 0) {
                                if (np2.getValue() == 0) {
                                    aValue1 = String.valueOf(np3.getValue());
                                } else {
                                    aValue1 = String.valueOf(np2.getValue()) + String.valueOf(np3.getValue());
                                }
                            } else {
                                aValue1 = String.valueOf(np1.getValue()) + String.valueOf(np2.getValue()) + String.valueOf(np3.getValue());
                            }
                            //格式化小数点之后的数
                            if (little.getValue() != 0) {
                                aValue1_dian = "." + little.getValue();
                            }
                            //格式化小数点之前的数
                            if (np5.getValue() == 0) {
                                if (np6.getValue() == 0) {
                                    aValue2 = String.valueOf(np7.getValue());
                                } else {
                                    aValue2 = String.valueOf(np6.getValue()) + String.valueOf(np7.getValue());
                                }
                            } else {
                                aValue2 = String.valueOf(np5.getValue()) + String.valueOf(np6.getValue()) + String.valueOf(np7.getValue());
                            }
                            ((TextView) view1).setText(aValue1 + aValue1_dian);
                            ((TextView) view2).setText(aValue2);
                            CommonUtil.hideSoftInputView(getActivity(), np1);
                            CommonUtil.hideSoftInputView(getActivity(), np2);
                            CommonUtil.hideSoftInputView(getActivity(), np3);
                            CommonUtil.hideSoftInputView(getActivity(), np4);
                            CommonUtil.hideSoftInputView(getActivity(), np5);
                            CommonUtil.hideSoftInputView(getActivity(), np6);
                            CommonUtil.hideSoftInputView(getActivity(), np7);
                            dialog.dismiss();
                            if (!TextUtils.isEmpty(et.getText())) {
                                ((TextView) view1).setText(et.getText());
                            } else {
                                spinnerTellMedical6.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, getResources().getStringArray(R.array.medic_blfy)));
                                nextView.performClick();
                            }
                        }
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        np1.setValue(0);
                        np2.setValue(0);
                        np3.setValue(0);
                        np5.setValue(0);
                        np6.setValue(0);
                        np7.setValue(0);
                        et.setText("");
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        CommonUtil.hideSoftInputView(getActivity(), np1);
                        CommonUtil.hideSoftInputView(getActivity(), np2);
                        CommonUtil.hideSoftInputView(getActivity(), np3);
                        CommonUtil.hideSoftInputView(getActivity(), np4);
                        CommonUtil.hideSoftInputView(getActivity(), np5);
                        CommonUtil.hideSoftInputView(getActivity(), np6);
                        CommonUtil.hideSoftInputView(getActivity(), np7);
                        CommonUtil.hideSoftInputView(getActivity(), et);
                        dialog.dismiss();
                    }
                })
                .build();
        dialog.show();
        //关闭自动弹出输入法
        CommonUtil.resetDialogParam(getActivity(), dialog);
    }
}
