package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GMY on 2015/11/10 14:18.
 * Contact me via email gmyboy@qq.com.
 */
public class Sheet implements Parcelable {

    /**
     * NameType1 : 实验室检查开化验单
     * typeID3 : 50000
     * NameType2 : 尿液检查
     * typeID1 : 5
     * NameType3 : 尿常规
     * typeID2 : 500
     */
    private String NameType1;
    private String typeID3;
    private String NameType2;
    private String typeID1;
    private String NameType3;
    private String typeID2;

    public void setNameType1(String NameType1) {
        this.NameType1 = NameType1;
    }

    public void setTypeID3(String typeID3) {
        this.typeID3 = typeID3;
    }

    public void setNameType2(String NameType2) {
        this.NameType2 = NameType2;
    }

    public void setTypeID1(String typeID1) {
        this.typeID1 = typeID1;
    }

    public void setNameType3(String NameType3) {
        this.NameType3 = NameType3;
    }

    public void setTypeID2(String typeID2) {
        this.typeID2 = typeID2;
    }

    public String getNameType1() {
        return NameType1;
    }

    public String getTypeID3() {
        return typeID3;
    }

    public String getNameType2() {
        return NameType2;
    }

    public String getTypeID1() {
        return typeID1;
    }

    public String getNameType3() {
        return NameType3;
    }

    public String getTypeID2() {
        return typeID2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.NameType1);
        dest.writeString(this.typeID3);
        dest.writeString(this.NameType2);
        dest.writeString(this.typeID1);
        dest.writeString(this.NameType3);
        dest.writeString(this.typeID2);
    }

    public Sheet() {
    }

    protected Sheet(Parcel in) {
        this.NameType1 = in.readString();
        this.typeID3 = in.readString();
        this.NameType2 = in.readString();
        this.typeID1 = in.readString();
        this.NameType3 = in.readString();
        this.typeID2 = in.readString();
    }

    public static final Parcelable.Creator<Sheet> CREATOR = new Parcelable.Creator<Sheet>() {
        public Sheet createFromParcel(Parcel source) {
            return new Sheet(source);
        }

        public Sheet[] newArray(int size) {
            return new Sheet[size];
        }
    };
}
