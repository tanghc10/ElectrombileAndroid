package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostAutoLockSet {
    private int code;
    private static HttpPostAutoLockSet mInstance = null;

    public static HttpPostAutoLockSet getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostAutoLockSet();
        }
        return mInstance;
    }

    public void AutoLockSetResult(String resultStr){
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
