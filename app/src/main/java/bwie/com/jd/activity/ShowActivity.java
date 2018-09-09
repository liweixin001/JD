package bwie.com.jd.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;

import bwie.com.jd.R;
import bwie.com.jd.fragment.FindFragment;
import bwie.com.jd.fragment.FragmentCart;
import bwie.com.jd.fragment.FragmentFenLei;
import bwie.com.jd.fragment.FragmentFistPage;
import bwie.com.jd.fragment.FragmentMine;

public class ShowActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        BottomTabBar btb = findViewById(R.id.bottomTabBar);
        btb.init(getSupportFragmentManager())//初始化方法布局管理
                .setFontSize(0)//设置文字大小
                //参数1：选中后的颜色，参数2：选中前的颜色
                //参数1：文字内容。参数2：导航图片。参数3：切换哪个fragment类
                .setImgSize(150,150)
                .setTabPadding(5,1,1)
                .addTabItem("首页", R.drawable.ac1, R.drawable.ac0, FragmentFistPage.class)
                .addTabItem("分类", R.drawable.abx, R.drawable.abw, FragmentFenLei.class)
                .addTabItem("发现", R.drawable.abz, R.drawable.aby, FindFragment.class)
                .addTabItem("购物车", R.drawable.abv, R.drawable.abu, FragmentCart.class)
                .addTabItem("我的", R.drawable.ac3, R.drawable.ac2, FragmentMine.class)
                .isShowDivider(false);

    }

}
