package com.keyi.report.utils;

import android.content.Context;
import android.support.annotation.NonNull;


/**
 * Created by Administrator on 2016/4/27.
 */
public class DatasUtils {
    public static final String strToken = "&Token=";
    public static final String strTime = "&time=";
    public static final String yanzhengUrl = "http://appweb.keyierp.com/sms.aspx?m=";//获取验证码
    public static final String mobUrl = "http://appweb.keyierp.com/ERP/Login.aspx?mobile=";//验证验证码
    public static final String hotShopUrl = "http://appweb.keyierp.com/Report/ReportHot.aspx?mobile=";//热销榜
    public static final String dayShopUrl = "http://appweb.keyierp.com/Report/Report_DaySales.aspx?mobile=";//日报表
    public static final String MonthUrl = "http://appweb.keyierp.com/Report/Report_MonthSales.aspx?mobile=";//月报表
    public static final String[] dataNume = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    public static final String[] dayNumber = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    public static final String ShopName = "http://appweb.keyierp.com/Report/Report_ShopName.aspx?mobile=";//获取店铺名
    public static final String inventCollectUrl = "http://appweb.keyierp.com/Report/Report_InventCollect.aspx?mobile=";//商品库存报表
    public static final String hotShopSalesUrl = "http://appweb.keyierp.com/Report/Report_HotSales.aspx?mobile=";//
    public static final String faHuoUrl="http://appweb.keyierp.com/Report/Report_ItemSaleNum.aspx?mobile=";//商品销量表发货
    public static final String shuaDanXiaoLiangUrl="http://appweb.keyierp.com//Report/Report_FilterOrderSales.aspx?mobile=";//刷单商品销量表
    public static final String tokenName = "&SellerNick=";

    public static final String headString(Context context, String str) {
        StringBuffer stringBuffer = new StringBuffer()
                .append(str)
                .append(getStringBuffer(context));
        return stringBuffer.toString();
    }

    @NonNull
    private static StringBuffer getStringBuffer(Context context) {
//        ACache aCache = ACache.get(context);
//        SMSData smsData = (SMSData) aCache.getAsObject("SMSData");
        return new StringBuffer()
                .append(SharedPreferencesUtils.getParam(context, "MobilNumber", ""))
                .append(strToken)
                .append(SharedPreferencesUtils.getParam(context, "SMSData", ""));
    }

    public static final String headString1(Context context, String str, String str1, String str2) {
        StringBuffer stringBuffer = new StringBuffer()
                .append(str)
                .append(getStringBuffer(context))
                .append(strTime)
                .append(str1)
                .append("-")
                .append(str2);
        return stringBuffer.toString();
    }

    public static final String headString2(Context context, String str, String str1, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer()
                .append(str)
                .append(getStringBuffer(context))
                .append(strTime)
                .append(str1)
                .append("-")
                .append(str2)
                .append(tokenName)
                .append(str3);
        return stringBuffer.toString();
    }

    public static final String inventCollectUrlSting(Context context,  int str1) {
        StringBuffer stringBuffer = new StringBuffer()
                .append(inventCollectUrl)
                .append(getStringBuffer(context))
                .append("&Page=")
                .append(str1);
        return stringBuffer.toString();
    }

    public static final String shuaDanXiaoLiangUrlSting(Context context,  int str1) {
        StringBuffer stringBuffer = new StringBuffer()
                .append(shuaDanXiaoLiangUrl)
                .append(getStringBuffer(context))
                .append("&Page=")
                .append(str1);
        return stringBuffer.toString();
    }

    public static final String faHuoUrlSting(Context context,  int str1) {
        StringBuffer stringBuffer = new StringBuffer()
                .append(faHuoUrl)
                .append(getStringBuffer(context))
                .append("&Page=")
                .append(str1);
        return stringBuffer.toString();
    }
}
