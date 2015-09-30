package com.shwootide.metabolictreat.entity;

import com.shwootide.metabolictreat.utils.CommonUtil;

/**
 * 病史提交的实体类
 * Created by GMY on 2015/9/18 17:52.
 * Contact me via email gmyboy@qq.com.
 */
public class MHistory {
    private String id;
    /**
     * 对应{@link MHistory_Past}的id
     */
    private String MHTypeID;
    private String isCheck;
    private String CheckValues1;
    private String CheckValues2;
    private String InputDate;
    private String InputUser;
    private String MedicalRecordID;
    private String bz;
    private String type;//现病史01 既往史04 家族史05 个人史06 诊断10

    public MHistory() {
        this.id = CommonUtil.generateGUID();
        this.InputDate = CommonUtil.getSysDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMHTypeID() {
        return MHTypeID;
    }

    public void setMHTypeID(String MHTypeID) {
        this.MHTypeID = MHTypeID;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getCheckValues1() {
        return CheckValues1;
    }

    public void setCheckValues1(String checkValues1) {
        CheckValues1 = checkValues1;
    }

    public String getCheckValues2() {
        return CheckValues2;
    }

    public void setCheckValues2(String checkValues2) {
        CheckValues2 = checkValues2;
    }

    public String getInputDate() {
        return InputDate;
    }

    public void setInputDate(String inputDate) {
        InputDate = inputDate;
    }

    public String getInputUser() {
        return InputUser;
    }

    public void setInputUser(String inputUser) {
        InputUser = inputUser;
    }

    public String getMedicalRecordID() {
        return MedicalRecordID;
    }

    public void setMedicalRecordID(String medicalRecordID) {
        MedicalRecordID = medicalRecordID;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
