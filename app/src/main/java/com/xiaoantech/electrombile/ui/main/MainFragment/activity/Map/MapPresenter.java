package com.xiaoantech.electrombile.ui.main.MainFragment.activity.Map;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.xiaoantech.electrombile.constant.LayoutConstant;
import com.xiaoantech.electrombile.constant.MqttCommonConstant;
import com.xiaoantech.electrombile.event.cmd.LocationEvent;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.mqtt.MqttPublishManager;
import com.xiaoantech.electrombile.utils.TimeUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

/**
 * Created by yangxu on 2016/11/15.
 */

public class MapPresenter implements MapContract.Presenter,OnGetGeoCoderResultListener{
    private final static String TAG = "MapActivitypresenter";
    private MapContract.View   mMapView;
    private GeoCoder mSearch;
    public static int mapIndex = 0 ;

    protected MapPresenter(MapContract.View mapActivity){
        subscribe();
        this.mMapView = mapActivity;
        mMapView.setPresenter(this);

        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
    }

    @Override
    public void subscribe() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unsubscribe() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void refreshLocation() {
        MqttPublishManager.getInstance().getLocation(BasicDataManager.getInstance().getIMEI());
    }

    @Override
    public void changeMapType(int index){
        mapIndex++;
        switch (mapIndex%3){
            case 0:
                mMapView.changeMapType(LayoutConstant.MapType.MAP_TYPE_NORMAL);
                break;
            case 1:
                mMapView.changeMapType(LayoutConstant.MapType.MAP_TYPE_SATELLITE);
                break;
            case 2:
                mMapView.changeMapType(LayoutConstant.MapType.MAP_TYPE_3D);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationEvent(LocationEvent event){
        JSONObject jsonObject = event.getJsonObject();
        try {
            int code = jsonObject.getInt("code");
            if (code != MqttCommonConstant.CODE_SUCCESS && code != MqttCommonConstant.CODE_WAITING){
                dealWithErrorCode(code);
            }else{
                JSONObject result = jsonObject.getJSONObject("result");
                double lat = result.getDouble("lat");
                double lng = result.getDouble("lng");
                long timestamp = result.getLong("timestamp");
                LatLng point = new LatLng(lat,lng);
                mMapView.changeGPSPoint(point);
                mMapView.changeDateInfo(TimeUtil.getDateStringFromTimeStamp(timestamp));
                mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(point));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        ReverseGeoCodeResult.AddressComponent addressComponent = result.getAddressDetail();
        mMapView.changePlaceInfo(addressComponent.district+addressComponent.street+addressComponent.streetNumber);
    }



    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    private void dealWithErrorCode(int errorCode){
        if (errorCode == MqttCommonConstant.CODE_INTERNAL_ERR){
            mMapView.showToast("服务器内部错误!");
    }else  if (errorCode == MqttCommonConstant.CODE_DEVICE_OFFLINE){
            mMapView.showToast("设备离线，请检查设备！");
            //TODO:ErrorShow
        }
    }


}
