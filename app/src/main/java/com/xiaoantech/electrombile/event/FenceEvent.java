package com.xiaoantech.electrombile.event;

import com.xiaoantech.electrombile.constant.EventBusConstant;

import org.json.JSONObject;

/**
 * Created by yangxu on 2016/11/2.
 */

public class FenceEvent extends CMDEvent{
    public FenceEvent(EventBusConstant.eventType type,JSONObject jsonObject){
        this.eventType = type;
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject(){
        return jsonObject;
    }

    public EventBusConstant.eventType getEventType(){
        return eventType;
    }
}
