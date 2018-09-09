package bwie.com.jd.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import bwie.com.jd.bean.UserBean;
import bwie.com.jd.model.RegisterModel;
import bwie.com.jd.utils.RegexValidateUtil;
import bwie.com.jd.view.IRegView;

public class RegisterPresenter {

    private RegisterModel registerModel;
    private IRegView iRegView;

    public RegisterPresenter(IRegView iRegView) {
        this.registerModel = new RegisterModel();
        this.iRegView = iRegView;
    }

    public void register(String mobile,String pwd){
        if(TextUtils.isEmpty(mobile)){
            iRegView.mobileEmpty();
            return;
        }
        if(!RegexValidateUtil.checkCellphone(mobile)){
            iRegView.mobileVerify();
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            iRegView.pwdVerify();
            return;
        }

        HashMap<String,String> params = new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",pwd);

        registerModel.register(params, new RegisterModel.RegCallback() {
            @Override
            public void failure(String errorMsg) {
                iRegView.failure(errorMsg);
            }

            @Override
            public void success(UserBean userBean) {
                iRegView.success(userBean);
            }
        });
    }
}
