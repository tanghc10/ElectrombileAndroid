package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangxu on 2017/3/27.
 */

public class HttpPostBattery {
    private int code;
    private int percent;

    public HttpPostBattery(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            this.code = jsonObject.getInt("code");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }

    public int getPercent() {
        return percent;
    }
}
