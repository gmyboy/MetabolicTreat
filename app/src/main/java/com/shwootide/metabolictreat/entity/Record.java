package com.shwootide.metabolictreat.entity;

/**
 * 病历单
 * Created by Administrator on 2015/8/19.
 */
public class Record {
    private String id;//病历编号
    private String name;//姓名
    private String sex;//性别
    private String birthday;//出生日期
    private String diagnosisTime;//就诊时间
    private String diagnosisCount;//就诊次数
    private String Doctor;//医生


    public Record() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDiagnosisTime() {
        return diagnosisTime;
    }

    public void setDiagnosisTime(String diagnosisTime) {
        this.diagnosisTime = diagnosisTime;
    }

    public String getDiagnosisCount() {
        return diagnosisCount;
    }

    public void setDiagnosisCount(String diagnosisCount) {
        this.diagnosisCount = diagnosisCount;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }
}
