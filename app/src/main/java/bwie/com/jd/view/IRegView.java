package bwie.com.jd.view;


import bwie.com.jd.bean.UserBean;

public interface IRegView {
    void mobileVerify();
    void mobileEmpty();
    void pwdVerify();
    void success(UserBean userBean);
    void failure(String msg);
}
