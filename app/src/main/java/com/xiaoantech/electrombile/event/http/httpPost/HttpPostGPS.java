package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostGPS {
    private int code;
    private int timestamp;
    private double lat;
    private double lng;
    private int speed;
    private int cource;
    private static HttpPostGPS mInstance = null;

    public static HttpPostGPS getmInstance(){
        if (mInstance == null){
            mInstance = new HttpPostGPS();
        }
        return mInstance;
    }

    public void GPSResult(String resultStr){
        try{
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
                if (code == 0){
                    JSONObject result = jsonObject.getJSONObject("result");
                    this.timestamp = result.getInt("timestamp");
                    this.lat = result.getDouble("lat");
                    this.lng = result.getDouble("lng");
                    this.speed = result.getInt("speed");
                    this.cource = result.getInt("cource");
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getCode(){
        return code;
    }

    public int getTimestamp(){
        return timestamp;
    }

    public double getLat(){
        return lat;
    }

    public double getLng(){
        return lng;
    }

    public int getSpeed(){
        return speed;
    }

    public int getCource(){
        return cource;
    }
}
