package com.keyi.report.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class HotShopSales {


    /**
     * IsOK : true
     * ErrMsg : null
     * Data : [{"Num":41,"OuterIid":"LSMYSFH-0401","outerSkuId":"XXY&MD1387-21&TJ"},{"Num":32,"OuterIid":"LSMYRCR3101","outerSkuId":"MA1820&5019-10#&FP&QD"},{"Num":32,"OuterIid":"MZH107701","outerSkuId":"SZ&XYH"},{"Num":30,"OuterIid":"LSMYRCR13601","outerSkuId":"FP2116&MA1820&CW&2"},{"Num":30,"OuterIid":"LSMYSF201301","outerSkuId":"ZT&ZP2117"},{"Num":20,"OuterIid":"23954368383","outerSkuId":"62031452301"},{"Num":20,"OuterIid":"JMSFJM1051A01","outerSkuId":"ZT&FPT"},{"Num":19,"OuterIid":"15376344250","outerSkuId":"18868887001"},{"Num":16,"OuterIid":"LSJCA301","outerSkuId":"MA1520&FGB"},{"Num":14,"OuterIid":"LSMYRCR3401","outerSkuId":"393-21393-20&MA1820"}]
     */

    private boolean IsOK;
    private Object ErrMsg;
    /**
     * Num : 41
     * OuterIid : LSMYSFH-0401
     * outerSkuId : XXY&MD1387-21&TJ
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
        private int Num;
        private String OuterIid;
        private String outerSkuId;

        public int getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }

        public String getOuterIid() {
            return OuterIid;
        }

        public void setOuterIid(String OuterIid) {
            this.OuterIid = OuterIid;
        }

        public String getOuterSkuId() {
            return outerSkuId;
        }

        public void setOuterSkuId(String outerSkuId) {
            this.outerSkuId = outerSkuId;
        }
    }
}
