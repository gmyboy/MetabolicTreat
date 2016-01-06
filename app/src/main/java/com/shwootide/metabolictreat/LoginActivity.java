package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.shwootide.metabolictreat.entity.UserInfo;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.PreferenceUtil;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 用户登录
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.et_login_username)
    EditText etLoginUsername;
    @Bind(R.id.et_login_password)
    EditText etLoginPassword;
    @Bind(R.id.cb_login_pwd)
    CheckBox cbLogin;
    @Bind(R.id.btn_login_submit)
    Button btnLoginSubmit;

    @OnClick(R.id.btn_login_submit)
    void login() {
        String username = etLoginUsername.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            CommonUtil.showToast(mContext, "用户名不能为空");
        } else if (TextUtils.isEmpty(password)) {
            CommonUtil.showToast(mContext, "密码不能为空");
        } else {
            Map<String, String> params = new WeakHashMap<>();
            params.put("UserName", username);
            params.put("usrPwd", password);
            new MutiFetcher(UserInfo[].class).fetch(mContext, "UserLogin", "正在登陆...", params);
        }
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void setTitleBarOption() {

    }
    @Override
    public void wentTo(RadioGroup group, int checkedId) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
        UserInfo userInfo = PreferenceUtil.getUser(mContext, Config.USERINFO);
        if (userInfo.isAutoLogin()) {
            etLoginPassword.setText(userInfo.getPwd());
        }
        etLoginUsername.setText(userInfo.getUserName());
        cbLogin.setChecked(userInfo.isAutoLogin());
    }

    /**
     * 接收简单数据
     *
     * @param event 病历单
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.getWhat().equals("UserLogin")) {
            if (event.getCode().equals("200")) {
                //保存用户所有信息
                UserInfo userInfo = (UserInfo) event.getObjects().get(0);
                CommonUtil.showToast(mContext, userInfo.getUserToStaff()+"医生，欢迎您登陆");
                userInfo.setAutoLogin(cbLogin.isChecked());
                if (PreferenceUtil.saveUser(mContext, Config.USERINFO, userInfo)) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else
                CommonUtil.showToast(mContext, "登录失败");
        }
    }
}
