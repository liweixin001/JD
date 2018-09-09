package bwie.com.jd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.jd.R;
import bwie.com.jd.bean.HomeBean;

class GridViewAdapter extends BaseAdapter{
    private Context context;
    List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist;
    private String images;

    public GridViewAdapter(Context context, List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist) {
        this.context = context;
        this.miaoshalist = miaoshalist;

//        for (HomeBean.DataBean.MiaoshaBean.ListBean listBean : miaoshalist) {
//            images = listBean.getImages();
//            System.out.println("gridViewAdapter:"+images);
//        }
    }


    @Override
    public int getCount() {
        return miaoshalist.size();
    }

    @Override
    public Object getItem(int i) {
        return miaoshalist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = View.inflate(context, R.layout.item_girl_gridview, null);
        ImageView imageView = (ImageView) view1.findViewById(R.id.iv_cover);
        TextView textView = (TextView) view1.findViewById(R.id.tv_title);
        String[] imageUrls = miaoshalist.get(i).getImages().split("\\|");
        if(imageUrls!=null&&imageUrls.length>0){
            Glide.with(context).load(imageUrls[0]).into(imageView);
        }
        textView.setText(miaoshalist.get(i).getTitle());
        return view1;
    }

}
