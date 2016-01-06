package com.shwootide.metabolictreat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.shwootide.metabolictreat.fragment.Common.AddFragment;
import com.shwootide.metabolictreat.fragment.Common.HelpFragment;
import com.shwootide.metabolictreat.fragment.Common.RemindFragment;
import com.shwootide.metabolictreat.fragment.Common.SearchFragment;
import com.shwootide.metabolictreat.fragment.Common.SettingsFragment;

/**
 * Created by GMY on 2015/12/24 17:38.
 * Contact me via email igmyboy@gmail.com.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private boolean[] fragmentsUpdateFlag = {false, false, false, false};
    private FragmentManager fm;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AddFragment();
                break;
            case 1:
                fragment = new SearchFragment();
                break;
            case 2:
                fragment = new RemindFragment();
                break;
            case 3:
                fragment = new HelpFragment();
                break;
            case 4:
                fragment = new HelpFragment();
                break;
            case 5:
                fragment = new SettingsFragment();
                break;
        }
        return fragment;
    }

    public boolean[] getFragmentsUpdateFlag() {
        return fragmentsUpdateFlag;
    }

    public void setFragmentsUpdateFlag(boolean[] fragmentsUpdateFlag) {
        this.fragmentsUpdateFlag = fragmentsUpdateFlag;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        //得到tag
        String fragmentTag = fragment.getTag();

        if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
            //如果这个fragment需要更新
            FragmentTransaction ft = fm.beginTransaction();
            //移除旧的fragment
            ft.remove(fragment);
            //换成新的fragment
            fragment = new AddFragment();
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
    public int getCount() {
        return 4;
    }
}
