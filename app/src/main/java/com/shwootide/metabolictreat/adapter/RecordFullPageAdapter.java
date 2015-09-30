package com.shwootide.metabolictreat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.shwootide.metabolictreat.fragment.RecordFamilyFragment;
import com.shwootide.metabolictreat.fragment.RecordMedicineFragment;
import com.shwootide.metabolictreat.fragment.RecordNowFragment;
import com.shwootide.metabolictreat.fragment.RecordPastFragment;
import com.shwootide.metabolictreat.fragment.RecordPersonalFragment;
import com.shwootide.metabolictreat.fragment.RecordSugarFragment;

/**
 * 诊断页tab适配器
 * Created by GMY on 2015/8/25 09:50.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordFullPageAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"现病史", "既往史", "家族史", "个人史"};
    private FragmentManager fm;
    private boolean[] fragmentsUpdateFlag = {false, false, false, false};
    private String illnessid = "";
    private int changeType;//0现病史   1 血糖监测   2 现治疗用药
    private String recordName = "";//患者姓名
    private String position = "1";//就诊次数  默认为初诊1

    public RecordFullPageAdapter(FragmentManager fm, String illnessid, String recordName, String position) {
        super(fm);
        this.fm = fm;
        this.illnessid = illnessid;
        this.recordName = recordName;
        this.position = position;
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
                    fragment = RecordNowFragment.newInstance(illnessid);
                    break;
                case 1:
                    fragment = RecordSugarFragment.newInstance(illnessid);
                    break;
                case 2:
                    fragment = RecordMedicineFragment.newInstance(illnessid);
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
                fragment = RecordNowFragment.newInstance(illnessid);
                break;
            case 1:
                fragment = RecordPastFragment.newInstance(illnessid);
                break;
            case 2:
                fragment = RecordFamilyFragment.newInstance(illnessid);
                break;
            case 3:
                fragment = RecordPersonalFragment.newInstance(illnessid);
                break;
        }
        return fragment;
    }


    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }
}
