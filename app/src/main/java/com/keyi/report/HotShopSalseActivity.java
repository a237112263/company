package com.keyi.report;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.keyi.report.adpater.MyHotShopSalesAdapter;
import com.keyi.report.bean.HotShopSales;
import com.keyi.report.interfacer.MsgInterface;
import com.keyi.report.model.LoadMsgModel;
import com.keyi.report.utils.DatasUtils;
import com.keyi.report.utils.MyUtils;
import com.keyi.report.view.MyDecoration;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/28.
 */
public class HotShopSalseActivity extends Activity implements MsgInterface{
    @InjectView(R.id.lv_main)
     RecyclerView listView;
    @InjectView(R.id.chart1_horizon)
     HorizontalBarChart barChart;
    @InjectView(R.id.sv_hotshop)
     ScrollView scrollView;
    @InjectView(R.id.avloadingIndicatorView_BallClipRotatePulse)
     AVLoadingIndicatorView avLoadingIndicatorView;
    @InjectView(R.id.tv_hot)
     TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotshopsalse);
        ButterKnife.inject(this);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        final String url =  DatasUtils.headString(HotShopSalseActivity.this,DatasUtils.hotShopSalesUrl );
        LoadMsgModel loadMsgModel = new LoadMsgModel(this,0);
        loadMsgModel.getLoadMsg(url);
    }

    @Override
    public void isTrue(String data,int f) {
        scrollView.setVisibility(View.VISIBLE);
        Gson gson = new Gson();
        HotShopSales hotShop = gson.fromJson(data, HotShopSales.class);
        if (hotShop.isIsOK()){
            MyHotShopSalesAdapter adapter = new MyHotShopSalesAdapter(hotShop);

            listView.setAdapter(adapter);
            avLoadingIndicatorView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);

            barChart.setBackgroundColor(Color.WHITE);
            barChart.setDoubleTapToZoomEnabled(false);
            barChart.setDrawBarShadow(false);
            barChart.getAxisLeft().setEnabled(false);
            XAxis xAxis1 = barChart.getXAxis();
            xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
            ArrayList<String> xValues = new ArrayList<String>();
            for (int d = 0; d < 10; d++) {
                xValues.add("第"+(10-d) + "名");
            }

            ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
            for (int i = 0; i < hotShop.getData().size(); i++) {
                float val1 = (float)(hotShop.getData().get(9-i).getNum()) ;
                yValues.add(new BarEntry(val1, i));
            }
            // y轴的数据集合
            BarDataSet barDataSet = new BarDataSet(yValues, "");
            barDataSet.setColors(MyUtils.getColors());

            ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
            barDataSets.add(barDataSet); // add the datasets
            Legend mLegend1 = barChart.getLegend();
            mLegend1.setEnabled(false);
            BarData barData = new BarData(xValues, barDataSets);
            barChart.setDescriptionColor(Color.parseColor("#FF02BAFF"));
            barChart.setDescription("单位：件");
            barChart.setData(barData);
            barChart.animateY(3000);
            barChart.invalidate();
        }else {
            if (hotShop.getErrMsg().toString().equals("手机号码不正确或者登录超时,请重新登录!")) {
                new AlertDialog.Builder(this).setMessage("验证码已失效，请重新登录！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.activity.finish();
                                Intent intent = new Intent(HotShopSalseActivity.this, SMSActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
            }else {
                Toast.makeText(HotShopSalseActivity.this, hotShop.getErrMsg().toString(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void isError(String isError,int f) {
        Toast.makeText(HotShopSalseActivity.this, "网络加载错误", Toast.LENGTH_SHORT).show();
    }
}
