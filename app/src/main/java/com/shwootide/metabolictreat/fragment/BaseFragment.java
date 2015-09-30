package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.PreferenceUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 所有Fragment的基类
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public abstract class BaseFragment extends Fragment {
    @Bind(R.id.btn_fragment_back)
    Button btnBack;
    @Bind(R.id.btn_fragment_certain)
    Button btnCertain;
    @Bind(R.id.tv_full_doctor)
    TextView tvFullDoctor;
    @Bind(R.id.tv_full_bingren)
    TextView tvFullBingren;
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

    @OnClick(R.id.btn_fragment_back)
    void back() {
        getActivity().finish();
    }

    @OnClick(R.id.btn_fragment_certain)
    void certain() {
        certainSubmit();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(bindViewById(), null);
            ButterKnife.bind(this, view);
            //view 已经准备好去加载数据
            isPrepared = true;
            tvFullBingren.setText("张三");
            tvFullDoctor.setText(PreferenceUtil.getUser(getActivity(), Config.USERINFO).getUserName());
            loadData();
        }
        ButterKnife.bind(this, view);
        return view;
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
    public abstract void lazyLoad();

    /**
     * 捕捉事件
     */
    public abstract void onEventMainThread(MessageEvent event);

    /**
     * 通过id返回view
     *
     * @return
     */
    public abstract int bindViewById();

    /**
     * 确认按钮点击之后
     *
     * @return
     */
    public abstract void certainSubmit();
}
