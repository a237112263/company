package com.keyi.report;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.keyi.report.adpater.ShuaDanAdapter;
import com.keyi.report.bean.FilterOrderSalesMsg;
import com.keyi.report.interfacer.MsgInterface;
import com.keyi.report.model.LoadMsgModel;
import com.keyi.report.utils.DatasUtils;
import com.keyi.report.view.MyDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ShuaDanActivity extends Activity implements MsgInterface {
    private XRecyclerView tableListView;
    List<FilterOrderSalesMsg.DataBean> dataBeans;
    private int page=0;
    ShuaDanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuadan);
        ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(229, 183, 198));
        dataBeans=new ArrayList<>();
        tableListView = (XRecyclerView) findViewById(R.id.list);
        tableListView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        tableListView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        initData();
        tableListView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        dataBeans.clear();
                        page = 0;
                        initData();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        initData();
                    }
                }, 2000);
            }
        });

    }

    @Override
    public void isTrue(String istrue, int queneFlag) {
        page++;
        Gson gson=new Gson();
        FilterOrderSalesMsg inventCollectMsg=gson.fromJson(istrue,FilterOrderSalesMsg.class);
        if (inventCollectMsg.isIsOK()){
            if (queneFlag==0){
                for (int i=0;i<inventCollectMsg.getData().size();i++){
                    dataBeans.add(inventCollectMsg.getData().get(i));
                }
                adapter = new ShuaDanAdapter(dataBeans);
                tableListView.setAdapter(adapter);
                tableListView.refreshComplete();
            }else {
                for (int i=0;i<inventCollectMsg.getData().size();i++){
                    dataBeans.add(inventCollectMsg.getData().get(i));
                }
                adapter.notifyDataSetChanged();
                tableListView.loadMoreComplete();
            }
        }else {
            Toast.makeText(ShuaDanActivity.this, "已全部加载完", Toast.LENGTH_SHORT).show();
            tableListView.loadMoreComplete();
        }

    }
    @Override
    public void isError(String isError, int queneFlag) {
        Toast.makeText(ShuaDanActivity.this, "网络或服务器异常！", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
    private void initData() {
        LoadMsgModel loadMsgModel = new LoadMsgModel(this, page);
        final String url = DatasUtils.shuaDanXiaoLiangUrlSting(ShuaDanActivity.this, page);
        loadMsgModel.getLoadMsg(url);
    }
}
