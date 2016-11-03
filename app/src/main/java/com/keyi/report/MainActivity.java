package com.keyi.report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.keyi.report.adpater.MyMainAdapter;
import com.keyi.report.utils.MyUtils;
import com.keyi.report.view.MyDecoration;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity implements View.OnClickListener {
    @InjectView(R.id.lv_main)
     RecyclerView listView;
    private MyMainAdapter adapter;
    @InjectView(R.id.avloadingIndicatorView_BallClipRotatePulse)
     AVLoadingIndicatorView avLoadingIndicatorView;
    @InjectView(R.id.iv_main1)
    ImageView imageView;
    private long exitTime = 0;
    public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        this.activity = this;
        String[] shopName = {"1.店铺热销榜报表", "2.店铺销售额与成交额报表","3.商品库存报表","4.客服业绩报表","5.商品销量报表"};
        List<String> list = new ArrayList<>();
       for (int i=0;i<shopName.length;i++){
           list.add(shopName[i]);
       }
        listView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        listView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        adapter = new MyMainAdapter(list);
        adapter.setOnRecyclerViewListener(new MyMainAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        MyUtils.inTent(MainActivity.this, HotShopActivity.class);
                        break;
                    case 1:
                        MyUtils.inTent(MainActivity.this, ShopSalesReportActivity.class);
                        break;
                    case 2:
                        MyUtils.inTent(MainActivity.this, InventCollectActivity.class);
                        break;
                    case 3:
                        MyUtils.inTent(MainActivity.this, KeFuYeJiActivity.class);
                        break;
                    case 4:
                        MyUtils.inTent(MainActivity.this, ShopSalseNumActivity.class);
                        break;
                }
            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        listView.setAdapter(adapter);
        avLoadingIndicatorView.setVisibility(View.GONE);
        imageView.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次推出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent3 = new Intent();
        intent3.setClass(MainActivity.this, SMSActivity.class);
        startActivity(intent3);
    }
}
