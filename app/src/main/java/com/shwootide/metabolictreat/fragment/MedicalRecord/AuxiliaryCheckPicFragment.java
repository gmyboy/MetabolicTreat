package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.AuxiliaryPicAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.PicInfo;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.ImageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;

/**
 * 辅助检查
 * Created by GMY on 2015/9/8 12:29.
 * Contact me via email gmyboy@qq.com.
 */
public class AuxiliaryCheckPicFragment extends BaseFragment {
    @Bind(R.id.gv_auxiliary)
    GridView gvAuxiliary;

    private List<PicInfo> paths;// 用于存放选取（拍照）图片的路径
    private AuxiliaryPicAdapter adapter;
    private final int RESULT_PHOTO = 1; // 使用照相机拍照获取图片
    private File file = null;//临时存放图片文件
    private int flag;//用于区分是辅助检查3 还是实验室检查2
    private MaterialDialog dialog;//拍照返回显示的dialog
    //dialog相关布局
    private View d_v;
    private ImageView d_iv;
    private Button d_btn;
    private EditText d_et;
    private Button d_reset;

    public static AuxiliaryCheckPicFragment newInstance(String illnessid, int position, int i, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", position);
        args.putInt("Flag", i);
        args.putInt("SequenceNumber", mSequenceNumber);
        AuxiliaryCheckPicFragment fragment = new AuxiliaryCheckPicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getArguments().getInt("Flag");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCertain.setClickable(false);
        paths = new ArrayList<>();
        adapter = new AuxiliaryPicAdapter(getActivity(), paths);
        gvAuxiliary.setAdapter(adapter);
        initDialog();
        adapter.setPicClickListener(new AuxiliaryPicAdapter.onPicClickListener() {
            @Override
            public void onPick(final int position, boolean isLast) {
                if (isLast) {
                    getPicture();
                } else {
                    final PicInfo picInfo = adapter.getItem(position);
                    setDialogView(d_iv, d_btn, d_et, picInfo);
                    dialog.getBuilder().callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            picInfo.setFileName(file.getName());
                            picInfo.setLocal_path(file.getAbsolutePath());
                            picInfo.setCheckDate(d_btn.getText().toString().trim());
                            picInfo.setTitleName(d_et.getText().toString().trim());
                            new SingleFetcher(String.class).updatePic(getActivity(), "UpdateMedicalRecordInfoFile", "正在修改...", picInfo);
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            super.onPositive(dialog);
                            dialog.dismiss();
                        }
                    });
                    showDialog();
                }
            }
        });
    }

    /**
     * 跳转拍照界面
     */
    private void getPicture() {
//        File dir = new File(Config.IMG_PATH);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        file = new File(dir, System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, RESULT_PHOTO);
    }

    /**
     * 初始化拍照返回dialog
     */
    private void initDialog() {
        d_v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_auxiliarypic_dialog, null);
        d_iv = (ImageView) d_v.findViewById(R.id.iv_item_pic);
        d_btn = (Button) d_v.findViewById(R.id.btn_item_date);
        d_et = (EditText) d_v.findViewById(R.id.iv_item_more);
//        d_et.clearFocus();
        d_reset = (Button) d_v.findViewById(R.id.btn_item_reset);
        d_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        d_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicture();
            }
        });
        dialog = new MaterialDialog.Builder(getActivity())
                .autoDismiss(false)
                .cancelable(false)
                .title("编辑图片")
                .customView(d_v, false)
                .positiveText("确定并上传")
                .negativeText("取消")
                .build();
    }

    @Override
    public void lazyLoad() {
        if (position != 2) {//初诊
        } else {//编辑
            getServerPicture();
        }
    }

    /**
     * 获取服务器图片信息
     */
    private void getServerPicture() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
        new MutiFetcher(PicInfo[].class).fetch(getActivity(), flag == 3 ? "GetMedicalRecordInfoFile2" : "GetMedicalRecordInfoFile1", "正在查询...", params);
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if ((event.getWhat().equals("GetMedicalRecordInfoFile1") || event.getWhat().equals("GetMedicalRecordInfoFile2")) && isVisible) {//获取已有图片
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                adapter.setDatas(event.getObjects());
                adapter.setImgType(AuxiliaryPicAdapter.FROM_SERVER);
                gvAuxiliary.setAdapter(adapter);
            }
        } else if (event.getWhat().equals("AddMedicalRecordInfoFile") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "上传成功");
                getServerPicture();
                if (dialog.isShowing()) dialog.dismiss();
            } else {
                CommonUtil.showToast(getActivity(), "上传失败");
            }
        } else if (event.getWhat().equals("UpdateMedicalRecordInfoFile") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "修改成功");
                getServerPicture();
                if (dialog.isShowing()) dialog.dismiss();
            } else {
                CommonUtil.showToast(getActivity(), "修改失败");
            }
        }
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_auxiliarycheck_pic;
    }

    @Override
    public void certainSubmit() {
        addNewPic();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == RESULT_PHOTO) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                file = ImageUtil.savePhotoZoom(photo, Config.IMG_PATH, System.currentTimeMillis() + ".jpg", getActivity());
                setDialogView(d_iv, d_btn, file.getAbsolutePath());
                dialog.getBuilder().callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        if (TextUtils.isEmpty(d_et.getText())) {
                            CommonUtil.showToast(getActivity(), "请输入备注信息");
                        } else if (d_btn.getText().toString().contains("点击添加日期")) {
                            CommonUtil.showToast(getActivity(), "请选择日期");
                        } else if (SysApplication.getInstance().getMedicalRecord() == null) {
                            addMedicalRecord();
                        } else {
                            addNewPic();
                        }
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        dialog.dismiss();
                    }
                });
                showDialog();
            } else {
                CommonUtil.showToast(getActivity(), "sdcard不可读");
            }
        }
    }

    private void addNewPic() {
        PicInfo picInfo = new PicInfo();
        picInfo.setId(CommonUtil.generateGUID());
        picInfo.setFlg(String.valueOf(flag));//辅助检查 3  实验室 2
        picInfo.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
        picInfo.setStandardTypeID(String.valueOf(flag));//辅助检查 3  实验室 2
        picInfo.setFileName(file.getName());
        picInfo.setLocal_path(file.getAbsolutePath());
        picInfo.setCheckDate(d_btn.getText().toString().trim());
        picInfo.setTitleName(d_et.getText().toString().trim());
        paths.add(picInfo);
        new SingleFetcher(String.class).upload(getActivity(), "AddMedicalRecordInfoFile", "正在提交...", picInfo);
    }

    /**
     * 设置dialog中的数据
     *
     * @param imageView
     * @param
     */
    private void setDialogView(ImageView imageView, Button button, String path) {
        Glide.with(getActivity()).load(path).into(imageView);
        button.setText("点击添加日期");
//        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        // 设置为ture只获取图片大小
//        opts.inJustDecodeBounds = true;
//        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        try {
//            imageView.setImageBitmap(ImageUtil.getBitmapByPath(path, opts, width, height));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 设置dialog中的数据
     *
     * @param imageView
     * @param
     */
    private void setDialogView(ImageView imageView, Button button, EditText d_et, PicInfo item) {
        Glide.with(getActivity()).load(Config.IMG_SERVER_PATH + item.getFileName()).override(200, 100).into(imageView);
        button.setText(CommonUtil.parseForminnerStr(item.getCheckDate()));
        d_et.setText(item.getTitleName());
    }

    /**
     * show之后重设dialog高度
     */
    private void showDialog() {
        dialog.show();
        //加高dialog的高度
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.height = (int) (display.getHeight()); //设置宽度
        dialog.getWindow().setAttributes(lp);
        //关闭自动弹出输入法
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
