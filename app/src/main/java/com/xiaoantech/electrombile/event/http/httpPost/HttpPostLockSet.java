package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostLockSet {
    private int code;
    private static HttpPostLockSet mInstance = null;

    public static HttpPostLockSet getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostLockSet();
        }
        return mInstance;
    }

    public void LockSetResult(String resultStr){
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
