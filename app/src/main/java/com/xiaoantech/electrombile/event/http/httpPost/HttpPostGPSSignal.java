package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostGPSSignal {
    private int code;
    private double GPSSignal;
    private static HttpPostGPSSignal mInstance = null;

    public static HttpPostGPSSignal getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostGPSSignal();
        }
        return mInstance;
    }

    public void GPSSignalResult(String resultStr){
        try {
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
                if (code == 0){
                    JSONObject result = jsonObject.getJSONObject("result");
                    this.GPSSignal = result.getDouble("GPSSignal");
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode(){
        return code;
    }

    public double getGPSSignal(){
        return GPSSignal;
    }
}
