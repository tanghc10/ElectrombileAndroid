package com.xiaoantech.electrombile.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangxu on 2016/11/16.
 */

public class TimeUtil {

    /**
     * 获取时间字符串，格式为：yyyy-mm-dd,HH:mm:ss
     *
     * @param timestamp long
     *
     * @return dataStr String类的实例
     */
    public static String getDateStringFromTimeStamp(long timestamp){
        Date date = new Date(timestamp*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        return sdf.format(date);
    }
}
