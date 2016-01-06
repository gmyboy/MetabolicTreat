package com.shwootide.metabolictreat.fragment.Common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shwootide.metabolictreat.entity.UserInfo;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.PreferenceUtil;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by GMY on 2015/12/18 14:52.
 * Contact me via email igmyboy@gmail.com.
 */
public class MenuFragment extends Fragment {
    private UserInfo mUserInfo;
    private View view;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(bindViewById(), null);
            ButterKnife.bind(this, view);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 通过id返回view
     *
     * @return
     */
    public int bindViewById() {
        return 0;
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
     * 显示toast
     *
     * @param massage
     */
    public void showToast(String massage) {
        Toast.makeText(getActivity(), massage, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            this.update();
//        }
//    }



    public void onEventMainThread(MessageEvent event) {

    }

    /**
     * 刷新数据
     */
    public void update() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
