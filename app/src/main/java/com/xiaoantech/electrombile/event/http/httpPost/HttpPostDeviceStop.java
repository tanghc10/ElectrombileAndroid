package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostDeviceStop {
    private int code;
    private static HttpPostDeviceStop mInstance = null;

    public static HttpPostDeviceStop getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostDeviceStop();
        }
        return mInstance;
    }

    public void DeviceStopResult(String resultStr){
        try {
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode(){
        return code;
    }
}
