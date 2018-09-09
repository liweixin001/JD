package bwie.com.jd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bwie.com.jd.R;
import bwie.com.jd.bean.UserBean;
import bwie.com.jd.presenter.RegisterPresenter;
import bwie.com.jd.view.IRegView;

public class RegisterActivity extends AppCompatActivity implements IRegView{
    private EditText useret,pwdet;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initDatas();
    }

    private void initDatas() {
        presenter = new RegisterPresenter(this);
    }

    private void initViews() {
        useret = findViewById(R.id.mobile_et);
        pwdet = findViewById(R.id.pwd_et);
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
    public void success(UserBean userBean) {
        Toast.makeText(this, userBean.msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void register(View view) {
        presenter.register(useret.getText().toString(),pwdet.getText().toString());
    }
}
