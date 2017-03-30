package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostDeviceStart {
    private int code;
    private static HttpPostDeviceStart mInstance = null;

    public static HttpPostDeviceStart getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostDeviceStart();
        }
        return mInstance;
    }

    public void DeviceStartResult(String resultStr){
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
