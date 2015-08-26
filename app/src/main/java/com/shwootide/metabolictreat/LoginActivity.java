package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.shwootide.metabolictreat.entity.Common;
import com.shwootide.metabolictreat.network.WebServiceFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;

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
    @Bind(R.id.cb_login)
    CheckBox cbLogin;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @OnClick(R.id.btn_login)
    void login() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("UserName", etLoginUsername.getText().toString().trim());
        params.put("usrPwd", etLoginPassword.getText().toString().trim());
        new WebServiceFetcher<>().fetch(mContext, "UserLogin", params);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void setTitleBarOption() {

    }

    /**
     * 接收简单数据
     *
     * @param common 病历单
     */
    public void onEventMainThread(Common common) {
        if (common != null) {
            if (common.getCode() == 200) {
                CommonUtil.showToast(mContext, "登陆成功");
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            } else
                CommonUtil.showToast(mContext, common.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
    }
}
