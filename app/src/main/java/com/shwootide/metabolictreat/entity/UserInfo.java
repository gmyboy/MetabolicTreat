package com.shwootide.metabolictreat.entity;

/**
 * Created by GMY on 2015/8/26 14:31.
 * Contact me via email gmyboy@qq.com.
 */
public class UserInfo {

    /**
     * UserName : changbao
     * UserState : 启动
     * StaffID : 016907ce-d298-46d8-bb45-d17118532ce2
     * UserID : 1
     * HospitalID : 1
     * fdate : 2014-09-18T09:24:54
     * UserToStaff : 常宝
     * Tel :
     * DepartmentID : 1001
     * Pwd : 888888
     * RoleID : 20
     */
    private String UserName;
    private String UserState;
    private String StaffID;
    private int UserID;
    private String HospitalID;
    private String fdate;
    private String UserToStaff;
    private String Tel;
    private String DepartmentID;
    private String Pwd;
    private int RoleID;

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setUserState(String UserState) {
        this.UserState = UserState;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setHospitalID(String HospitalID) {
        this.HospitalID = HospitalID;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public void setUserToStaff(String UserToStaff) {
        this.UserToStaff = UserToStaff;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public void setDepartmentID(String DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public void setPwd(String Pwd) {
        this.Pwd = Pwd;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserState() {
        return UserState;
    }

    public String getStaffID() {
        return StaffID;
    }

    public int getUserID() {
        return UserID;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public String getFdate() {
        return fdate;
    }

    public String getUserToStaff() {
        return UserToStaff;
    }

    public String getTel() {
        return Tel;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public String getPwd() {
        return Pwd;
    }

    public int getRoleID() {
        return RoleID;
    }
}
