package com.xiaoantech.electrombile.event.http.httpPost;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostATTest {
    private int code;
    private String response;
    private static HttpPostATTest mInstance = null;

    public static HttpPostATTest getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostATTest();
        }
        return mInstance;
    }

    public void ATTestResult(String resultStr){
        try {
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
                if (code == 0){
                    JSONObject result = jsonObject.getJSONObject("result");
                    this.response = result.getString("response");
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode(){
        return code;
    }

    public String getResponse(){
        return response;
    }
}
