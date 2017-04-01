package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/29.
 */

public class HttpPostElectricSet {
    private int code;
    private static HttpPostElectricSet mInstance = null;

    public static HttpPostElectricSet getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostElectricSet();
        }
        return mInstance;
    }

    public void ElectricSetResult(String resultStr){
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
