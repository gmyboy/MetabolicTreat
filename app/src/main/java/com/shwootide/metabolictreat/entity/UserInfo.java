package com.shwootide.metabolictreat.entity;

/**
 * Created by GMY on 2015/8/26 14:31.
 * Contact me via email gmyboy@qq.com.
 */
public class UserInfo {

    /**
     * flg : 本医院数据
     * OrganizationName : 仁济医院
     * UserName : changbao
     * UserState : 启动
     * code : RJ
     * HospitalID : 1
     * UserToStaff : 常宝
     * RoleID : 20
     * FDate : 2014-09-18T09:24:54
     * USERREALNAME : 常宝
     * RoleName : 医生组
     * UserID : 1
     * Tel : null
     * DepartmentID : 1001
     * Pwd : 888888
     * staffID : 016907ce-d298-46d8-bb45-d17118532ce2
     */
    private String flg = "";
    private String OrganizationName = "";
    private String UserName = "";
    private String UserState = "";
    private String code = "";
    private String HospitalID = "";
    private String UserToStaff = "";
    private int RoleID = 0;
    private String FDate = "";
    private String USERREALNAME = "";
    private String RoleName = "";
    private int UserID = 0;
    private String Tel = "";
    private String DepartmentID = "";
    private String Pwd = "";
    private String staffID = "";
    private boolean autoLogin = false;

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public void setOrganizationName(String OrganizationName) {
        this.OrganizationName = OrganizationName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setUserState(String UserState) {
        this.UserState = UserState;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHospitalID(String HospitalID) {
        this.HospitalID = HospitalID;
    }

    public void setUserToStaff(String UserToStaff) {
        this.UserToStaff = UserToStaff;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public void setFDate(String FDate) {
        this.FDate = FDate;
    }

    public void setUSERREALNAME(String USERREALNAME) {
        this.USERREALNAME = USERREALNAME;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
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

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getFlg() {
        return flg;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserState() {
        return UserState;
    }

    public String getCode() {
        return code;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public String getUserToStaff() {
        return UserToStaff;
    }

    public int getRoleID() {
        return RoleID;
    }

    public String getFDate() {
        return FDate;
    }

    public String getUSERREALNAME() {
        return USERREALNAME;
    }

    public String getRoleName() {
        return RoleName;
    }

    public int getUserID() {
        return UserID;
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

    public String getStaffID() {
        return staffID;
    }
}
