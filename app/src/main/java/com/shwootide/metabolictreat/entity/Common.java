package com.shwootide.metabolictreat.entity;

/**
 * 通用的返回数据，用于解析json数据
 * Created by GMY on 2015/8/26 10:42.
 * Contact me via email gmyboy@qq.com.
 */
public class Common {
    /**
     * code : 200
     * info : {"UserName":"changbao","UserState":"启动","StaffID":"016907ce-d298-46d8-bb45-d17118532ce2","UserID":1,"HospitalID":"1","fdate":"2014-09-18T09:24:54","UserToStaff":"常宝","Tel":"","DepartmentID":"1001","Pwd":"888888","RoleID":20}
     */
    private int code;
    private String message;
    private UserInfo userInfo;

    public void setCode(int code) {
        this.code = code;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getCode() {
        return code;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
