package com.shwootide.metabolictreat.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.shwootide.metabolictreat.entity.UserInfo;

import java.io.FileNotFoundException;

/**
 * preference工具类
 * Created by GMY on 2015/9/6 16:24.
 * Contact me via email gmyboy@qq.com.
 */
public class PreferenceUtil {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

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
    public static boolean saveUser(Context context, String filename, UserInfo userInfo) {
        if (userInfo != null) {
            try {
                //取到编辑器
                editor = context.getSharedPreferences(filename, Context.MODE_PRIVATE).edit();
                editor.putString("UserName", userInfo.getUserName());
                editor.putString("UserState", userInfo.getUserState());
                editor.putString("StaffID", userInfo.getStaffID());
                editor.putInt("UserID", userInfo.getUserID());
                editor.putString("HospitalID", userInfo.getHospitalID());
                editor.putString("fdate", userInfo.getFDate());
                editor.putString("UserToStaff", userInfo.getUserToStaff());
                editor.putString("Tel", userInfo.getTel());
                editor.putString("DepartmentID", userInfo.getDepartmentID());
                editor.putString("Pwd", userInfo.getPwd());
                editor.putInt("RoleID", userInfo.getRoleID());

                editor.putString("RoleName", userInfo.getRoleName());
                editor.putString("FDate", userInfo.getFDate());
                editor.putString("code", userInfo.getCode());
                editor.putString("OrganizationName", userInfo.getOrganizationName());
                editor.putString("flg", userInfo.getFlg());
                editor.putBoolean("AutoLogin", userInfo.isAutoLogin());
                //把数据提交给文件中
                editor.clear();
                editor.commit();
            } catch (Exception e) {
                return false;
            }
            return true;
        } else
            return false;
    }

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
    public static UserInfo getUser(Context context, String filename) {
        UserInfo info = new UserInfo();
        try {
            sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            info.setUserName(sharedPreferences.getString("UserName", ""));
            info.setUserState(sharedPreferences.getString("UserState", ""));
            info.setStaffID(sharedPreferences.getString("StaffID", ""));
            info.setUserID(sharedPreferences.getInt("UserID", 0));
            info.setHospitalID(sharedPreferences.getString("HospitalID", ""));
            info.setFDate(sharedPreferences.getString("fdate", ""));
            info.setUserToStaff(sharedPreferences.getString("UserToStaff", ""));
            info.setTel(sharedPreferences.getString("Tel", ""));
            info.setDepartmentID(sharedPreferences.getString("DepartmentID", ""));
            info.setPwd(sharedPreferences.getString("Pwd", ""));
            info.setRoleID(sharedPreferences.getInt("RoleID", 0));
            info.setRoleName(sharedPreferences.getString("RoleName", ""));
            info.setFDate(sharedPreferences.getString("FDate", ""));
            info.setCode(sharedPreferences.getString("code", ""));
            info.setOrganizationName(sharedPreferences.getString("OrganizationName", ""));
            info.setFlg(sharedPreferences.getString("flg", ""));
            info.setAutoLogin(sharedPreferences.getBoolean("AutoLogin", false));

        } catch (Exception e) {
            info = new UserInfo();
        }
        return info;
    }
}
