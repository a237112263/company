package com.keyi.report.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class Shopname implements Serializable{


    private static final long serialVersionUID = -84445649L;
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

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = -86796614L;


        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            private static final long serialVersionUID = -4894494L;
            private String SellerNick;

            public String getSellerNick() {
                return SellerNick;
            }

            public void setSellerNick(String SellerNick) {
                this.SellerNick = SellerNick;
            }
        }
    }
}
