package com.shwootide.metabolictreat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shwootide.metabolictreat.fragment.PhysicalFragment;

/**
 * 诊断页tab适配器
 * Created by GMY on 2015/8/25 09:50.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisPageAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"体格检查", "实验室检查", "辅助检查", "血糖检测", "现治疗用药", "诊断", "治疗"};

    public DiagnosisPageAdapter(FragmentManager fm) {
        super(fm);
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
                fragment = new PhysicalFragment();
                break;
            case 1:
                fragment = new PhysicalFragment();
                break;
            case 2:
                fragment = new PhysicalFragment();
                break;
            case 3:
                fragment = new PhysicalFragment();
                break;
            case 4:
                fragment = new PhysicalFragment();
                break;
            case 5:
                fragment = new PhysicalFragment();
                break;
            case 6:
                fragment = new PhysicalFragment();
                break;
        }
        return fragment;
    }


}
