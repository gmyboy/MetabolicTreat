package com.shwootide.metabolictreat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.shwootide.metabolictreat.fragment.AuxiliaryCheckPicFragment;
import com.shwootide.metabolictreat.fragment.AuxiliaryCheckPrintFragment;
import com.shwootide.metabolictreat.fragment.DiagnosisFragment;
import com.shwootide.metabolictreat.fragment.LabCheckPicFragment;
import com.shwootide.metabolictreat.fragment.PhysicalFragment;
import com.shwootide.metabolictreat.fragment.TellFragment;

/**
 * 诊断页tab适配器
 * Created by GMY on 2015/8/25 09:50.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisPageAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"体格检查", "实验室检查", "辅助检查", "诊断", "医嘱"};
    private String illnessid = "";
    private FragmentManager fm;
    private boolean[] fragmentsUpdateFlag = {false, false, false, false, false};
    private int changeType;//0拍照   1 录入

    public DiagnosisPageAdapter(FragmentManager fm, String illnessid) {
        super(fm);
        this.illnessid = illnessid;
        this.fm = fm;
    }

    public boolean[] getFragmentsUpdateFlag() {
        return fragmentsUpdateFlag;
    }

    public void setFragmentsUpdateFlag(boolean[] fragmentsUpdateFlag) {
        this.fragmentsUpdateFlag = fragmentsUpdateFlag;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        //得到tag
        String fragmentTag = fragment.getTag();

        if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
            //如果这个fragment需要更新
            FragmentTransaction ft = fm.beginTransaction();
            //移除旧的fragment
            ft.remove(fragment);
            //换成新的fragment
            switch (changeType) {
                case 0:
                    fragment = AuxiliaryCheckPicFragment.newInstance(illnessid);
                    break;
                case 1:
                    fragment = AuxiliaryCheckPrintFragment.newInstance(illnessid);
                    break;
            }
            //添加新fragment时必须用前面获得的tag
            ft.add(container.getId(), fragment, fragmentTag);
            ft.attach(fragment);
            ft.commit();
            //复位更新标志
            fragmentsUpdateFlag[position % fragmentsUpdateFlag.length] = false;
        }
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = PhysicalFragment.newInstance(illnessid);
                break;
            case 1:
                fragment = LabCheckPicFragment.newInstance(illnessid);
                break;
            case 2:
                fragment = AuxiliaryCheckPicFragment.newInstance(illnessid);
                break;
            case 3:
                fragment = DiagnosisFragment.newInstance(illnessid);
                break;
            case 4:
                fragment = new TellFragment();
                break;
        }
        return fragment;
    }


}
