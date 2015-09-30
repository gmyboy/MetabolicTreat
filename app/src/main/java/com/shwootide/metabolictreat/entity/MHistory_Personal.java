package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 既往史04 /家族史05 /诊断10 的疾病诊断分类(个人史)
 * Created by GMY on 2015/9/18 17:51.
 * Contact me via email gmyboy@qq.com.
 */
public class MHistory_Personal implements Parcelable {
    /**
     * ParentID : 04
     * flg : 04
     * reference : 嗜荤/嗜素
     * editMode : 2
     * name : 饮食习惯
     * id : 840
     * pindex : 1
     * isDisplay : 1
     */
    private String ParentID;
    private String flg;
    private String reference;
    private String editMode;
    private String name;
    private String id;
    private int pindex;
    private String isDisplay;

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setEditMode(String editMode) {
        this.editMode = editMode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getParentID() {
        return ParentID;
    }

    public String getFlg() {
        return flg;
    }

    public String getReference() {
        return reference;
    }

    public String getEditMode() {
        return editMode;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getPindex() {
        return pindex;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ParentID);
        dest.writeString(this.flg);
        dest.writeString(this.reference);
        dest.writeString(this.editMode);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeInt(this.pindex);
        dest.writeString(this.isDisplay);
    }

    public MHistory_Personal() {
    }

    protected MHistory_Personal(Parcel in) {
        this.ParentID = in.readString();
        this.flg = in.readString();
        this.reference = in.readString();
        this.editMode = in.readString();
        this.name = in.readString();
        this.id = in.readString();
        this.pindex = in.readInt();
        this.isDisplay = in.readString();
    }

    public static final Parcelable.Creator<MHistory_Personal> CREATOR = new Parcelable.Creator<MHistory_Personal>() {
        public MHistory_Personal createFromParcel(Parcel source) {
            return new MHistory_Personal(source);
        }

        public MHistory_Personal[] newArray(int size) {
            return new MHistory_Personal[size];
        }
    };
}
