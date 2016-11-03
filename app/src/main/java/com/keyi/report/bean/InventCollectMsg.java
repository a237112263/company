package com.keyi.report.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
public class InventCollectMsg {

    /**
     * IsOK : true
     * ErrMsg : null
     * Data : [{"UseQty":0,"OuterIid":"011#欧式布艺餐椅","OuterSkuId":"边椅","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"011#欧式布艺餐椅","OuterSkuId":"扶手椅","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"011#欧式方桌【框架】","OuterSkuId":"1380*760*790【荷花白】","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"013#布餐椅","OuterSkuId":"美式扶手椅","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"193#田园沙发","OuterSkuId":"1+2+3【3号色】","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"193#田园沙发","OuterSkuId":"1+2+3【3号色】","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"193#田园沙发","OuterSkuId":"1+2+3【3号色】","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"193#田园沙发","OuterSkuId":"1+2+3【3号色】","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"902#欧式长茶几【大理石面】","OuterSkuId":"1380*800【白冰花】","AlarmCountSupp":null},{"UseQty":0,"OuterIid":"902#欧式长茶几【大理石面】","OuterSkuId":"1380*800【红龙玉】","AlarmCountSupp":null},{"UseQty":1,"OuterIid":"905#美式转角布沙发","OuterSkuId":"3+中位+右贵妃【1号色】","AlarmCountSupp":"0"},{"UseQty":1,"OuterIid":"011#欧式方桌【框架】","OuterSkuId":"1380*760*790【荷花白】","AlarmCountSupp":"0"},{"UseQty":4,"OuterIid":"012#欧式方桌","OuterSkuId":"1600*900*790【木面】","AlarmCountSupp":"0"},{"UseQty":1,"OuterIid":"011#欧式方桌【框架】","OuterSkuId":"1600*900*790【荷花白】","AlarmCountSupp":"0"},{"UseQty":6,"OuterIid":"015#欧式方桌【大理石面】","OuterSkuId":"1600*900【红龙玉】","AlarmCountSupp":"0"},{"UseQty":5,"OuterIid":"015#美式方桌","OuterSkuId":"1380*800*790【木面】","AlarmCountSupp":"0"},{"UseQty":6,"OuterIid":"012#欧式布艺餐椅","OuterSkuId":"扶手椅【2号色】","AlarmCountSupp":"0"},{"UseQty":5,"OuterIid":"012#欧式布餐椅","OuterSkuId":"扶手椅【1号色】","AlarmCountSupp":"0"},{"UseQty":1,"OuterIid":"013#布餐椅","OuterSkuId":"美式边椅","AlarmCountSupp":"0"},{"UseQty":1,"OuterIid":"012#欧式布餐椅","OuterSkuId":"边椅【1号色】","AlarmCountSupp":"0"}]
     */

    private boolean IsOK;
    private Object ErrMsg;
    /**
     * UseQty : 0
     * OuterIid : 011#欧式布艺餐椅
     * OuterSkuId : 边椅
     * AlarmCountSupp : null
     */

    private List<DataBean> Data;

    public boolean isIsOK() {
        return IsOK;
    }

    public void setIsOK(boolean IsOK) {
        this.IsOK = IsOK;
    }

    public Object getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(Object ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private int UseQty;
        private String OuterIid;
        private String OuterSkuId;
        private Object AlarmCountSupp;

        public int getUseQty() {
            return UseQty;
        }

        public void setUseQty(int UseQty) {
            this.UseQty = UseQty;
        }

        public String getOuterIid() {
            return OuterIid;
        }

        public void setOuterIid(String OuterIid) {
            this.OuterIid = OuterIid;
        }

        public String getOuterSkuId() {
            return OuterSkuId;
        }

        public void setOuterSkuId(String OuterSkuId) {
            this.OuterSkuId = OuterSkuId;
        }

        public Object getAlarmCountSupp() {
            return AlarmCountSupp;
        }

        public void setAlarmCountSupp(Object AlarmCountSupp) {
            this.AlarmCountSupp = AlarmCountSupp;
        }
    }
}
