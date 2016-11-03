package com.keyi.report;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.keyi.report.bean.Shopname;
import com.keyi.report.utils.ACache;
import com.keyi.report.utils.DatasUtils;
import com.keyi.report.utils.HttpUtils;
import com.keyi.report.utils.MyUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ShopSalesReportActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    @InjectView(R.id.lv_shop)
    ListView listView;
    @InjectView(R.id.tv_selectdate)
    TextView textView;
    @InjectView(R.id.tv_shopposition)
    TextView textView1;
    @InjectView(R.id.tv_shopnavestion)
    TextView textView2;
    @InjectView(R.id.np1)
    NumberPicker np1;
    @InjectView(R.id.np2)
    NumberPicker np2;
    @InjectView(R.id.ll_shop)
    LinearLayout linearLayout;
    @InjectView(R.id.tv_shop)
    TextView textView3;
    @InjectView(R.id.avloading)
    AVLoadingIndicatorView loadingIndicatorView;
    private int year, month;
    private ACache aCache;
    private MyListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopsalesreport);
        ButterKnife.inject(this);
        aCache = ACache.get(this);
        init();
        listView.setOnItemClickListener(this);
        textView.setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    private void init() {
        np1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        np1 = (NumberPicker) findViewById(R.id.np1);
        // 设置np1的最小值和最大值
        np1.setMinValue(0);
        np1.setMaxValue(3000);
        // 设置np1的当前值
        np1.setValue(year);
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            // 当NumberPicker的值发生改变时，将会激发该方法
            @Override
            public void onValueChange(NumberPicker picker,
                                      int oldVal, int newVal) {
                year = newVal;
                aCache.put("year", "" + year);
            }
        });
        np2 = (NumberPicker) findViewById(R.id.np2);
        // 设置np2的最小值和最大值
        np2.setMinValue(1);
        np2.setMaxValue(12);
        // 设置np2的当前值
        np2.setValue(month);
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            // 当NumberPicker的值发生改变时，将会激发该方法
            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                month = newVal;
                aCache.put("month", "" + month);
            }
        });
        aCache.put("year", "" + year);
        aCache.put("month", "" + month);
        LoadData();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ShopSalesReportActivity.this, ShopActivity.class);
        intent.putExtra("Flag", position);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_selectdate:
                linearLayout.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                loadingIndicatorView.setVisibility(View.GONE);
                listView.setEnabled(false);
                break;
            case R.id.tv_shopposition:
                linearLayout.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                listView.setEnabled(true);
                textView3.setVisibility(View.VISIBLE);
                loadingIndicatorView.setVisibility(View.VISIBLE);
                LoadData();
                break;
            case R.id.tv_shopnavestion:
                linearLayout.setVisibility(View.GONE);
                listView.setEnabled(true);
                break;
        }
    }

    public void LoadData() {
        final TextView textView = (TextView) findViewById(R.id.emptyview);
        textView.setVisibility(View.GONE);
        final String url =  DatasUtils.headString1(ShopSalesReportActivity.this,DatasUtils.ShopName,aCache.getAsString("year"),aCache.getAsString("month")) ;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Log.e("data",data);
                textView3.setVisibility(View.GONE);
                loadingIndicatorView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                Shopname dayShop = gson.fromJson(data, Shopname.class);
                aCache.put("dayShopname",dayShop);
                if (dayShop.isIsOK()) {
                    textView.setVisibility(View.GONE);
                    adapter = new MyListViewAdapter(dayShop);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    textView.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    if (dayShop.getErrMsg().toString().equals("手机号码不正确或者登录超时,请重新登录!")) {
                        new AlertDialog.Builder(ShopSalesReportActivity.this).setMessage("验证码已失效，请重新登录！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        MainActivity.activity.finish();
                                        Intent intent = new Intent(ShopSalesReportActivity.this, SMSActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).show();
                    }
                }
            }
        });
    }
    private class MyListViewAdapter extends BaseAdapter {
        Shopname list;
        public MyListViewAdapter(Shopname list) {
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.getData().size();
        }

        @Override
        public Object getItem(int position) {
            return list.getData().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView= LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.list_item, parent, false);
                viewHolder.textView=(TextView) convertView.findViewById(R.id.tv_list);
                viewHolder.textView.setText(list.getData().get(position).getList().get(0).getSellerNick());
            }else {
                Toast.makeText(ShopSalesReportActivity.this,"",Toast.LENGTH_SHORT).show();
            }
            MyUtils.showItemAnim(convertView, position);
            return convertView;
        }


        class ViewHolder {
            TextView textView;
        }

    }
}