package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 拍照上传的文件类
 * Created by GMY on 2015/11/22 16:33.
 * Contact me via email gmyboy@qq.com.
 */
public class PicInfo implements Parcelable {

    /**
     * flg : 1
     * TitleName : 2015.11.12肝功能检查化验单
     * StandardTypeID : 1
     * FileName : 20151112肝功能检查化验单.png
     * MedicalRecordID : 8ebba12d-7a94-4e51-be37-4c404fb2c411
     * CheckDate : 2015-11-12T13:16:51.83
     * id : e68a0dcd-d9ae-4e02-bd0c-002bf04fe5bf
     * bak : bak1111121
     */
    private String flg;
    private String TitleName;
    private String StandardTypeID;
    private String FileName;
    private String MedicalRecordID;
    private String CheckDate;
    private String id;
    private String bak = "";
    private String local_path;

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getTitleName() {
        return TitleName;
    }

    public void setTitleName(String titleName) {
        TitleName = titleName;
    }

    public String getStandardTypeID() {
        return StandardTypeID;
    }

    public void setStandardTypeID(String standardTypeID) {
        StandardTypeID = standardTypeID;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getMedicalRecordID() {
        return MedicalRecordID;
    }

    public void setMedicalRecordID(String medicalRecordID) {
        MedicalRecordID = medicalRecordID;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String checkDate) {
        CheckDate = checkDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public String getLocal_path() {
        return local_path;
    }

    public void setLocal_path(String local_path) {
        this.local_path = local_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.flg);
        dest.writeString(this.TitleName);
        dest.writeString(this.StandardTypeID);
        dest.writeString(this.FileName);
        dest.writeString(this.MedicalRecordID);
        dest.writeString(this.CheckDate);
        dest.writeString(this.id);
        dest.writeString(this.bak);
        dest.writeString(this.local_path);
    }

    public PicInfo() {
    }

    protected PicInfo(Parcel in) {
        this.flg = in.readString();
        this.TitleName = in.readString();
        this.StandardTypeID = in.readString();
        this.FileName = in.readString();
        this.MedicalRecordID = in.readString();
        this.CheckDate = in.readString();
        this.id = in.readString();
        this.bak = in.readString();
        this.local_path = in.readString();
    }

    public static final Parcelable.Creator<PicInfo> CREATOR = new Parcelable.Creator<PicInfo>() {
        public PicInfo createFromParcel(Parcel source) {
            return new PicInfo(source);
        }

        public PicInfo[] newArray(int size) {
            return new PicInfo[size];
        }
    };
}
