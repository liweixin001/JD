package bwie.com.jd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.jd.R;
import bwie.com.jd.bean.HomeBean;

public class TJGridViewAdapter extends BaseAdapter{
    private Context context;
    List<HomeBean.DataBean.TuijianBean.ListBeanX> list;

    public TJGridViewAdapter(Context context, List<HomeBean.DataBean.TuijianBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
        System.out.println("tjadap:"+list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.item_recom_gridview, null);
            viewHolder.recom_iv = view.findViewById(R.id.recom_iv);
            viewHolder.recom_tv = view.findViewById(R.id.recom_tv);
            viewHolder.recom_price = view.findViewById(R.id.recom_price);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.recom_tv.setText(list.get(i).getTitle());
        viewHolder.recom_price.setText(list.get(i).getPrice());
        Glide.with(context).load(list.get(i).getImages().toString()).into(viewHolder.recom_iv);
        return view;
    }
    class ViewHolder{
        TextView recom_tv,recom_price;
        ImageView recom_iv;

    }
}
