package com.shwootide.metabolictreat.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.AuxiliaryPicAdapter;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * 辅助检查
 * Created by GMY on 2015/9/8 12:29.
 * Contact me via email gmyboy@qq.com.
 */
public class AuxiliaryCheckPicFragment extends BaseFragment {
    @Bind(R.id.gv_auxiliary)
    GridView gvAuxiliary;
    private ArrayList<String> paths;// 用于存放选取（拍照）图片的路径
    private AuxiliaryPicAdapter adapter;
    private final int RESULT_PHOTO = 1; // 使用照相机拍照获取图片
    private File file = null;//临时存放图片文件
    private String illnessid = "";

    public static AuxiliaryCheckPicFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        AuxiliaryCheckPicFragment fragment = new AuxiliaryCheckPicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = getArguments().getString("IllnessID");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        paths = new ArrayList<>();
        adapter = new AuxiliaryPicAdapter(getActivity(), paths);
        gvAuxiliary.setAdapter(adapter);
        gvAuxiliary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == paths.size()) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    String path = Config.IMG_PATH;
                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    file = new File(dir, System.currentTimeMillis() + ".jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, RESULT_PHOTO);
                } else {

                }
            }
        });
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void onEventMainThread(MessageEvent event) {

    }

    @Override
    public int bindViewById() {
        return R.layout.frag_auxiliarycheck_pic;
    }

    @Override
    public void certainSubmit() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == RESULT_PHOTO) {
                paths.add(file.getAbsolutePath());
                adapter.notifyDataSetChanged();
            } else {
                CommonUtil.showToast(getActivity(), "sdcard不可读");
            }
        }

    }
}
