package bwie.com.jd.view;

import bwie.com.jd.bean.LoginBean;

public interface ILoginView {
    void mobileVerify();
    void mobileEmpty();
    void pwdVerify();
    void success(LoginBean userBean);
    void failure(String msg);
}
