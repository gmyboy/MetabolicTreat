package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.shwootide.metabolictreat.event.MessageEvent;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/17.
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
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
    }



    public void onEventMainThread(MessageEvent event) {
        btnLogin.setText(event.message);
    }
}
