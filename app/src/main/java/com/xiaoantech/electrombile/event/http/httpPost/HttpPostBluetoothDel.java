package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/29.
 */

public class HttpPostBluetoothDel {
    private int code;
    private static HttpPostBluetoothDel mInstance = null;

    public static HttpPostBluetoothDel getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostBluetoothDel();
        }
        return mInstance;
    }

    public void BluetoothDelResult(String resultStr){
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
