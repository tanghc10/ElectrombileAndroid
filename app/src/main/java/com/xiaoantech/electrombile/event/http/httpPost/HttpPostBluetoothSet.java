package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostBluetoothSet {
    private int code;
    private static HttpPostBluetoothSet mInstance = null;

    public static HttpPostBluetoothSet getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostBluetoothSet();
        }
        return mInstance;
    }

    public void BluetoothSetResult(String resultStr){
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
