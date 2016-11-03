package com.keyi.report;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.keyi.report.utils.MyUtils;

/**
 * Created by Administrator on 2016/6/16.
 */
public class YanZhengMaAtivity extends Activity implements View.OnClickListener {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yanzhengma);
        button= (Button) findViewById(R.id.bt_yanzheng);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MyUtils.inTent(YanZhengMaAtivity.this,SMSActivity.class);
        finish();
    }
}
