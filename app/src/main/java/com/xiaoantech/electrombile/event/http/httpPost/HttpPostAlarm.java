package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostAlarm {
    private int code;
    private static HttpPostAlarm mInstance = null;

    public static HttpPostAlarm getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostAlarm();
        }
        return mInstance;
    }

    public void AlarmResult(String resultStr){
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
