package com.xiaoantech.electrombile.http;

import com.xiaoantech.electrombile.constant.HttpConstant;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.manager.LocalDataManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/26.
 */

public class HttpPublishManager {
    private static String TAG = "HttpPublishManager";
    private static HttpPublishManager mInstance = null;

    public static HttpPublishManager getInstance(){
        if (null == mInstance){
            mInstance = new HttpPublishManager();
        }
        return mInstance;
    }

    public void getStatus(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_STATUS, HttpConstant.HttpCmd.HTTP_CMD_GET_STATUS, getStringWithCmd(0));
    }

    public void getGPS(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_GPS, HttpConstant.HttpCmd.HTTP_CMD_GET_GPS, getStringWithCmd(1));
    }

    public void setAutoLock(int sw, int period){
        try{
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            param.put("period", period);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_AUTOLOCK_SET, HttpConstant.HttpCmd.HTTP_CMD_SET_AUTOLOCK, getStringWithCmdAndParam(2, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getAutoLock(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_AUTOLOCK_GET, HttpConstant.HttpCmd.HTTP_CMD_GET_AUTOLOCK, getStringWithCmd(3));
    }

    public void setFence(int defend){
        try {
            JSONObject param = new JSONObject();
            param.put("defend", defend);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_FENCE_SET, HttpConstant.HttpCmd.HTTP_CMD_SET_FENCE, getStringWithCmdAndParam(4, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getFence(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_FENCE_GET, HttpConstant.HttpCmd.HTTP_CMD_GET_FENCE, getStringWithCmd(5));
    }

    public void getBattery(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_BATTERY, HttpConstant.HttpCmd.HTTP_CMD_GET_BATTERY, getStringWithCmd(6));
    }

    public void setBatteryType(int batterytype){
        try {
            JSONObject param = new JSONObject();
            param.put("batterytype", batterytype);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_BATTERY_TYPE, HttpConstant.HttpCmd.HTTP_CMD_SET_BATTERY_TYPE, getStringWithCmd(7));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void startRecord(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_DEVICE_START, HttpConstant.HttpCmd.HTTP_CMD_START_RECORD, getStringWithCmd(8));
    }

    public void stopRecord(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_DEVICE_STOP, HttpConstant.HttpCmd.HTTP_CMD_STOP_RECORD, getStringWithCmd(9));
    }

    public void addBluetoothId(int bluetoothId){
        try {
            JSONObject param = new JSONObject();
            param.put("bluetoothId", bluetoothId);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_BLUETOOTH_DEFAULT, HttpConstant.HttpCmd.HTTP_CMD_DEFAULT, getStringWithCmdAndParam(10, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setServer(int type, String server){
        try {
            JSONObject param = new JSONObject();
            param.put("type", type);
            param.put("server",server);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_SERVER, HttpConstant.HttpCmd.HTTP_CMD_SET_SERVER, getStringWithCmdAndParam(11, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setFileName(int use, String fileName){
        try {
            JSONObject param = new JSONObject();
            param.put("use", use);
            param.put("fileName",fileName);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_FILENAME, HttpConstant.HttpCmd.HTTP_CMD_SET_FILENAME, getStringWithCmdAndParam(12, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setBluetooth(int sw){
        try {
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_SET_BLUETOOTH, HttpConstant.HttpCmd.HTTP_CMD_SET_BLUETOOTH, getStringWithCmdAndParam(13, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setAlarm(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_ALARM, HttpConstant.HttpCmd.HTTP_CMD_SETPHONEALARM, getStringWithCmd(14));
    }

    public void setLockOn(int sw){
        try {
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_LOCKON_SET, HttpConstant.HttpCmd.HTTP_CMD_SET_LOCKON, getStringWithCmdAndParam(15, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getDeviceMSG(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_DEVICEMSG, HttpConstant.HttpCmd.HTTP_CMD_GET_DEVICEMSG, getStringWithCmd(16));
    }

    public void getGPSSignal(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_SIGNAL_GPS, HttpConstant.HttpCmd.HTTP_CMD_GET_GPS_SIGNAL, getStringWithCmd(17));
    }

    public void getGSMSignal(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_SIGNAL_GSM, HttpConstant.HttpCmd.HTTP_CMD_GET_GSM_SIGNAL, getStringWithCmd(18));
    }

    public void setATTest(String AT){
        try {
            JSONObject param = new JSONObject();
            param.put("AT", AT);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_ATTEST, HttpConstant.HttpCmd.HTTP_CMD_SET_ATTEST, getStringWithCmdAndParam(19, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getLog(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_LOG, HttpConstant.HttpCmd.HTTP_CMD_GET_LOG, getStringWithCmd(20));
    }

    public void setReset(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_RESET, HttpConstant.HttpCmd.HTTP_CMD_SET_RESET, getStringWithCmd(21));
    }

    public void setLinkSwitch(int sw){
        try {
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_SWITCH_SET, HttpConstant.HttpCmd.HTTP_CMD_SET_LINK_SWITCH, getStringWithCmdAndParam(22, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getLinkSwitch(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_SWITCH_GET, HttpConstant.HttpCmd.HTTP_CMD_GET_LINK_SWITCH, getStringWithCmd(23));
    }

    public void getLockOn(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_LOCKON_GET, HttpConstant.HttpCmd.HTTP_CMD_GET_LOCKON, getStringWithCmd(24));
    }

    public void setLinkElectricLock(int sw){
        try {
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_ELECTRICLOCK_SET, HttpConstant.HttpCmd.HTTP_CMD_SET_LINK_ELECTRICLOCK, getStringWithCmdAndParam(25, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getLinkElectricLock(){
        HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_ELECTRICLOCK_GET, HttpConstant.HttpCmd.HTTP_CMD_GET_LINK_ELECTRICLOCK, getStringWithCmd(26));
    }

    public void delBluetoothIMEI(String bluetoothId){
        try {
            JSONObject param = new JSONObject();
            param.put("bluetoothId", bluetoothId);
            HttpManager.postHttpResult(getDeviceUrl(), HttpManager.postType.POST_TYPE_DEL_BLUETOOTH_IMEI, HttpConstant.HttpCmd.HTTP_CMD_DEL_BLUETOOTH_IMEI, getStringWithCmdAndParam(27, param));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String getDeviceUrl(){
        return LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
    }

    private String getStringWithCmd(int cmd){
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonCmd = new JSONObject();
            jsonCmd.put("c", cmd);
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", jsonCmd);
            return jsonObject.toString();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    private String getStringWithCmdAndParam(int cmd,JSONObject param){
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonCmd = new JSONObject();
            jsonCmd.put("c", cmd);
            jsonCmd.put("param",param);
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", jsonCmd);
            return jsonObject.toString();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
