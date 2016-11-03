package com.keyi.report;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.keyi.report.adpater.MyMainAdapter;
import com.keyi.report.utils.MyUtils;
import com.keyi.report.view.MyDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/23.
 */
public class HotShopActivity extends Activity {
    private RecyclerView listView;
    private MyMainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotshop);
        listView = (RecyclerView) findViewById(R.id.lv_main);
        String[] shopName = {"1.销售总金额前十排名", "2.商品销量前十排名"};
        List<String> list = new ArrayList<>();
        list.add(shopName[0]);
        list.add(shopName[1]);
        listView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        //这句就是添加我们自定义的分隔线
        listView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        adapter = new MyMainAdapter(list);

        adapter.setOnRecyclerViewListener(new MyMainAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        MyUtils.inTent(HotShopActivity.this, HotActivity.class);
                        break;
                    case 1:
                        MyUtils.inTent(HotShopActivity.this, HotShopSalseActivity.class);
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        listView.setAdapter(adapter);
    }
}
