package com.xiaoantech.electrombile.event.http.httpPost;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/29.
 */

public class HttpPostLog {
    private int code;
    private String log;
    private static HttpPostLog mInstance = null;

    public static HttpPostLog getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostLog();
        }
        return mInstance;
    }

    public void LogGetResult(String resultStr){
        try {
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
                if (code == 0){
                    JSONObject result = jsonObject.getJSONObject("result");
                    this.log = result.getString("log");
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode(){
        return code;
    }
}
