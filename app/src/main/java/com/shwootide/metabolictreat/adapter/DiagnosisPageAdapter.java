package com.shwootide.metabolictreat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.shwootide.metabolictreat.fragment.MedicalRecord.AuxiliaryCheckPicFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.AuxiliaryCheckPrintFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.DiagnosisFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.LabCheckPrintFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.SheetAuxFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.SheetFragment;
import com.shwootide.metabolictreat.fragment.MedicalRecord.TellMedicalFragment;

/**
 * 诊断页tab适配器
 * Created by GMY on 2015/8/25 09:50.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisPageAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"实验室检查", "辅助检查", "诊断", "医嘱"};
    private int mPosition;
    private String illnessid = "";
    private FragmentManager fm;
    private boolean[] fragmentsUpdateFlag = {false, false, false, false, false};
    private int changeType;//0拍照   1 录入
    private int mSequenceNumber = 1;

    public DiagnosisPageAdapter(FragmentManager fm, String illnessid, int position, int sequenceNumber) {
        super(fm);
        this.illnessid = illnessid;
        this.mPosition = position;
        this.fm = fm;
        this.mSequenceNumber = sequenceNumber;
    }

    public int getChangeType() {
        return changeType;
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
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        //得到tag
        String fragmentTag = fragment.getTag();

        if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
            //如果这个fragment需要更新
            FragmentTransaction ft = fm.beginTransaction();
            //移除旧的fragment
            ft.remove(fragment);
            //换成新的fragment
            switch (changeType) {
                case 0://辅助检查录入
                    fragment = AuxiliaryCheckPrintFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                    break;
                case 1://辅助检查拍照
                    fragment = AuxiliaryCheckPicFragment.newInstance(illnessid, mPosition, 3, mSequenceNumber);
                    break;
                case 2://实验室检查录入
                    fragment = LabCheckPrintFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                    break;
                case 3://实验室检查拍照
                    fragment = AuxiliaryCheckPicFragment.newInstance(illnessid, mPosition, 2, mSequenceNumber);
                    break;
                case 4://医嘱->生活方式指导/治疗用药/下次
                    fragment = TellMedicalFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                    break;
                case 5://医嘱->实验室检查
                    fragment = SheetFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                    break;
                case 6://医嘱->辅助检查
                    fragment = SheetAuxFragment.newInstance(illnessid, mPosition, mSequenceNumber);
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
                fragment = LabCheckPrintFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                break;
            case 1:
                fragment = AuxiliaryCheckPicFragment.newInstance(illnessid, mPosition, 3, mSequenceNumber);
                break;
            case 2:
                fragment = DiagnosisFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                break;
            case 3:
                fragment = TellMedicalFragment.newInstance(illnessid, mPosition, mSequenceNumber);
                break;
        }
        return fragment;
    }


}
