package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GMY on 2015/11/24 09:57.
 * Contact me via email gmyboy@qq.com.
 */
public class SequenceNum implements Parcelable {

    /**
     * SequenceNumber : 2
     * id : d839e537-b779-40d5-a900-3fba8d698518
     */
    private String SequenceNumber;
    private String id;

    public void setSequenceNumber(String SequenceNumber) {
        this.SequenceNumber = SequenceNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSequenceNumber() {
        return SequenceNumber;
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.SequenceNumber);
        dest.writeString(this.id);
    }

    public SequenceNum() {
    }

    protected SequenceNum(Parcel in) {
        this.SequenceNumber = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<SequenceNum> CREATOR = new Parcelable.Creator<SequenceNum>() {
        public SequenceNum createFromParcel(Parcel source) {
            return new SequenceNum(source);
        }

        public SequenceNum[] newArray(int size) {
            return new SequenceNum[size];
        }
    };

    @Override
    public String toString() {
        return getSequenceNumber();
    }
}
