package com.keyi.report.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class DayShop implements Serializable{

    private static final long serialVersionUID = -45848245L;

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
            private double payment;
            private double RealFee;

            public String getDates() {
                return Dates;
            }

            public void setDates(String Dates) {
                this.Dates = Dates;
            }

            public double getPayment() {
                return payment;
            }

            public void setPayment(double payment) {
                this.payment = payment;
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
