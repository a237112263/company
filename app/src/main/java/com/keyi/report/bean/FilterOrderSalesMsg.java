package com.keyi.report.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class FilterOrderSalesMsg {

    /**
     * IsOK : true
     * ErrMsg : null
     * Data : [{"PayTime":"2016/9/14 15:47:39","OuterIid":"911#欧式布沙发","OuterSkuId":"三人【4号色】","Num":"1","SellerNick":"tb056387_2010","BuyerNick":"tb8090946_88","Payment":"3576.00"},{"PayTime":"2016/9/14 15:47:39","OuterIid":null,"OuterSkuId":null,"Num":"1","SellerNick":"tb056387_2010","BuyerNick":"tb8090946_88","Payment":"3576.00"},{"PayTime":"2016/9/14 15:41:26","OuterIid":"911#美式布沙发","OuterSkuId":"三人【1号色】","Num":"1","SellerNick":"tb056387_2010","BuyerNick":"妞妞宝贝爱玩具","Payment":"3876.00"},{"PayTime":"2016/9/14 15:41:26","OuterIid":null,"OuterSkuId":null,"Num":"1","SellerNick":"tb056387_2010","BuyerNick":"妞妞宝贝爱玩具","Payment":"3876.00"},{"PayTime":"2016/9/14 15:36:27","OuterIid":"905#美式转角布沙发","OuterSkuId":"双扶手单人位【2号色】","Num":"1","SellerNick":"qq251116168","BuyerNick":"mafeimeinv","Payment":"2740.00"},{"PayTime":"2016/9/14 15:12:39","OuterIid":"911#美式皮沙发","OuterSkuId":"单人【1号色】","Num":"1","SellerNick":"qq251116168","BuyerNick":"若不离有你真好","Payment":"3914.00"},{"PayTime":"2016/9/14 15:09:05","OuterIid":"911#美式皮沙发","OuterSkuId":"单人【1号色】","Num":"1","SellerNick":"qq251116168","BuyerNick":"aqsierxcqun","Payment":"3914.00"},{"PayTime":"2016/9/14 15:09:05","OuterIid":null,"OuterSkuId":null,"Num":"1","SellerNick":"qq251116168","BuyerNick":"aqsierxcqun","Payment":"3914.00"},{"PayTime":"2016/9/14 15:02:25","OuterIid":"905#美式转角布沙发","OuterSkuId":"双扶手单人位【1号色】","Num":"1","SellerNick":"tb056387_2010","BuyerNick":"眉开眼笑889","Payment":"2740.00"},{"PayTime":"2016/9/14 15:02:25","OuterIid":null,"OuterSkuId":null,"Num":"1","SellerNick":"tb056387_2010","BuyerNick":"眉开眼笑889","Payment":"2740.00"},{"PayTime":"2016/9/14 14:58:53","OuterIid":"911#美式皮沙发","OuterSkuId":"单人【1号色】","Num":"1","SellerNick":"qq251116168","BuyerNick":"唯美19900908","Payment":"3914.00"},{"PayTime":"2016/9/14 14:58:53","OuterIid":null,"OuterSkuId":null,"Num":"1","SellerNick":"qq251116168","BuyerNick":"唯美19900908","Payment":"3914.00"},{"PayTime":"2016/9/14 14:58:00","OuterIid":"905#欧式转角布沙发","OuterSkuId":"双扶手单人位【1号色】","Num":"1","SellerNick":"tb056387_2010","BuyerNick":"六组华丽","Payment":"2533.00"},{"PayTime":"2016/9/14 14:55:27","OuterIid":"905#美式转角皮沙发","OuterSkuId":"双扶手单人位","Num":"1","SellerNick":"tb056387_2010","BuyerNick":"tb78838922","Payment":"3990.00"},{"PayTime":"2016/9/14 14:55:03","OuterIid":"901#美式皮沙发","OuterSkuId":"单人【2号色】","Num":"1","SellerNick":"tb056387_2010","BuyerNick":"819超越梦想","Payment":"5280.00"},{"PayTime":"2016/9/14 14:55:03","OuterIid":null,"OuterSkuId":null,"Num":"1","SellerNick":"tb056387_2010","BuyerNick":"819超越梦想","Payment":"5280.00"},{"PayTime":"2016/9/14 14:52:50","OuterIid":"911#美式皮沙发","OuterSkuId":"单人【1号色】","Num":"1","SellerNick":"qq251116168","BuyerNick":"lina880927","Payment":"3914.00"},{"PayTime":"2016/9/14 14:51:40","OuterIid":"905#欧式转角布沙发","OuterSkuId":"双扶手单人位【2号色】","Num":"1","SellerNick":"tb056387_2010","BuyerNick":"mashanshan_87","Payment":"2533.00"},{"PayTime":"2016/9/14 14:51:40","OuterIid":null,"OuterSkuId":null,"Num":"1","SellerNick":"tb056387_2010","BuyerNick":"mashanshan_87","Payment":"2533.00"},{"PayTime":"2016/9/14 14:49:28","OuterIid":"911#美式皮沙发","OuterSkuId":"单人【1号色】","Num":"1","SellerNick":"qq251116168","BuyerNick":"粉红蔷薇_love","Payment":"3914.00"}]
     */
    private boolean IsOK;
    private Object ErrMsg;
    /**
     * PayTime : 2016/9/14 15:47:39
     * OuterIid : 911#欧式布沙发
     * OuterSkuId : 三人【4号色】
     * Num : 1
     * SellerNick : tb056387_2010
     * BuyerNick : tb8090946_88
     * Payment : 3576.00
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
        private String PayTime;
        private String OuterIid;
        private String OuterSkuId;
        private String Num;
        private String SellerNick;
        private String BuyerNick;
        private String Payment;

        public String getPayTime() {
            return PayTime;
        }

        public void setPayTime(String PayTime) {
            this.PayTime = PayTime;
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

        public String getSellerNick() {
            return SellerNick;
        }

        public void setSellerNick(String SellerNick) {
            this.SellerNick = SellerNick;
        }

        public String getBuyerNick() {
            return BuyerNick;
        }

        public void setBuyerNick(String BuyerNick) {
            this.BuyerNick = BuyerNick;
        }

        public String getPayment() {
            return Payment;
        }

        public void setPayment(String Payment) {
            this.Payment = Payment;
        }
    }
}
