package bwie.com.jd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwie.com.jd.R;
import bwie.com.jd.activity.SelectActivity;
import bwie.com.jd.adapter.RecyclerAdapter;
import bwie.com.jd.bean.HomeBean;
import bwie.com.jd.common.Api;
import bwie.com.jd.utils.OkHttpUtils;
import bwie.com.jd.utils.RequestCallback;
import okhttp3.Call;
import okhttp3.Response;

public class FragmentFistPage extends Fragment{

    private View view;
    private Button sm_btn;
    private EditText sel_et;
    private XRecyclerView xRecyclerView;
    private RecyclerAdapter homeAdapter;
    private Handler handler = new Handler();
    private LinearLayoutManager linearLayoutManager;
    private HomeBean homeBean;
    private int page = 1;
    private List<HomeBean.DataBean.FenleiBean> fenlei;
    private List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> tuijianlist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.firstpage_fragment, container, false);
        initViews();
        initDatas();
        return view;
    }

    public void initViews() {
        sm_btn = view.findViewById(R.id.sm_btn);
        sel_et = view.findViewById(R.id.sel_et);
        xRecyclerView = view.findViewById(R.id.page_xrcv);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(linearLayoutManager);
//        xRecyclerView.setLoadingListener((XRecyclerView.LoadingListener) getActivity());
        xRecyclerView.setLoadingMoreEnabled(true);
    }

    public void initDatas() {
        initLinstanter();
        getHome();
    }
    //二维码和我的消息监听
    private void initLinstanter() {
        sm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });

        sel_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SelectActivity.class));
            }
        });
    }
    //获取数据
    private void getHome() {
        OkHttpUtils.getInstance().postData(Api.HOME_URL, new HashMap<String, String>(), new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                if(200==response.code()){
                    try {
                        String result = response.body().string();
                        if(!TextUtils.isEmpty(result)){
                            parseResult(result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void parseResult(String result) {
        homeBean = new Gson().fromJson(result, HomeBean.class);
        fenlei = homeBean.getData().getFenlei();
        miaoshalist = homeBean.getData().getMiaosha().getList();
        tuijianlist = homeBean.getData().getTuijian().getList();
        if(homeBean !=null){
            handler.post(new Runnable() {
                @Override
                public void run() {

                    fillDatas();
                }
            });
        }
    }

    private void fillDatas() {
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (page==1){
            homeAdapter = new RecyclerAdapter(getActivity(), homeBean,fenlei,miaoshalist,tuijianlist);
            xRecyclerView.setAdapter(homeAdapter);
            xRecyclerView.refreshComplete();//刷新完成
        }

    }

}
