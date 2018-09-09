package bwie.com.jd.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import bwie.com.jd.bean.UserBean;
import bwie.com.jd.common.Api;
import bwie.com.jd.utils.OkHttpUtils;
import bwie.com.jd.utils.RequestCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterModel {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    public void register(HashMap<String,String> params,final RegCallback regCallback) {
        OkHttpUtils.getInstance().postData(Api.REG_URL, params, new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {
                if(regCallback!=null){
                    regCallback.failure("请求失败");
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                if(response.code()==200){
                    String result = null;
                    try {
                        result = response.body().string();
                        parseJsonResult(result,regCallback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void parseJsonResult(String result, final RegCallback regCallback) {
        if(result!=null){
            final UserBean userBean = new Gson().fromJson(result, UserBean.class);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(regCallback!=null){
                        regCallback.success(userBean);
                    }
                }
            });
        }
    }

    public interface RegCallback{
        void failure(String errorMsg);
        void success(UserBean userBean);
    }
}
