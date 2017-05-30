package com.xiaoantech.electrombile.utils;

/**
 * Created by yangxu on 2017/4/19.
 */

public class ErrorCodeConvertUtil {
    public final static int HTTPCodeSuccess = 0;
    public final static int HTTPCodeOffline = 109;
    public static String getHttpErrorStrWithCode(int code){
        String errStr = "";
        switch (code){
            case 100:
                errStr = "服务器内部错误!";
                break;
            case 101:
            case 102:
            case 103:
            case 104:
                errStr = "操作内容错误";
                break;
            case 106:
            case 107:
            case 108:
                errStr = "暂无响应，请稍后重试";
                break;
            case 109:
                errStr = "设备不在线";
                break;
            case 111:
                errStr = "设备不支持该操作";
                break;
        }
        return errStr;
    }

}
