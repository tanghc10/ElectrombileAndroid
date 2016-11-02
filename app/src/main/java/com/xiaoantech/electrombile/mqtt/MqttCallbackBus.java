package com.xiaoantech.electrombile.mqtt;

import android.util.Log;

import com.xiaoantech.electrombile.constant.EventBusConstant;
import com.xiaoantech.electrombile.constant.MqttCallbackConstant;
import com.xiaoantech.electrombile.constant.MqttCommonConstant;
import com.xiaoantech.electrombile.event.FenceEvent;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangxu on 2016/11/2.
 */

public class MqttCallbackBus implements MqttCallback {
    private static final String TAG = "MqttCallback";
    @Override
    public void connectionLost(Throwable cause) {
        //TODO:connectionLost
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String messageStr = message.toString();
        if (null == messageStr){
            //TODO:Empty Message
            return;
        }
        try {
             JSONObject jsonObject = new JSONObject(messageStr);
            if (messageStr.contains(MqttCommonConstant.CMD)){
                receivedCMDMessage(jsonObject);
            }else if (messageStr.contains(MqttCommonConstant.GPS)){

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        Log.d(TAG,message.toString());
    }

    private void receivedCMDMessage(JSONObject jsonObject){
        try {
            int code =  Integer.valueOf(jsonObject.get(MqttCommonConstant.CMD).toString());
            switch (code){
                case MqttCommonConstant.CMD_FENCE_ON:
                    EventBus.getDefault().post(new FenceEvent(EventBusConstant.eventType.EventType_FenceON,jsonObject));

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void receivedGPSMessage(JSONObject jsonObject){

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

}
