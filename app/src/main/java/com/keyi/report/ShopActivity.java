package com.keyi.report;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.keyi.report.bean.DayShop;
import com.keyi.report.bean.MonthShop;
import com.keyi.report.bean.Shopname;
import com.keyi.report.interfacer.MsgInterface;
import com.keyi.report.model.LoadMsgModel;
import com.keyi.report.utils.ACache;
import com.keyi.report.utils.DatasUtils;
import com.keyi.report.utils.MyUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ShopActivity extends Activity implements View.OnClickListener, MsgInterface {
    @InjectView(R.id.tv_shopname)
    TextView textViewShopName;
    @InjectView(R.id.shop_time)
    TextView textView1;
    @InjectView(R.id.xiaoshoue)
    CheckBox checkBox;
    @InjectView(R.id.chengjiaoe)
    CheckBox checkBox1;
    @InjectView(R.id.tv_shop)
    TextView textView3;
    @InjectView(R.id.avloading)
    AVLoadingIndicatorView loadingIndicatorView;
    @InjectView(R.id.rl_shop)
    ScrollView scrollView;
    @InjectView(R.id.tv_this_xiaoshoue)
    TextView textViewThisXiao;
    @InjectView(R.id.tv_this_chengjiaoe)
    TextView textViewThisCheng;
    @InjectView(R.id.tv_up_xiaoshoue)
    TextView textViewUpXiao;
    @InjectView(R.id.tv_up_chengjiaoe)
    TextView textViewUpCheng;


    private LineDataSet dataSet, dataSet1;
    private ArrayList<LineDataSet> dataSets, dataSets1;
    private ArrayList<Entry> yVals, yVals1;
    private BarChart barChart;
    private ACache aCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.inject(this);
        aCache = ACache.get(this);
        Intent intent = getIntent();
        int flag = intent.getIntExtra("Flag", -1);
        Shopname shopname = (Shopname) aCache.getAsObject("dayShopname");
        String name = shopname.getData().get(flag).getList().get(0).getSellerNick();
        LoadMsgModel loadMsgModel = new LoadMsgModel(this, 0);
        final String url = DatasUtils.headString2(ShopActivity.this, DatasUtils.MonthUrl, aCache.getAsString("year"), aCache.getAsString("month"), name);
        loadMsgModel.getLoadMsg(url);

        LoadMsgModel loadMsgModel1 = new LoadMsgModel(this, 1);
        final String url1 = DatasUtils.headString2(ShopActivity.this, DatasUtils.dayShopUrl, aCache.getAsString("year"), aCache.getAsString("month"), name);
        loadMsgModel1.getLoadMsg(url1);
        initView(name);
    }


    private void initView(String name) {
        textViewShopName.setText(name);
        textView1.setText(aCache.getAsString("year") + "年" + aCache.getAsString("month") + "月");
        checkBox.setOnClickListener(this);
        checkBox1.setOnClickListener(this);
    }

    private void lineCharView(String string) {
        Gson gson = new Gson();
        DayShop dayShop = gson.fromJson(string, DayShop.class);
        dataSets = new ArrayList<>();
        dataSets1 = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<String> xVals1 = new ArrayList<>();
        for (int j = 0; j < 31; j++) {
            if (j<15){
                xVals.add((j + 1) + "");
            }else {
                xVals1.add((j + 1) + "");
            }

        }
        textView3.setVisibility(View.GONE);
        loadingIndicatorView.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
        if (dayShop.isIsOK()) {
            for (int i = 0; i < 2; i++) {
                yVals = new ArrayList<>();
                yVals1 = new ArrayList<>();
                for (int j = 0; j < dayShop.getData().get(0).getList().size(); j++) {
                    String[] data1 = dayShop.getData().get(0).getList().get(j).getDates().split("-");
                    Arrays.sort(DatasUtils.dataNume);
                    int index = Arrays.binarySearch(DatasUtils.dataNume, data1[2]);
                    if (index < 15) {
                        if (i == 0) {
                            yVals.add(new Entry((float) (dayShop.getData().get(0).getList().get(j).getPayment()), index));
                        } else {
                            yVals.add(new Entry((float) (dayShop.getData().get(0).getList().get(j).getRealFee()), index));
                        }
                        dataSet = new LineDataSet(yVals, "");
                    } else {
                        if (i == 0) {
                            yVals1.add(new Entry((float) (dayShop.getData().get(0).getList().get(j).getPayment()), index - 15));
                        } else {
                            yVals1.add(new Entry((float) (dayShop.getData().get(0).getList().get(j).getRealFee()), index - 15));
                        }
                        dataSet1 = new LineDataSet(yVals1, "");
                    }
                }
                dataSet.setColor(MyUtils.getColors1()[i]);
                dataSets.add(dataSet);

                LineChart chart = (LineChart) findViewById(R.id.linear);

                lineViewLend(chart, xVals, 0);

                LineChart chart1 = (LineChart) findViewById(R.id.linear_houban);
                if (dataSet1 == null) {
                    chart1.setVisibility(View.GONE);
                } else {
                    dataSet1.setColor(MyUtils.getColors1()[i]);
                    dataSets1.add(dataSet1);
                    lineViewLend(chart1, xVals1, 1);
                }
            }
        } else {

        }

    }

    private void lineViewLend(LineChart chart, ArrayList<String> xVals, int flag) {
        Legend mLegend = chart.getLegend();
        mLegend.setEnabled(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        if (flag == 0) {
            LineData data = new LineData(xVals, dataSets);
            lineData(chart, data);
        } else {
            LineData data = new LineData(xVals, dataSets1);
            lineData(chart, data);
        }
        chart.setDoubleTapToZoomEnabled(false);
        chart.getAxisRight().setEnabled(false);
        // chart.animateY(0);
        chart.setGridBackgroundColor(Color.WHITE);
        chart.setDescription("单位：元");
    }

    private void lineData(LineChart chart, LineData data) {
        data.setValueTextColor(Color.rgb(0, 0, 0));
        data.setValueTextSize(10);
        chart.setData(data);
    }


    @Override
    public void onClick(View v) {
        LineChart chart = (LineChart) findViewById(R.id.linear);
        LineChart chart1 = (LineChart) findViewById(R.id.linear_houban);
        switch (v.getId()) {
            case R.id.xiaoshoue:
                if (checkBox.isChecked()) {
                    intChageData(0, checkBox.isChecked(), dataSets);
                    try{
                        intChageData(0, checkBox.isChecked(), dataSets1);
                    }catch (Exception e){

                    }
                } else {
                    initChangeDataFalse(0, checkBox.isChecked(), dataSets);
                   try{
                       initChangeDataFalse(0, checkBox.isChecked(), dataSets1);
                   }catch (Exception e){

                   }
                }
                break;
            case R.id.chengjiaoe:
                if (checkBox1.isChecked()) {
                    intChageData(1, checkBox1.isChecked(), dataSets);
                    try{
                        intChageData(1, checkBox1.isChecked(), dataSets1);
                    }catch (Exception e){

                    }
                } else {
                    initChangeDataFalse(1, checkBox1.isChecked(), dataSets);
                    try{
                        initChangeDataFalse(1, checkBox1.isChecked(), dataSets1);
                    }catch (Exception e){

                    }
                }
                break;
        }
        chart.invalidate();
        chart1.invalidate();
    }


    private void initChangeDataFalse(int a, boolean f, ArrayList<LineDataSet> dataSets) {
        dataSets.get(a).setValueTextColor(Color.rgb(255, 255, 255));
        dataSets.get(a).setVisible(f);
        dataSets.get(a).setValueTextSize(0);
    }

    private void intChageData(int a, boolean f, ArrayList<LineDataSet> dataSets) {
        dataSets.get(a).setValueTextSize(10);
        dataSets.get(a).setValueTextColor(Color.rgb(0, 0, 0));
        dataSets.get(a).setVisible(f);
    }

    @Override
    public void isTrue(String data, int queneFlag) {
        switch (queneFlag) {
            case 0:
                initBarView(data);
                break;
            case 1:
                lineCharView(data);
                break;
        }
    }

    private void initBarView(String data) {
        barChart = (BarChart) findViewById(R.id.linear1);
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.getAxisRight().setEnabled(false);
        XAxis xAxis1 = barChart.getXAxis();
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        Gson gson = new Gson();
        MonthShop monthShop = gson.fromJson(data, MonthShop.class);
        if (monthShop.isIsOK()) {
            ArrayList<String> xValues = new ArrayList<String>();
            for (int d = 0; d < 12; d++) {
                xValues.add((d + 1) + "月");
            }
            ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
            for (int j = 0; j < monthShop.getData().get(0).getList().size(); j++) {
                String[] data1 = monthShop.getData().get(0).getList().get(j).getDates().split("-");
                Arrays.sort(DatasUtils.dayNumber);
                int index = Arrays.binarySearch(DatasUtils.dataNume, data1[1]);
                float val1 = (float) monthShop.getData().get(0).getList().get(j).getPayment();
                float val2 = (float) monthShop.getData().get(0).getList().get(j).getRealFee();
                yValues.add(new BarEntry(new float[]{val1, val2}, index));
                if (Integer.parseInt(aCache.getAsString("month")) == Integer.parseInt(data1[1])) {
                    textViewThisXiao.setText(monthShop.getData().get(0).getList().get(j).getPayment() + "");
                    textViewThisCheng.setText(monthShop.getData().get(0).getList().get(j).getRealFee() + "");
                }
                if ((Integer.parseInt(aCache.getAsString("month")) - 1) == Integer.parseInt(data1[1])) {
                    textViewUpXiao.setText(monthShop.getData().get(0).getList().get(j).getPayment() + "");
                    textViewUpCheng.setText(monthShop.getData().get(0).getList().get(j).getRealFee() + "");
                }
            }
            // y轴的数据集合
            BarDataSet barDataSet = new BarDataSet(yValues, "");
            barDataSet.setColors(MyUtils.getColors());

            ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
            barDataSets.add(barDataSet);
            barDataSet.setStackLabels(new String[]{"销售额", "成交额"});
            Legend mLegend1 = barChart.getLegend();
            mLegend1.setFormSize(12f);//比例块字体大小
            mLegend1.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);  //左下边显示
            mLegend1.setXEntrySpace(10f);//设置距离饼图的距离，防止与饼图重合
            mLegend1.setYEntrySpace(10f);
            BarData barData = new BarData(xValues, barDataSets);
            barChart.setDescriptionColor(Color.parseColor("#FF02BAFF"));
            barChart.setDescription("单位：元");
            barChart.setData(barData);
            barChart.animateY(3000);
        } else {
            Toast.makeText(ShopActivity.this, data, Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void isError(String isError, int queneFlag) {
        Toast.makeText(ShopActivity.this, "网络加载错误", Toast.LENGTH_SHORT);
    }
}
