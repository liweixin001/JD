package bwie.com.jd.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bwie.com.jd.R;
import bwie.com.jd.adapter.MenuAdapter;
import bwie.com.jd.bean.RecomBean;
import bwie.com.jd.common.Api;
import bwie.com.jd.utils.OkHttpUtils;
import bwie.com.jd.utils.RequestCallback;
import okhttp3.Call;
import okhttp3.Response;

public class FragmentFenLei extends Fragment{
    private List<String> menuList = new ArrayList<>();
    private List<Integer> showTitle;
    private List<RecomBean.DataBean> homeList = new ArrayList<>();
    private ListView lv_menu;
    private ListView lv_home;
    private TextView tv_title;
    private View view;
    private List<RecomBean.DataBean> recomlist;
    private MenuAdapter menuAdapter;
    private int currentItem;
    private Handler handler = new Handler();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fenlei_fragment, container, false);
        initViews();
        initDatas();
        return view;
    }

    private void initDatas() {
        getRecom();
    }
    //分类数据
    private void getRecom() {
        OkHttpUtils.getInstance().postData(Api.RECOM_URL, new HashMap<String, String>(), new RequestCallback() {
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
        RecomBean recomBean = new Gson().fromJson(result, RecomBean.class);
        showTitle = new ArrayList<>();
        for (int i = 0; i < recomBean.getData().size(); i++) {
            RecomBean.DataBean dataBean = recomBean.getData().get(i);
            menuList.add(dataBean.getName());
            showTitle.add(i);
            homeList.add(dataBean);
        }
        if(recomBean!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    lv_menu.setAdapter(menuAdapter);
                }
            });
        }

        menuAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        lv_menu =  view.findViewById(R.id.lv_menu);
        tv_title =  view.findViewById(R.id.tv_titile);
        lv_home = view.findViewById(R.id.lv_home);

        menuAdapter = new MenuAdapter(getActivity(), menuList);

        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                menuAdapter.setSelectItem(i);
                menuAdapter.notifyDataSetInvalidated();
                tv_title.setText(menuList.get(i));
                lv_home.setSelection(showTitle.get(i));
            }
        });

        lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                int current = showTitle.indexOf(i);
                if (currentItem != current && current >= 0) {
                    currentItem = current;
                    tv_title.setText(menuList.get(currentItem));
                    menuAdapter.setSelectItem(currentItem);
                    menuAdapter.notifyDataSetInvalidated();
                }
            }
        });
    }
}
