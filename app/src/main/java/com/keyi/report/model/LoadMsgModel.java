package com.keyi.report.model;


import com.keyi.report.interfacer.MsgInterface;
import com.keyi.report.utils.HttpUtils;


/**
 * Created by Administrator on 2016/8/1.
 */
public class LoadMsgModel {
    private MsgInterface msgInterface;
    private int queneFlag;
    public LoadMsgModel(MsgInterface msgInterface,int queneFlag){
        this.queneFlag=queneFlag;
        this.msgInterface=msgInterface;
    }
    public void getLoadMsg(final String url){
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                msgInterface.isTrue(data,queneFlag);
            }

            @Override
            public void onError(String meg) {
                super.onError(meg);
                msgInterface.isError(meg,queneFlag);
            }
        });
    }
}
