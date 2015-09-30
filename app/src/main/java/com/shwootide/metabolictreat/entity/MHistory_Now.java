package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 现病史类型
 * Created by GMY on 2015/9/18 15:53.
 * Contact me via email gmyboy@qq.com.
 */
public class MHistory_Now implements Parcelable {

    /**
     * ParentID : 01
     * flg : 01
     * reference : 年/月
     * IllnessName : 糖尿病
     * IllnessID : 1
     * editMode : 0
     * name : 血糖升高
     * id : 100
     * pindex : 1
     * isDisplay : 1
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

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setIllnessName(String IllnessName) {
        this.IllnessName = IllnessName;
    }

    public void setIllnessID(String IllnessID) {
        this.IllnessID = IllnessID;
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

    public String getIllnessName() {
        return IllnessName;
    }

    public String getIllnessID() {
        return IllnessID;
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
        dest.writeString(this.IllnessName);
        dest.writeString(this.IllnessID);
        dest.writeString(this.editMode);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeInt(this.pindex);
        dest.writeString(this.isDisplay);
    }

    public MHistory_Now() {
    }

    protected MHistory_Now(Parcel in) {
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

    public static final Parcelable.Creator<MHistory_Now> CREATOR = new Parcelable.Creator<MHistory_Now>() {
        public MHistory_Now createFromParcel(Parcel source) {
            return new MHistory_Now(source);
        }

        public MHistory_Now[] newArray(int size) {
            return new MHistory_Now[size];
        }
    };
}
