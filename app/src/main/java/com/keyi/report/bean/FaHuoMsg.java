package com.keyi.report.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class FaHuoMsg {


    /**
     * IsOK : true
     * ErrMsg : null
     * Data : [{"Reserved2":"2016-09-19","OuterIid":"012#欧式方桌","OuterSkuId":"1600*900*790【木面】","Num":"2"},{"Reserved2":"2016-09-19","OuterIid":"015#美式方桌","OuterSkuId":"1380*800*790【木面】","Num":"2"},{"Reserved2":"2016-09-23","OuterIid":"012#欧式布餐椅","OuterSkuId":"扶手椅【1号色】","Num":"1"},{"Reserved2":"2016-09-23","OuterIid":"012#欧式方桌【框架】","OuterSkuId":"1600*900*790【荷花白】","Num":"1"},{"Reserved2":"2016-09-23","OuterIid":"015#欧式方桌【大理石面】","OuterSkuId":"1600*900【红龙玉】","Num":"1"},{"Reserved2":"2016-09-26","OuterIid":"012#欧式方桌","OuterSkuId":"1600*900*790【木面】","Num":"1"},{"Reserved2":"2016-09-26","OuterIid":"012#欧式方桌【框架】","OuterSkuId":"1600*900*790【荷花白】","Num":"1"},{"Reserved2":"2016-09-26","OuterIid":"013#布餐椅","OuterSkuId":"美式边椅","Num":"1"},{"Reserved2":"2016-09-26","OuterIid":"013#布餐椅","OuterSkuId":"欧式边椅","Num":"1"},{"Reserved2":"2016-09-26","OuterIid":"015#美式皮餐椅","OuterSkuId":"扶手椅","Num":"1"},{"Reserved2":"2016-09-26","OuterIid":"015#欧式方桌【大理石面】","OuterSkuId":"1600*900【白冰花】","Num":"1"},{"Reserved2":"2016-09-26","OuterIid":"905#欧式布沙发","OuterSkuId":"贵妃位【1号色】","Num":"1"},{"Reserved2":"2016-09-26","OuterIid":"936#欧式布沙发","OuterSkuId":"三人【1号色】","Num":"1"},{"Reserved2":"2016-09-27","OuterIid":"012#欧式布餐椅","OuterSkuId":"边椅【1号色】","Num":"1"},{"Reserved2":"2016-09-27","OuterIid":"905#美式转角布沙发","OuterSkuId":"双扶手单人位【1号色】","Num":"1"},{"Reserved2":"2016-09-28","OuterIid":"012#欧式布餐椅","OuterSkuId":"扶手椅【1号色】","Num":"1"},{"Reserved2":"2016-09-28","OuterIid":"013#布餐椅","OuterSkuId":"欧式扶手椅","Num":"1"},{"Reserved2":"2016-09-28","OuterIid":"015#欧式方桌【大理石面】","OuterSkuId":"1600*900【白冰花】","Num":"1"},{"Reserved2":"2016-09-28","OuterIid":"015#欧式方桌【框架】","OuterSkuId":"1380*800*790【荷花白】","Num":"1"},{"Reserved2":"2016-09-28","OuterIid":"911B#欧式布沙发","OuterSkuId":"三人【1号色】","Num":"1"}]
     */

    private boolean IsOK;
    private Object ErrMsg;
    /**
     * Reserved2 : 2016-09-19
     * OuterIid : 012#欧式方桌
     * OuterSkuId : 1600*900*790【木面】
     * Num : 2
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
        private String Reserved2;
        private String OuterIid;
        private String OuterSkuId;
        private String Num;

        public String getReserved2() {
            return Reserved2;
        }

        public void setReserved2(String Reserved2) {
            this.Reserved2 = Reserved2;
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

        public String getNum() {
            return Num;
        }

        public void setNum(String Num) {
            this.Num = Num;
        }
    }
}
