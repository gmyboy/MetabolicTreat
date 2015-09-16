package com.shwootide.metabolictreat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.shwootide.metabolictreat.fragment.RecordFamilyFragment;
import com.shwootide.metabolictreat.fragment.RecordNowFragment;
import com.shwootide.metabolictreat.fragment.RecordPastFragment;
import com.shwootide.metabolictreat.fragment.RecordPersonalFragment;

/**
 * 诊断页tab适配器
 * Created by GMY on 2015/8/25 09:50.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordFullPageAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"现病史", "既往史", "家族史", "个人史"};
    private FragmentManager fm;
    private boolean[] fragmentsUpdateFlag = {false, false, false, false};

    public RecordFullPageAdapter(FragmentManager fm) {
        super(fm);
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

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RecordNowFragment();
                break;
            case 1:
                fragment = new RecordPastFragment();
                break;
            case 2:
                fragment = new RecordFamilyFragment();
                break;
            case 3:
                fragment = new RecordPersonalFragment();
                break;
        }
        return fragment;
    }


}
