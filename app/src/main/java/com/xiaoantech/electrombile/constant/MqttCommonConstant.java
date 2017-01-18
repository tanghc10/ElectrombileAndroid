package com.xiaoantech.electrombile.constant;

/**
 * Created by yangxu on 2016/11/2.
 */

public class MqttCommonConstant {

    public final static String CMD = "cmd";
    public final static String GPS = "gps";
    public final static String ALARM = "alarm";
    public final static String NOTIFY = "notify";
    public final static String FOURTT = "intensity";

    public final static String Period = "period";
    public final static String Type = "type";

    public final static int CMD_FENCE_ON = 1;
    public final static int CMD_FENCE_OFF = 2;
    public final static int CMD_FENCE_GET = 3;
    public final static int CMD_SEEK_ON = 4;
    public final static int CMD_SEEK_OFF = 5;
    public final static int CMD_LOCATION = 6;
    public final static int CMD_AUTO_LOCK_ON = 7;
    public final static int CMD_AUTO_LOCK_OFF = 8;
    public final static int CMD_AUTO_PERIOD_SET = 9;
    public final static int CMD_AUTO_PERIOD_GET = 10;
    public final static int CMD_AUTOLOCK_GET = 11;
    public final static int CMD_BATTERY = 12;
    public final static int CMD_STATUS_GET = 13;
    public final static int CMD_SET_BATTERY_TYPE = 14;

    public final static int CODE_SUCCESS = 0;
    public final static int CODE_INTERNAL_ERR = 100;
    public final static int CODE_WAITING    =   101;
    public final static int CODE_DEVICE_OFFLINE = 102;
    public final static int CODE_BATTERY_LEARNING = 103;
}
