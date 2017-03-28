package com.xiaoantech.electrombile.event.http.httpPost;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 73843 on 2017/3/27.
 */

public class HttpPostStatus {
    private int code;
    private int sw;
    private int period;
    private int percent;
    private int type;
    private int defend;
    private int timestamp;
    private double lat;
    private double lng;
    private int speed;
    private int cource;
    private static HttpPostStatus mInstance = null;

    public static HttpPostStatus getmInstance() {
        if (mInstance == null) {
            mInstance = new HttpPostStatus();
        }
        return mInstance;
    }

    public void StatusResult(String resultStr) {
        try {
            JSONObject jsonObject = new JSONObject(resultStr);
            if (jsonObject.has("code")) {
                this.code = jsonObject.getInt("code");
                if (code == 0) {
                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONObject autolock = result.getJSONObject("autolock");
                    this.sw = autolock.getInt("sw");
                    this.period = autolock.getInt("period");
                    JSONObject battery = result.getJSONObject("battery");
                    this.percent = battery.getInt("percent");
                    this.type = battery.getInt("type");
                    this.defend = result.getInt("defend");
                    JSONObject gps = result.getJSONObject("gps");
                    this.timestamp = gps.getInt("timestamp");
                    this.lat = gps.getDouble("lat");
                    this.lng = gps.getDouble("lng");
                    this.speed = gps.getInt("speed");
                    this.cource = gps.getInt("cource");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }

    public int getSw() {
        return sw;
    }

    public int getPeriod() {
        return period;
    }

    public int getPercent(){
        return percent;
    }

    public int getType(){
        return type;
    }

    public int getDefend(){
        return defend;
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