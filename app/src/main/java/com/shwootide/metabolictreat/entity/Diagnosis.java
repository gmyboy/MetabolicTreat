package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 既往史04 /家族史05 /诊断10 的疾病诊断名称
 * Created by GMY on 2015/9/18 17:52.
 * Contact me via email gmyboy@qq.com.
 */
public class Diagnosis implements Parcelable {


    /**
     * ParentID : 0201
     * flg : 02
     * reference : null
     * type1id : 0201
     * IllnessName : 糖尿病
     * IllnessID : 1
     * editMode : 1
     * name : 1型糖尿病
     * type1Name : 糖尿病
     * id : 021501
     * pindex : 17
     * isDisplay : 1
     *
     *
     *
     * {"name":"1型糖尿病",
     * "id":"26eb6d34-5763-43e6-ab59-18aeccfaa501",
     * "MHTypeID":"021501",
     * "isCheck":"11",
     * "CheckValues1":"1112","CheckValues2":" 111 ","CheckValues3":" 111 ","CheckValues4":" 111 ","CheckValues5":" 111 ",
     * "InputDate":"2014-10-01T13:16:36.323",
     * "InputUser":"016907ce-d298-46d8-bb45-d17118532ce2",
     * "MedicalRecordID":"8ebba12d-7a94-4e51-be37-4c404fb2c410",
     * "bz":"bz001111",
     * "type":"02"}
     */
    private String ParentID;
    private String flg;
    private String reference;
    private String type1id;
    private String IllnessName;
    private String IllnessID;
    private String editMode;
    private String name;
    private String type1Name;
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

    public void setType1id(String type1id) {
        this.type1id = type1id;
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

    public void setType1Name(String type1Name) {
        this.type1Name = type1Name;
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

    public String getType1id() {
        return type1id;
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

    public String getType1Name() {
        return type1Name;
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
        dest.writeString(this.type1id);
        dest.writeString(this.IllnessName);
        dest.writeString(this.IllnessID);
        dest.writeString(this.editMode);
        dest.writeString(this.name);
        dest.writeString(this.type1Name);
        dest.writeString(this.id);
        dest.writeInt(this.pindex);
        dest.writeString(this.isDisplay);
    }

    public Diagnosis() {
    }

    protected Diagnosis(Parcel in) {
        this.ParentID = in.readString();
        this.flg = in.readString();
        this.reference = in.readString();
        this.type1id = in.readString();
        this.IllnessName = in.readString();
        this.IllnessID = in.readString();
        this.editMode = in.readString();
        this.name = in.readString();
        this.type1Name = in.readString();
        this.id = in.readString();
        this.pindex = in.readInt();
        this.isDisplay = in.readString();
    }

    public static final Creator<Diagnosis> CREATOR = new Creator<Diagnosis>() {
        public Diagnosis createFromParcel(Parcel source) {
            return new Diagnosis(source);
        }

        public Diagnosis[] newArray(int size) {
            return new Diagnosis[size];
        }
    };
}
