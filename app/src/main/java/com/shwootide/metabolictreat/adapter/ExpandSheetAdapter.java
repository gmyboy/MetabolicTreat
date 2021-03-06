package com.shwootide.metabolictreat.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.Diagnosis;
import com.shwootide.metabolictreat.entity.MHistory_Past;
import com.shwootide.metabolictreat.entity.Sheet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 化验单左边适配器
 * Created by GMY on 2015/9/23 13:51.
 * Contact me via email gmyboy@qq.com.
 */
public class ExpandSheetAdapter extends BaseExpandableListAdapter {

    /**
     * Multiple choice for all the groups
     */
    public static final int CHOICE_MODE_MULTIPLE = AbsListView.CHOICE_MODE_MULTIPLE;
    public static final int CHOICE_MODE_MULTIPLE_MODAL = AbsListView.CHOICE_MODE_MULTIPLE_MODAL;
    /**
     * No child could be selected
     */
    public static final int CHOICE_MODE_NONE = AbsListView.CHOICE_MODE_NONE;
    /**
     * One single choice per group
     */
    public static final int CHOICE_MODE_SINGLE_PER_GROUP = AbsListView.CHOICE_MODE_SINGLE;
    /**
     * One single choice for all the groups
     */
    public static final int CHOICE_MODE_SINGLE_ABSOLUTE = 10001;
    /**
     * The default choice is the multiple one
     */
    private int choiceMode = CHOICE_MODE_MULTIPLE;

    private List<Sheet> datas;//原始数据
    private Context context;
    private List<String> listGroup;//存放所有头部
    private List<List<Sheet>> commData = new ArrayList<>(); //存放所有子项
    private SparseArray<SparseBooleanArray> checkedPositions;//存放所有选中/未选中的状态
    private int type = 0;//用于区分实验室检查化验单[0]  辅助检查化验单[1]

    public ExpandSheetAdapter(Context context, int type, List<Sheet> datas) {
        this.context = context;
        this.type = type;
        this.datas = getDatas(type, datas);
        this.checkedPositions = new SparseArray<>();
        listGroup = getHeaders();
        for (int i = 0; i < listGroup.size(); i++) {
            List<Sheet> temp = new ArrayList<>();
            for (int j = 0; j < this.datas.size(); j++) {
                if (this.datas.get(j).getNameType2().equals(listGroup.get(i))) {
                    temp.add(this.datas.get(j));
                }
            }
            commData.add(temp);
        }
    }

    /**
     * 截取相关数据
     *
     * @param type
     * @param mDates
     * @return
     */
    public static List<Sheet> getDatas(int type, List<Sheet> mDates) {
        List<Sheet> temp = new ArrayList<>();

        if (type == 1) {//辅助检查
            for (Sheet s : mDates) {
                if (s.getTypeID1().equals("6")) {
                    temp.add(s);
                }
            }
        } else {
            for (Sheet s : mDates) {
                if (s.getTypeID1().equals("5")) {
                    temp.add(s);
                }
            }
        }
        return temp;
    }

    public List<String> getHeaders() {
        Set<String> group = new HashSet<>();
        //获取所有的头部
        for (Sheet s : datas) {
            group.add(s.getNameType2());
        }
        return new ArrayList(group);
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return commData.get(groupPosition).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Sheet getChild(int groupPosition, int childPosition) {
        return commData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        HeaderViewHolder headerholder;
        if (view != null) {
            headerholder = (HeaderViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_dialog_list_header, parent, false);
            headerholder = new HeaderViewHolder();
            headerholder.tvHeader = (TextView) view.findViewById(R.id.tv_dialog_list_header);
            view.setTag(headerholder);
        }
        headerholder.tvHeader.setText(String.valueOf(listGroup.get(groupPosition)));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
        }
        ((CheckedTextView) convertView).setText(getChild(groupPosition, childPosition).getNameType3());
        if (checkedPositions.get(groupPosition) != null) {
            boolean isChecked = checkedPositions.get(groupPosition).get(childPosition);
            ((CheckedTextView) convertView).setChecked(isChecked);
        } else {
            ((CheckedTextView) convertView).setChecked(false);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * Update the list of the positions checked and update the view
     *
     * @param groupPosition The position of the group which has been checked
     * @param childPosition The position of the child which has been checked
     */
    public void setClicked(int groupPosition, int childPosition) {
        switch (choiceMode) {
            case CHOICE_MODE_MULTIPLE:
                SparseBooleanArray checkedChildPositionsMultiple = checkedPositions.get(groupPosition);
                // if in the group there was not any child checked
                if (checkedChildPositionsMultiple == null) {
                    checkedChildPositionsMultiple = new SparseBooleanArray();
                    // By default, the status of a child is not checked
                    // So a click will enable it
                    checkedChildPositionsMultiple.put(childPosition, true);
                    checkedPositions.put(groupPosition, checkedChildPositionsMultiple);
                } else {
                    boolean oldState = checkedChildPositionsMultiple.get(childPosition);
                    checkedChildPositionsMultiple.put(childPosition, !oldState);
                }
                break;
            // TODO: Implement it
            case CHOICE_MODE_MULTIPLE_MODAL:
                throw new RuntimeException("The choice mode CHOICE_MODE_MULTIPLE_MODAL " +
                        "has not implemented yet");
            case CHOICE_MODE_NONE:
                checkedPositions.clear();
                break;
            case CHOICE_MODE_SINGLE_PER_GROUP:
                SparseBooleanArray checkedChildPositionsSingle = checkedPositions.get(groupPosition);
                // If in the group there was not any child checked
                if (checkedChildPositionsSingle == null) {
                    checkedChildPositionsSingle = new SparseBooleanArray();
                    // By default, the status of a child is not checked
                    checkedChildPositionsSingle.put(childPosition, true);
                    checkedPositions.put(groupPosition, checkedChildPositionsSingle);
                } else {
                    boolean oldState = checkedChildPositionsSingle.get(childPosition);
                    // If the old state was false, set it as the unique one which is true
                    if (!oldState) {
                        checkedChildPositionsSingle.clear();
                        checkedChildPositionsSingle.put(childPosition, !oldState);
                    } // Else does not allow the user to uncheck it
                }
                break;
            // This mode will remove all the checked positions from other groups
            // and enable just one from the selected group
            case CHOICE_MODE_SINGLE_ABSOLUTE:
                checkedPositions.clear();
                SparseBooleanArray checkedChildPositionsSingleAbsolute = new SparseBooleanArray();
                checkedChildPositionsSingleAbsolute.put(childPosition, true);
                checkedPositions.put(groupPosition, checkedChildPositionsSingleAbsolute);
                break;
        }
        // Notify that some data has been changed
        notifyDataSetChanged();
    }

    public int getChoiceMode() {
        return choiceMode;
    }

    /**
     * Set a new choice mode. This will remove
     * all the checked positions
     *
     * @param choiceMode
     */
    public void setChoiceMode(int choiceMode) {
        this.choiceMode = choiceMode;
        // For now the choice mode CHOICE_MODEL_MULTIPLE_MODAL
        // is not implemented
        if (choiceMode == CHOICE_MODE_MULTIPLE_MODAL) {
            throw new RuntimeException("The choice mode CHOICE_MODE_MULTIPLE_MODAL " +
                    "has not implemented yet");
        }
        checkedPositions.clear();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_labcheck.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    class ViewHolder {
        TextView tvLabitem;
    }

    class HeaderViewHolder {
        TextView tvHeader;
    }
}
