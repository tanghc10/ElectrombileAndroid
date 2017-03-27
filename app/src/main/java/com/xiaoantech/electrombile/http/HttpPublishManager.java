package com.xiaoantech.electrombile.http;

import android.webkit.JsPromptResult;

import com.xiaoantech.electrombile.constant.HttpConstant;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.manager.HttpManager;
import com.xiaoantech.electrombile.manager.LocalDataManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/26.
 */

public class HttpPublishManager {
    private static String TAG = "HttpPublishManager";
    private static HttpPublishManager mInstance = null;

    public static HttpPublishManager getmInstance(){
        if (null == mInstance){
            mInstance = new HttpPublishManager();
        }
        return mInstance;
    }

    public void getStatus(){
        try{
            JSONObject cmd = new JSONObject();
            cmd.put("c", 0);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_STATUS, HttpConstant.HttpCmd.HTTP_CMD_GET_STATUS, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getGPS(){
        try{
            JSONObject cmd = new JSONObject();
            cmd.put("c", 1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_GPS, HttpConstant.HttpCmd.HTTP_CMD_GET_GPS, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setAutoLock(int sw, int period){
        try{
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            param.put("period", period);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 2);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_AUTOLOCK, HttpConstant.HttpCmd.HTTP_CMD_SET_AUTOLOCK, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getAutoLock(){
        try{
            JSONObject cmd = new JSONObject();
            cmd.put("c", 3);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_AUTOLOCK, HttpConstant.HttpCmd.HTTP_CMD_GET_AUTOLOCK, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setFence(int defend){
        try {
            JSONObject param = new JSONObject();
            param.put("defend", defend);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 4);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_FENCE, HttpConstant.HttpCmd.HTTP_CMD_SET_FENCE, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getFence(){
        try{
            JSONObject cmd = new JSONObject();
            cmd.put("c", 5);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_FENCE, HttpConstant.HttpCmd.HTTP_CMD_GET_FENCE, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getBattery(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 6);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_BATTERY, HttpConstant.HttpCmd.HTTP_CMD_GET_BATTERY, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setBatteryType(int batterytype){
        try {
            JSONObject param = new JSONObject();
            param.put("batterytype", batterytype);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 7);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_BATTERY_TYPE, HttpConstant.HttpCmd.HTTP_CMD_SET_BATTERY_TYPE, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void startRecord(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 8);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_DEVICE, HttpConstant.HttpCmd.HTTP_CMD_START_RECORD, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void stopRecord(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 9);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_DEVICE, HttpConstant.HttpCmd.HTTP_CMD_STOP_RECORD, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void addBluetoothId(int bluetoothId){
        try {
            JSONObject param = new JSONObject();
            param.put("bluetoothId", bluetoothId);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 10);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_DEFAULT, HttpConstant.HttpCmd.HTTP_CMD_DEFAULT, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setServer(int type, String server){
        try {
            JSONObject param = new JSONObject();
            param.put("type", type);
            param.put("server",server);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 11);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_SERVER, HttpConstant.HttpCmd.HTTP_CMD_SET_SERVER, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setFileName(int use, String fileName){
        try {
            JSONObject param = new JSONObject();
            param.put("use", use);
            param.put("fileName",fileName);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 12);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_FILENAME, HttpConstant.HttpCmd.HTTP_CMD_SET_FILENAME, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setBluetooth(int sw){
        try {
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 13);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_SET_BLUETOOTH, HttpConstant.HttpCmd.HTTP_CMD_SET_BLUETOOTH, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setAlarm(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 14);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_ALARM, HttpConstant.HttpCmd.HTTP_CMD_SETPHONEALARM, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setLockOn(int sw){
        try {
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 15);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_LOCKON, HttpConstant.HttpCmd.HTTP_CMD_SET_LOCKON, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getDeviceMSG(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 16);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_DEVICEMSG, HttpConstant.HttpCmd.HTTP_CMD_GET_DEVICEMSG, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getGPSSignal(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 17);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_SIGNAL, HttpConstant.HttpCmd.HTTP_CMD_GET_GPS_SIGNAL, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getGSMSignal(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 18);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_SIGNAL, HttpConstant.HttpCmd.HTTP_CMD_GET_GSM_SIGNAL, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setATTest(String AT){
        try {
            JSONObject param = new JSONObject();
            param.put("AT", AT);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 19);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_ATTEST, HttpConstant.HttpCmd.HTTP_CMD_SET_ATTEST, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getLog(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 20);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_LOG, HttpConstant.HttpCmd.HTTP_CMD_GET_LOG, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setReset(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 21);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_RESET, HttpConstant.HttpCmd.HTTP_CMD_SET_RESET, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setLinkSwitch(int sw){
        try {
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 22);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_SWITCH, HttpConstant.HttpCmd.HTTP_CMD_SET_LINK_SWITCH, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getLinkSwitch(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 23);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_SWITCH, HttpConstant.HttpCmd.HTTP_CMD_GET_LINK_SWITCH, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getLockOn(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 24);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_LOCKON, HttpConstant.HttpCmd.HTTP_CMD_GET_LOCKON, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setLinkElectricLock(int sw){
        try {
            JSONObject param = new JSONObject();
            param.put("sw", sw);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 25);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_ELECTRICLOCK, HttpConstant.HttpCmd.HTTP_CMD_SET_LINK_ELECTRICLOCK, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getLinkElectricLock(){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c", 26);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_ELECTRICLOCK, HttpConstant.HttpCmd.HTTP_CMD_GET_LINK_ELECTRICLOCK, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void delBluetoothIMEI(String bluetoothId){
        try {
            JSONObject param = new JSONObject();
            param.put("bluetoothId", bluetoothId);
            JSONObject cmd = new JSONObject();
            cmd.put("c", 27);
            cmd.put("param", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd", cmd);
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_DEL_BLUETOOTH_IMEI, HttpConstant.HttpCmd.HTTP_CMD_DEL_BLUETOOTH_IMEI, jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
