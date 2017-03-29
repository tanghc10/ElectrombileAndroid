package com.xiaoantech.electrombile.http;

import com.xiaoantech.electrombile.event.http.httpPost.HttpPostATTest;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostAlarm;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostAutoLockGet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostAutoLockSet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBattery;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBatteryType;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBluetoothBind;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBluetoothDel;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBluetoothSet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostDeviceMSG;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostDeviceStart;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostDeviceStop;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostElectricGet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostElectricSet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostFenceGet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostFenceSet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostFilename;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostGPS;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostGPSSignal;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostGSMSignal;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostLockGet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostLockSet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostLog;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostReset;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostServer;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostStatus;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostSwitchGet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostSwitchSet;

/**
 * Created by yangxu on 2017/3/27.
 */

public class HttpCallback {
    private static HttpCallback mInstance = null;

    public static HttpCallback getmInstance(){
        if (mInstance == null){
            mInstance = new HttpCallback();
        }
        return mInstance;
    }

    public static void dealWithHttpPost(HttpManager.postType postType,String result){
        switch (postType){
            case POST_TYPE_STATUS:
                HttpPostStatus.getmInstance().StatusResult(result);
                break;
            case POST_TYPE_GPS:
                HttpPostGPS.getmInstance().GPSResult(result);
                break;
            case POST_TYPE_AUTOLOCK_SET:
                HttpPostAutoLockSet.getmInstance().AutoLockSetResult(result);
                break;
            case POST_TYPE_AUTOLOCK_GET:
                HttpPostAutoLockGet.getmInstance().AutoLockGetResult(result);
                break;
            case POST_TYPE_FENCE_SET:
                HttpPostFenceSet.getmInstance().FenceGetResult(result);
                break;
            case POST_TYPE_FENCE_GET:
                HttpPostFenceGet.getmInstance().FenceGetResult(result);
                break;
            case POST_TYPE_BATTERY:
                HttpPostBattery.getmInstance().BatteryResult(result);
                break;
            case POST_TYPE_BATTERY_TYPE:
                HttpPostBatteryType.getmInstance().BatteryTypeResult(result);
                break;
            case POST_TYPE_DEVICE_START:
                HttpPostDeviceStart.getmInstance().DeviceStartResult(result);
                break;
            case POST_TYPE_DEVICE_STOP:
                HttpPostDeviceStop.getmInstance().DeviceStopResult(result);
                break;
            case POST_TYPE_DEFAULT:
                HttpPostBluetoothBind.getmInstance().BluetoothBindResult(result);
                break;
            case POST_TYPE_SERVER:
                HttpPostServer.getmInstance().ServerSetResult(result);
                break;
            case POST_TYPE_FILENAME:
                HttpPostFilename.getmInstance().FilenameSetResult(result);
                break;
            case POST_TYPE_SET_BLUETOOTH:
                HttpPostBluetoothSet.getmInstance().BluetoothSetResult(result);
                break;
            case POST_TYPE_ALARM:
                HttpPostAlarm.getmInstance().AlarmResult(result);
                break;
            case POST_TYPE_LOCKON_SET:
                HttpPostLockSet.getmInstance().LockSetResult(result);
                break;
            case POST_TYPE_DEVICEMSG:
                HttpPostDeviceMSG.getmInstance().DeviceMSGResult(result);
                break;
            case POST_TYPE_SIGNAL_GPS:
                HttpPostGPSSignal.getmInstance().GPSSignalResult(result);
                break;
            case POST_TYPE_SIGNAL_GSM:
                HttpPostGSMSignal.getmInstance().GSMSignalResult(result);
                break;
            case POST_TYPE_ATTEST:
                HttpPostATTest.getmInstance().ATTestResult(result);
                break;
            case POST_TYPE_LOG:
                HttpPostLog.getmInstance().LogGetResult(result);
                break;
            case POST_TYPE_RESET:
                HttpPostReset.getmInstance().ResetResult(result);
                break;
            case POST_TYPE_SWITCH_SET:
                HttpPostSwitchSet.getmInstance().SwitchSetResult(result);
                break;
            case POST_TYPE_SWITCH_GET:
                HttpPostSwitchGet.getmInstance().SwitchGetResult(result);
                break;
            case POST_TYPE_LOCKON_GET:
                HttpPostLockGet.getmInstance().LockGetResult(result);
                break;
            case POST_TYPE_ELECTRICLOCK_SET:
                HttpPostElectricSet.getmInstance().ElectricSetResult(result);
                break;
            case POST_TYPE_ELECTRICLOCK_GET:
                HttpPostElectricGet.getmInstance().ElectricGetResult(result);
                break;
            case POST_TYPE_DEL_BLUETOOTH_IMEI:
                HttpPostBluetoothDel.getmInstance().BluetoothDelResult(result);
                break;
            default:

        }
    }

}
