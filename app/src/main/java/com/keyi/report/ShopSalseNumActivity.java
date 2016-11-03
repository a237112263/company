package com.keyi.report;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.keyi.report.adpater.MyMainAdapter;
import com.keyi.report.utils.MyUtils;
import com.keyi.report.view.MyDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ShopSalseNumActivity extends Activity{
    @InjectView(R.id.lv_main)
    RecyclerView listView;
    private MyMainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopsalsenum);
        ButterKnife.inject(this);
        String[] shopName = {"1.刷单商品销量表", "2.商品销量表(发货)"};
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
                switch (position) {
                    case 0:
                        MyUtils.inTent(ShopSalseNumActivity.this, ShuaDanActivity.class);
                        break;
                    case 1:
                        MyUtils.inTent(ShopSalseNumActivity.this, FaHuoActivity.class);
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
