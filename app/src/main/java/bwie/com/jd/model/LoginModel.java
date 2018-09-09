package bwie.com.jd.model;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import bwie.com.jd.bean.LoginBean;
import bwie.com.jd.common.Api;
import bwie.com.jd.utils.OkHttpUtils;
import bwie.com.jd.utils.RequestCallback;
import okhttp3.Call;
import okhttp3.Response;

public class LoginModel {
    private Handler handler = new Handler();

    public void login(HashMap<String, String> params, final LoginCallback loginCallback){
        OkHttpUtils.getInstance().postData(Api.LOGIN_URL, params, new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {
                if(loginCallback!=null){
                    loginCallback.failure("请求失败");
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                if(response.code() == 200){
                    String result = null;

                    try {
                        result = response.body().string();
                        parseJsonResult(result,loginCallback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void parseJsonResult(String result, final LoginCallback loginCallback) {
        if(result!=null){
            final LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (loginCallback!=null){
                        loginCallback.loginSuccess(loginBean);
                    }
                }
            });
        }
    }


    public interface LoginCallback {
        void failure(String errorMsg);//网络请求失败

        void loginSuccess(LoginBean userBean);
    }
}
