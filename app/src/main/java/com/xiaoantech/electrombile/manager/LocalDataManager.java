package com.xiaoantech.electrombile.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.xiaoantech.electrombile.application.App;

/**
 * Created by yangxu on 2016/11/25.
 */

public class LocalDataManager {

    private final String SHARE_PREFERENCES = "SHARE_PREFERENCES";


    private final String MQTTHost = "MQTTHost";
    private final String MQTTPort = "MQTTPort";
    private final String HTTPHost = "HTTPHost";
    private final String HTTPPort = "HTTPPort";

    public final String MQTTHost_Release = "mqtt.xiaoan110.com";
    public final String MQTTPort_Release = "1883";
    public final String HTTPHost_Release = "http://api.xiaoan110.com";
    public final String HTTPPort_Release = "80";

    public final String MQTTHost_Test = "test.xiaoan110.com";
    public final String MQTTPort_Test = "1883";
    public final String HTTPHost_Test = "http://test.xiaoan110.com";
    public final String HTTPPort_Test = "8081";

    private SharedPreferences sharedPreferences;

    private  static LocalDataManager mInstance = null;

    public static LocalDataManager getInstance() {
        if (mInstance == null){
            mInstance = new LocalDataManager();
        }
        return mInstance;
    }

    private LocalDataManager(){
        Context context = App.getContext();
        sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES,Context.MODE_PRIVATE);
    }

    public void setMQTTHost(String mqttHost){
        sharedPreferences.edit().putString(MQTTHost,mqttHost).apply();
    }
    public String getMQTTHost() {
        return sharedPreferences.getString(MQTTHost,MQTTHost_Release);
    }

    public void setMQTTPort(String mqttPort) {
        sharedPreferences.edit().putString(MQTTPort,mqttPort).apply();
    }
    public String getMQTTPort() {
        return sharedPreferences.getString(MQTTPort,MQTTPort_Release);
    }

    public void setHTTPHost(String httpHost) {
        sharedPreferences.edit().putString(HTTPHost,httpHost).apply();
    }
    public String getHTTPHost(){
        return sharedPreferences.getString(HTTPHost,HTTPHost_Release);
    }
    public void setHTTPPort(String httpPort){
        sharedPreferences.edit().putString(HTTPPort,httpPort).apply();
    }
    public String getHTTPPort(){
        return sharedPreferences.getString(HTTPPort,HTTPPort_Release);
    }
}
