package com.xiaoantech.electrombile.http;

import com.xiaoantech.electrombile.event.http.httpPost.HttpPostAutoLockGet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostAutoLockSet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBattery;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostBatteryType;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostFenceGet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostFenceSet;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostGPS;
import com.xiaoantech.electrombile.event.http.httpPost.HttpPostStatus;

/**
 * Created by yangxu on 2017/3/27.
 */

public class HttpCallback {
    private static void dealWithHttpPost(HttpManager.postType postType,String result){
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
            default:

        }
    }

}
