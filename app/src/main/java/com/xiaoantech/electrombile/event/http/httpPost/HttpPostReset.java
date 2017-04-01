package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/29.
 */

public class HttpPostReset {
    private int code;
    private static HttpPostReset mInstance = null;

    public static HttpPostReset getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostReset();
        }
        return mInstance;
    }

    public void ResetResult(String resultStr){
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
