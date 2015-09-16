package com.shwootide.metabolictreat.entity;

/**
 * 疾病信息
 * Created by GMY on 2015/9/8 12:34.
 * Contact me via email gmyboy@qq.com.
 */
public class Illness {
    /**
     * other : null
     * HospitalID : 1
     * name : 糖尿病
     * id : 1
     * DepartmentID : 1001
     * info : null
     */
    private String other;
    private String HospitalID;
    private String name;
    private String id;
    private String DepartmentID;
    private String info;

    public void setOther(String other) {
        this.other = other;
    }

    public void setHospitalID(String HospitalID) {
        this.HospitalID = HospitalID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDepartmentID(String DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOther() {
        return other;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public String getInfo() {
        return info;
    }
}
