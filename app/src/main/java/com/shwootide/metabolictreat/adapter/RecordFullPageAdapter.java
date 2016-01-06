package com.shwootide.metabolictreat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.shwootide.metabolictreat.fragment.MedicalRecord.PhysicalFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.RecordFamilyFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.RecordMedicineFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.RecordNowFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.RecordPastFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.RecordPersonalFragment;

/**
 * 诊断页tab适配器
 * Created by GMY on 2015/8/25 09:50.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordFullPageAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"现病史", "既往史", "家族史", "个人史", "体格检查"};
    private FragmentManager fm;
    private boolean[] fragmentsUpdateFlag = {false, false, false, false};
    private String illnessid = "";
    private int changeType = 0;//0现病史   1 血糖监测   2 现治疗用药
    private int mPosition;//就诊次数 0初诊   2编辑
    private int mSequenceNumber = 1;

    public RecordFullPageAdapter(FragmentManager fm, String illnessid, int position, int sequenceNumber) {
        super(fm);
        this.fm = fm;
        this.illnessid = illnessid;
        this.mPosition = position;
        this.mSequenceNumber = sequenceNumber;
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

    public int getChangeType() {
        return changeType;
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        //得到tag
        String fragmentTag = fragment.getTag();
        if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
            //如果这个fragment需要更新
            FragmentTransaction ft = fm.beginTransaction();
            //移除旧的fragment
            ft.hide(fragment);
            //换成新的fragment
            switch (changeType) {
                case 0:
                    fragment = RecordNowFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                    break;
                case 1:
                    fragment = RecordMedicineFragment.newInstance(illnessid, mPosition, mSequenceNumber);
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
                fragment = RecordNowFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                break;
            case 1:
                fragment = RecordPastFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                break;
            case 2:
                fragment = RecordFamilyFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                break;
            case 3:
                fragment = RecordPersonalFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                break;
            case 4:
                fragment = PhysicalFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                break;
        }
        return fragment;
    }


    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }
}
