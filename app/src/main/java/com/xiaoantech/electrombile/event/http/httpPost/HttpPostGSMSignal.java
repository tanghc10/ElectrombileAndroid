package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostGSMSignal {
    private int code;
    private int GSMSignal;
    private static HttpPostGSMSignal mInstance = null;

    public static HttpPostGSMSignal getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostGSMSignal();
        }
        return mInstance;
    }

    public void GSMSignalResult(String resultStr){
        try {
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
                if (code == 0){
                    JSONObject result = jsonObject.getJSONObject("result");
                    this.GSMSignal = result.getInt("GSMSignal");
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode(){
        return code;
    }

    public int getGSMSignal(){
        return GSMSignal;
    }
}
