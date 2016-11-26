package com.xiaoantech.electrombile.model;

/**
 * Created by yangxu on 2016/11/25.
 */

public class GPSPointModel {
    private long timestamp;
    private double lat;
    private double lng;

    public GPSPointModel(long timestamp,double lat,double lng){
        this.timestamp = timestamp;
        this.lat = lat;
        this.lng = lng;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    @Override
    public String toString(){
        return timestamp + "   " + lat + "    " + lng;
    }
}
