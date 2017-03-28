package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostFenceSet {
    private int code;
    private static HttpPostFenceSet mInstance = null;

    public static HttpPostFenceSet getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostFenceSet();
        }
        return mInstance;
    }

    public void FenceGetResult(String resultStr){
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
