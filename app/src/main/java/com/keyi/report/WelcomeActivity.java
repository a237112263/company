package com.keyi.report;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.keyi.report.bean.Report;
import com.keyi.report.bean.SMSData;
import com.keyi.report.interfacer.MsgInterface;
import com.keyi.report.model.LoadMsgModel;
import com.keyi.report.utils.DatasUtils;
import com.keyi.report.utils.MyUtils;
import com.keyi.report.utils.SharedPreferencesUtils;
import com.keyi.report.utils.UpdateManager;

import java.text.SimpleDateFormat;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import static com.blankj.utilcode.utils.TimeUtils.getCurTimeString;
import static com.blankj.utilcode.utils.TimeUtils.getIntervalTime;


public class WelcomeActivity extends Activity implements View.OnClickListener, MsgInterface {
    private ImageView imageView;

    @Override
    protected void onStart() {
        super.onStart();
        BmobQuery<Report> bmobQuery=new BmobQuery<Report>();
        bmobQuery.getObject(this, "kzqD888S", new GetListener<Report>() {
            @Override
            public void onSuccess(Report person) {
                final UpdateManager manager = new UpdateManager(WelcomeActivity.this);
                Log.e("ASDASDD", person.getVisioncode().toString());
                // 获取当前软件版本
                int versionCode = getVersionCode(WelcomeActivity.this);
                Log.e("versionCode", versionCode + "");
                if (versionCode < Integer.parseInt(person.getVisioncode().toString())) {
                    manager.checkUpdate();
                } else {
                }
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(getApplicationContext(), "网络连接错误,请检查网络！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        imageView= (ImageView) findViewById(R.id.iv_welcome);
        imageView.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        imageView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        imageView.setEnabled(false);
        SharedPreferences setting = getSharedPreferences("SHARE_APP_TAG", 0);
        Boolean user_first = setting.getBoolean("FIRST", true);
        Log.e("setting", user_first + "");
        if(user_first){//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            MyUtils.inTent(WelcomeActivity.this, SMSActivity.class);
            finish();
        }else{
                if (getTimeErrand()>=86400){
                    MyUtils.inTent(WelcomeActivity.this, YanZhengMaAtivity.class);
                    finish();
                }else {
                    LoadMsgModel loadMsgModel = new LoadMsgModel(this,0);
                    final String url = DatasUtils.mobUrl+ SharedPreferencesUtils.getParam(WelcomeActivity.this,"MobilNumber","")+"&code="+SharedPreferencesUtils.getParam(WelcomeActivity.this,"yanzhengma","");
                    loadMsgModel.getLoadMsg(url);
                }
        }
    }
    private int getVersionCode(Context context)
    {
        int versionCode = 0;
        try
        {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().getPackageInfo("com.keyi.report", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return versionCode;
    }
    @Override
    public void isTrue(String data, int queneFlag) {
        Gson gson=new Gson();
        SMSData smsData=gson.fromJson(data,SMSData.class);
        if (smsData.isIsOK()==true){
            MyUtils.inTent(WelcomeActivity.this, MainActivity.class);
            finish();
        }else {
            MyUtils.inTent(WelcomeActivity.this, YanZhengMaAtivity.class);
            finish();
        }
    }
    @Override
    public void isError(String isError, int queneFlag) {
        ToastUtils.showShortToastSafe(WelcomeActivity.this,"服务器或网络异常！");
        Toast.makeText(WelcomeActivity.this,"试着去锻炼自己的记忆力",Toast.LENGTH_SHORT).show();
    }

    public long getTimeErrand(){
        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return getIntervalTime(SharedPreferencesUtils.getParam(WelcomeActivity.this, "lasttime", "").toString(), getCurTimeString(), ConstUtils.TimeUnit.SEC, formatter);
    }
}
