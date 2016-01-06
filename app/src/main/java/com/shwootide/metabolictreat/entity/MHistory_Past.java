package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 既往史04 /家族史05 /诊断10 的疾病诊断名称
 * Created by GMY on 2015/9/18 17:52.
 * Contact me via email gmyboy@qq.com.
 */
public class MHistory_Past implements Parcelable {

    /**
     * ParentID : 0201
     * flg : 02
     * reference : null
     * mhtype1ID : 0201
     * mhtype1Name : 糖尿病
     * editMode : 1
     * name : 糖尿病
     * id : 1500
     * pindex : 16
     * isDisplay : 0
     */
    private String ParentID;
    private String flg;
    private String reference;
    private String IllnessName;
    private String IllnessID;
    private String editMode;
    private String name;
    private String id;
    private int pindex;
    private String isDisplay;

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getIllnessName() {
        return IllnessName;
    }

    public void setIllnessName(String illnessName) {
        IllnessName = illnessName;
    }

    public String getIllnessID() {
        return IllnessID;
    }

    public void setIllnessID(String illnessID) {
        IllnessID = illnessID;
    }

    public String getEditMode() {
        return editMode;
    }

    public void setEditMode(String editMode) {
        this.editMode = editMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
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
        dest.writeString(this.IllnessName);
        dest.writeString(this.IllnessID);
        dest.writeString(this.editMode);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeInt(this.pindex);
        dest.writeString(this.isDisplay);
    }

    public MHistory_Past() {
    }

    protected MHistory_Past(Parcel in) {
        this.ParentID = in.readString();
        this.flg = in.readString();
        this.reference = in.readString();
        this.IllnessName = in.readString();
        this.IllnessID = in.readString();
        this.editMode = in.readString();
        this.name = in.readString();
        this.id = in.readString();
        this.pindex = in.readInt();
        this.isDisplay = in.readString();
    }

    public static final Parcelable.Creator<MHistory_Past> CREATOR = new Parcelable.Creator<MHistory_Past>() {
        public MHistory_Past createFromParcel(Parcel source) {
            return new MHistory_Past(source);
        }

        public MHistory_Past[] newArray(int size) {
            return new MHistory_Past[size];
        }
    };
}
