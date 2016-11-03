package com.keyi.report.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MonthShop {

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


        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String Dates;
            private String SellerNick;
            private double SalesFee;
            private double payment;
            private double BrushFee;
            private double RealFee;

            public String getDates() {
                return Dates;
            }

            public void setDates(String Dates) {
                this.Dates = Dates;
            }

            public String getSellerNick() {
                return SellerNick;
            }

            public void setSellerNick(String SellerNick) {
                this.SellerNick = SellerNick;
            }

            public double getSalesFee() {
                return SalesFee;
            }

            public void setSalesFee(double SalesFee) {
                this.SalesFee = SalesFee;
            }

            public double getPayment() {
                return payment;
            }

            public void setPayment(double payment) {
                this.payment = payment;
            }

            public double getBrushFee() {
                return BrushFee;
            }

            public void setBrushFee(double BrushFee) {
                this.BrushFee = BrushFee;
            }

            public double getRealFee() {
                return RealFee;
            }

            public void setRealFee(double RealFee) {
                this.RealFee = RealFee;
            }
        }
    }
}
