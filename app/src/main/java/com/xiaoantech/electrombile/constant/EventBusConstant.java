package com.xiaoantech.electrombile.constant;

/**
 * Created by yangxu on 2016/11/2.
 */

public class EventBusConstant {
    public enum  eventType{
        EventType_FenceON,                 //设置小安宝开关
        EventType_FenceOff,                 //
        EventType_FenceGet,                 //获取小安宝开关开关
        EventType_AutoLockSet,              //设置自动落锁
        EventType_AutoLockGet,              //获取自动落锁
        EventType_AutoPeriodSet,            //设置落锁时间
        EventType_AutoPeriodGet,            //获取落锁时间
        EventType_AutoStatusGet,            //自动落锁状态获取
        EventType_CMDGPSGET,                 //CMDGPS数据获得
        EventType_FetchItinerary           //获取总里程数
        }

//    public enum carSituationType{
//        carSituation_Online,
//        carSituation_Waiting,
//        carSituation_Offline,
//        carSituation_Unkown
//    }
}
