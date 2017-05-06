package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/28.
 */

public class HttpPostGPSEvent {
    private int code;
    private int timestamp;
    private double lat;
    private double lng;
    private int speed;

    public HttpPostGPSEvent(String resultStr){
        try{
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")){
                this.code = jsonObject.getInt("code");
                if (code == 0){
                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONObject gps = result.getJSONObject("gps");
                    this.timestamp = gps.getInt("timestamp");
                    this.lat = gps.getDouble("lat");
                    this.lng = gps.getDouble("lng");
                    this.speed = gps.getInt("speed");
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

}
