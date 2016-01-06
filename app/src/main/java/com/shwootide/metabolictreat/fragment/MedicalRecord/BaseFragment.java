package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.entity.SequenceNumber;
import com.shwootide.metabolictreat.entity.UserInfo;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.PreferenceUtil;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 所有Fragment的基类
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class BaseFragment extends Fragment {
    @Bind(R.id.btn_fragment_back)
    Button btnBack;
    @Bind(R.id.btn_fragment_certain)
    Button btnCertain;
    @Bind(R.id.btn_history_look)
    Button btnHistory;
    @Bind(R.id.et_other)
    public TextView mEtOther;
    @Bind(R.id.tv_full_doctor)
    TextView tvFullDoctor;
    @Bind(R.id.tv_full_bingren)
    TextView tvFullBingren;

    @OnClick(R.id.et_other)
    void showOther(View v) {
        CommonUtil.showEditDialog(getActivity(), v, mEtOther.getText().toString());
    }

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    public boolean mHasLoadedOnce;
    private View view;
    /**
     * 保存登录医师基本信息
     */
    private UserInfo mUserInfo;
    /**
     * 疾病id
     */
    public String illnessid = "";
    /**
     * 区分初诊 0 复诊 1 编辑 2
     */
    public int position;
    public int sequenceNumber = 1;
    private MedicalRecord medicalRecord;

    @OnClick(R.id.btn_fragment_back)
    void back() {
        getActivity().finish();
    }

    @OnClick(R.id.btn_fragment_certain)
    void certain() {
        Submit();
    }

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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = getArguments().getString("IllnessID");
        position = getArguments().getInt("Position");
        sequenceNumber = getArguments().getInt("SequenceNumber");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(bindViewById(), null);
            ButterKnife.bind(this, view);
            //view 已经准备好去加载数据
            isPrepared = true;
            tvFullBingren.setText(SysApplication.getInstance().getmRecord().getName());
            if (position != 2) {
                tvFullDoctor.setText(getmUserInfo().getUserName());
            } else {
                tvFullDoctor.setText(SysApplication.getInstance().getMedicalRecord().getInputUser());
            }
            loadData();
        }
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 获取登录医师的信息
     *
     * @return
     */
    public UserInfo getmUserInfo() {
        if (mUserInfo == null)
            mUserInfo = PreferenceUtil.getUser(getActivity(), "UserInfo");
        return mUserInfo;
    }

    public void setmUserInfo(UserInfo mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    /**
     * 加载数据
     */
    protected void loadData() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 可见
     */
    protected void onVisible() {
        loadData();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    /**
     * 延迟加载 子类必须重写此方法
     */
    public void lazyLoad() {

    }

    /**
     * 捕捉事件
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.getWhat().equals("AddMedicalRecord") && isVisible) {
            if (event.getCode().equals("200")) {
//                CommonUtil.showToast(getActivity(), "添加基本信息主表成功");
                //写入app
                SysApplication.getInstance().setMedicalRecord(medicalRecord);
                certainSubmit();
            } else {
                showMsgDialog("创建就诊信息失败", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            Submit();
                        }
                    }
                });
            }
        }
    }

    /**
     * 通过id返回view
     *
     * @return
     */
    public int bindViewById() {
        return 0;
    }

    public void Submit() {
        if (position != 2) {//初诊+复诊
            if (SysApplication.getInstance().getMedicalRecord() == null) {
                addMedicalRecord();
            } else {
                certainSubmit();
            }
        } else {
            certainSubmit();
        }
    }

    /**
     * 确认按钮点击之后
     *
     * @return
     */
    public void certainSubmit() {

    }

    /**
     * 添加一条就诊基本主表信息记录
     */
    public void addMedicalRecord() {
        medicalRecord = new MedicalRecord();
        medicalRecord.setId(CommonUtil.generateGUID());
        medicalRecord.setPersonID(SysApplication.getInstance().getmRecord().getId());
        medicalRecord.setRecordDate(CommonUtil.getSysDate());
        medicalRecord.setRecordNo(SysApplication.getInstance().getmRecord().getMedicalRecordNo());//就诊编号
        medicalRecord.setHospitalID(getmUserInfo().getHospitalID());
        medicalRecord.setDepartmentID(getmUserInfo().getDepartmentID());
        medicalRecord.setInputDate(CommonUtil.getSysDate());
        medicalRecord.setInputUser(String.valueOf(getmUserInfo().getUserID()));
        medicalRecord.setBz("");
        medicalRecord.setStaffID(getmUserInfo().getStaffID());
        medicalRecord.setDiagnose("");
        medicalRecord.setComplication("");
        medicalRecord.setSequenceNumber(String.valueOf(sequenceNumber + 1));
        medicalRecord.setIllnessID(illnessid);
        medicalRecord.setNextDate(CommonUtil.getSysDate());//带更改
        medicalRecord.setNextEndDate(CommonUtil.getSysDate());
        medicalRecord.setOther1(TextUtils.isEmpty(SysApplication.getInstance().getAddress()) ? "" : SysApplication.getInstance().getAddress());
        new SingleFetcher(String.class).addMedicalRecord(getActivity(), null, medicalRecord);
    }

    public void showMsgDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title("提示信息")
                .content(msg)
                .positiveText("重试")
                .negativeText("取消")
                .onPositive(callback)
                .onNegative(callback)
                .build();
        dialog.show();
    }

}
