package com.keyi.report;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.keyi.report.adpater.FaHuoAdapter;
import com.keyi.report.bean.FaHuoMsg;
import com.keyi.report.interfacer.MsgInterface;
import com.keyi.report.model.LoadMsgModel;
import com.keyi.report.utils.DatasUtils;
import com.keyi.report.view.MyDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class FaHuoActivity extends Activity implements MsgInterface {
    private XRecyclerView tableListView;
    List<FaHuoMsg.DataBean> dataBeans;
    private int page=0;
    FaHuoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahuo);
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
        FaHuoMsg inventCollectMsg=gson.fromJson(istrue,FaHuoMsg.class);
        if (inventCollectMsg.isIsOK()){
            if (queneFlag==0){
                for (int i=0;i<inventCollectMsg.getData().size();i++){
                    dataBeans.add(inventCollectMsg.getData().get(i));
                }
                adapter = new FaHuoAdapter(dataBeans);
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
            Toast.makeText(FaHuoActivity.this, "已全部加载完", Toast.LENGTH_SHORT).show();
            tableListView.loadMoreComplete();
        }
    }

    @Override
    public void isError(String isError, int queneFlag) {
        Toast.makeText(FaHuoActivity.this, "网络或服务器异常！", Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        LoadMsgModel loadMsgModel = new LoadMsgModel(this, page);
        final String url = DatasUtils.faHuoUrlSting(FaHuoActivity.this, page);
        Log.e("asdda",url);
        loadMsgModel.getLoadMsg(url);
    }

}
