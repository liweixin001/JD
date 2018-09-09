package bwie.com.jd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import bwie.com.jd.MainActivity;
import bwie.com.jd.R;
import bwie.com.jd.bean.LoginBean;
import bwie.com.jd.presenter.LoginPresenter;
import bwie.com.jd.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView{
    private EditText mobileEt,pwdEt;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initDatas();
    }

    private void initDatas() {
        loginPresenter = new LoginPresenter(this);
    }

    private void initViews() {
        mobileEt = findViewById(R.id.login_mobile_et);
        pwdEt = findViewById(R.id.login_pwd_et);
    }

    public void login(View view) {
        loginPresenter.login(mobileEt.getText().toString(),pwdEt.getText().toString());

    }

    @Override
    public void mobileVerify() {
        Toast.makeText(this, "手机号需11位合法数字", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobileEmpty() {
        Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdVerify() {
        Toast.makeText(this,"密码不合法" , Toast.LENGTH_SHORT).show();
    }


    @Override
    public void success(LoginBean userBean) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg , Toast.LENGTH_SHORT).show();
    }
}
