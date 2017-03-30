package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/29.
 */

public class HttpPostLockGet {
    private int code;
    private int sw;
    private static HttpPostLockGet mInstance = null;

    public static HttpPostLockGet getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostLockGet();
        }
        return mInstance;
    }

    public void LockGetResult(String resultStr){
        try {
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
                if (code == 0){
                    JSONObject result = jsonObject.getJSONObject("result");
                    this.sw = result.getInt("sw");
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode(){
        return code;
    }

    public int getSw(){
        return sw;
    }
}
