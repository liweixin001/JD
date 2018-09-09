package bwie.com.jd.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import bwie.com.jd.bean.LoginBean;
import bwie.com.jd.model.LoginModel;
import bwie.com.jd.utils.RegexValidateUtil;
import bwie.com.jd.view.ILoginView;

public class LoginPresenter {
    private LoginModel loginModel;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        this.loginModel = new LoginModel();
    }

    public void login(String moblie, String pwd) {

        if (TextUtils.isEmpty(moblie)){
            iLoginView.mobileEmpty();
            return;
        }
        if (!RegexValidateUtil.checkCellphone(moblie)){
            iLoginView.mobileVerify();
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            iLoginView.pwdVerify();
            return;
        }

        HashMap<String,String> params = new HashMap<>();
        params.put("mobile",moblie);
        params.put("password",pwd);
        loginModel.login(params, new LoginModel.LoginCallback() {
            @Override
            public void failure(String errorMsg) {
                iLoginView.failure(errorMsg);
            }

            @Override
            public void loginSuccess(LoginBean userBean) {
                iLoginView.success(userBean);
            }
        });
    }


}
