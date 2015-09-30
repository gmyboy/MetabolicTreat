package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GMY on 2015/9/23 15:00.
 * Contact me via email gmyboy@qq.com.
 */
public class LabCheck implements Parcelable {

    /**
     * nametype2id : 201
     * reference : 小于30 mg/g
     * score : 0.0
     * IllnessName : 糖尿病
     * IllnessID : 1
     * editMode : 0
     * nametype2 : 肾功能
     * name : 尿白蛋白排泄率（白蛋白/肌酐）
     * nametype1 : 实验室检查录入
     * id : 2090
     * pindex : 36
     * nametype1id : 2
     */
    private String nametype2id;
    private String reference;
    private double score;
    private String IllnessName;
    private String IllnessID;
    private String editMode;
    private String nametype2;
    private String name;
    private String nametype1;
    private String id;
    private int pindex;
    private String nametype1id;

    public void setNametype2id(String nametype2id) {
        this.nametype2id = nametype2id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setScore(double score) {
        this.score = score;
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

    public void setNametype2(String nametype2) {
        this.nametype2 = nametype2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNametype1(String nametype1) {
        this.nametype1 = nametype1;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
    }

    public void setNametype1id(String nametype1id) {
        this.nametype1id = nametype1id;
    }

    public String getNametype2id() {
        return nametype2id;
    }

    public String getReference() {
        return reference;
    }

    public double getScore() {
        return score;
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

    public String getNametype2() {
        return nametype2;
    }

    public String getName() {
        return name;
    }

    public String getNametype1() {
        return nametype1;
    }

    public String getId() {
        return id;
    }

    public int getPindex() {
        return pindex;
    }

    public String getNametype1id() {
        return nametype1id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nametype2id);
        dest.writeString(this.reference);
        dest.writeDouble(this.score);
        dest.writeString(this.IllnessName);
        dest.writeString(this.IllnessID);
        dest.writeString(this.editMode);
        dest.writeString(this.nametype2);
        dest.writeString(this.name);
        dest.writeString(this.nametype1);
        dest.writeString(this.id);
        dest.writeInt(this.pindex);
        dest.writeString(this.nametype1id);
    }

    public LabCheck() {
    }

    protected LabCheck(Parcel in) {
        this.nametype2id = in.readString();
        this.reference = in.readString();
        this.score = in.readDouble();
        this.IllnessName = in.readString();
        this.IllnessID = in.readString();
        this.editMode = in.readString();
        this.nametype2 = in.readString();
        this.name = in.readString();
        this.nametype1 = in.readString();
        this.id = in.readString();
        this.pindex = in.readInt();
        this.nametype1id = in.readString();
    }

    public static final Parcelable.Creator<LabCheck> CREATOR = new Parcelable.Creator<LabCheck>() {
        public LabCheck createFromParcel(Parcel source) {
            return new LabCheck(source);
        }

        public LabCheck[] newArray(int size) {
            return new LabCheck[size];
        }
    };
}
