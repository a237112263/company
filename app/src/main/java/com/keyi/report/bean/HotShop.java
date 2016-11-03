package com.keyi.report.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class HotShop {


    private boolean IsOK;
    private Object ErrMsg;

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
        private String SellerNick;
        private Object OuterIid;
        private Object OuterSkuId;
        private Object Dates;
        private double Payment;

        public int getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }

        public String getSellerNick() {
            return SellerNick;
        }

        public void setSellerNick(String SellerNick) {
            this.SellerNick = SellerNick;
        }

        public Object getOuterIid() {
            return OuterIid;
        }

        public void setOuterIid(Object OuterIid) {
            this.OuterIid = OuterIid;
        }

        public Object getOuterSkuId() {
            return OuterSkuId;
        }

        public void setOuterSkuId(Object OuterSkuId) {
            this.OuterSkuId = OuterSkuId;
        }

        public Object getDates() {
            return Dates;
        }

        public void setDates(Object Dates) {
            this.Dates = Dates;
        }

        public double getPayment() {
            return Payment;
        }

        public void setPayment(double Payment) {
            this.Payment = Payment;
        }
    }
}
