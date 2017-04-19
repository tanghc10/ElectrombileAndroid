package com.xiaoantech.electrombile.http;

import com.xiaoantech.electrombile.constant.HttpConstant;
import com.xiaoantech.electrombile.event.http.HttpPostEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostATTestEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostAlarmEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostAutoLockGetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostAutoLockSetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBatteryEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBatteryTypeEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBluetoothBindEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBluetoothDelEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBluetoothSetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostDeviceMSGEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostDeviceStartEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostDeviceStopEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostElectricGetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostElectricSetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostFenceGetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostFenceSetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostFilenameEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostGPSEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostGPSSignalEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostGSMSignalEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostLockGetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostLockSetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostLogEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostResetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostServerEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostStatusEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostSwitchGetEvent;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostSwitchSetEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yangxu on 2017/3/27.
 */

public class HttpCallback {

    public static void dealWithHttpPost(HttpManager.postType postType,String result){
        switch (postType){
            case POST_TYPE_STATUS:
                EventBus.getDefault().post(new HttpPostStatusEvent(result));
                break;
            case POST_TYPE_GPS:
                EventBus.getDefault().post(new HttpPostGPSEvent(result));
                break;
            case POST_TYPE_AUTOLOCK_SET:
                EventBus.getDefault().post(new HttpPostAutoLockSetEvent(result));
                break;
            case POST_TYPE_AUTOLOCK_GET:
                EventBus.getDefault().post(new HttpPostAutoLockGetEvent(result));
                break;
            case POST_TYPE_FENCE_SET:
                EventBus.getDefault().post(new HttpPostFenceSetEvent(result));
                break;
            case POST_TYPE_FENCE_GET:
                EventBus.getDefault().post(new HttpPostFenceGetEvent(result));
                break;
            case POST_TYPE_BATTERY:
                EventBus.getDefault().post(new HttpPostBatteryEvent(result));
                break;
            case POST_TYPE_BATTERY_TYPE:
                EventBus.getDefault().post(new HttpPostBatteryTypeEvent(result));
                break;
            case POST_TYPE_DEVICE_START:
                EventBus.getDefault().post(new HttpPostDeviceStartEvent(result));
                break;
            case POST_TYPE_DEVICE_STOP:
                EventBus.getDefault().post(new HttpPostDeviceStopEvent(result));
                break;
            case POST_TYPE_BLUETOOTH_DEFAULT:
                EventBus.getDefault().post(new HttpPostBluetoothBindEvent(result));
                break;
            case POST_TYPE_SERVER:
                EventBus.getDefault().post(new HttpPostServerEvent(result));
                break;
            case POST_TYPE_FILENAME:
                EventBus.getDefault().post(new HttpPostFilenameEvent(result));
                break;
            case POST_TYPE_SET_BLUETOOTH:
                EventBus.getDefault().post(new HttpPostBluetoothSetEvent(result));
                break;
            case POST_TYPE_ALARM:
                EventBus.getDefault().post(new HttpPostAlarmEvent(result));
                break;
            case POST_TYPE_LOCKON_SET:
                EventBus.getDefault().post(new HttpPostLockSetEvent(result));
                break;
            case POST_TYPE_DEVICEMSG:
                EventBus.getDefault().post(new HttpPostDeviceMSGEvent(result));
                break;
            case POST_TYPE_SIGNAL_GPS:
                EventBus.getDefault().post(new HttpPostGPSSignalEvent(result));
                break;
            case POST_TYPE_SIGNAL_GSM:
                EventBus.getDefault().post(new HttpPostGSMSignalEvent(result));
                break;
            case POST_TYPE_ATTEST:
                EventBus.getDefault().post(new HttpPostATTestEvent(result));
                break;
            case POST_TYPE_LOG:
                EventBus.getDefault().post(new HttpPostLogEvent(result));
                break;
            case POST_TYPE_RESET:
                EventBus.getDefault().post(new HttpPostResetEvent(result));
                break;
            case POST_TYPE_SWITCH_SET:
                EventBus.getDefault().post(new HttpPostSwitchSetEvent(result));
                break;
            case POST_TYPE_SWITCH_GET:
                EventBus.getDefault().post(new HttpPostSwitchGetEvent(result));
                break;
            case POST_TYPE_LOCKON_GET:
                EventBus.getDefault().post(new HttpPostLockGetEvent(result));
                break;
            case POST_TYPE_ELECTRICLOCK_SET:
                EventBus.getDefault().post(new HttpPostElectricSetEvent(result));
                break;
            case POST_TYPE_ELECTRICLOCK_GET:
                EventBus.getDefault().post(new HttpPostElectricGetEvent(result));
                break;
            case POST_TYPE_DEL_BLUETOOTH_IMEI:
                EventBus.getDefault().post(new HttpPostBluetoothDelEvent(result));
                break;

            case POST_TYPE_PHONE:
                EventBus.getDefault().post(new HttpPostEvent(HttpManager.postType.POST_TYPE_PHONE,result,true, HttpConstant.HttpCmd.HTTP_CMD_SETPHONEALARM));
            default:

        }
    }

}
