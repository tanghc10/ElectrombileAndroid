package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/29.
 */

public class HttpPostSwitchSet {
    private int code;
    private static HttpPostSwitchSet mInstance = null;

    public static HttpPostSwitchSet getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostSwitchSet();
        }
        return mInstance;
    }

    public void SwitchSetResult(String resultStr){
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
