package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostFenceGet {
    private int code;
    private int defend;
    private static HttpPostFenceGet mInstance = null;

    public static HttpPostFenceGet getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostFenceGet();
        }
        return mInstance;
    }

    public void FenceGetResult(String resultStr){
        try{
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
                if (code == 0){
                    JSONObject result = jsonObject.getJSONObject("result");
                    this.defend = result.getInt("defend");
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode(){
        return code;
    }

    public int getDefend(){
        return defend;
    }
}
