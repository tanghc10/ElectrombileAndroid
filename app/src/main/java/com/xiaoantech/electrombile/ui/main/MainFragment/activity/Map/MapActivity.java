package com.xiaoantech.electrombile.ui.main.MainFragment.activity.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.constant.LayoutConstant;
import com.xiaoantech.electrombile.databinding.ActivityMapBinding;
import com.xiaoantech.electrombile.ui.main.MainFragment.activity.MapHistory.MapListActivity;

/**
 * Created by yangxu on 2016/11/15.
 */

public class MapActivity extends BaseAcitivity implements MapContract.View{
    private final static String         TAG = "MapActivity";
    private ActivityMapBinding          mBinding;
    private MapContract.Presenter       mPresenter;
    private ProgressDialog              mProgressDialog;
    private BaiduMap                    mBaiduMap;
    private Marker                      mMarker;
    @Override
    protected void initBefore() {

    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_map);
    }

    @Override
    protected void initView() {
        mPresenter = new MapPresenter(this);
        mBinding.setPresenter(mPresenter);
        mProgressDialog = new ProgressDialog(this);
        mBaiduMap = mBinding.mapView.getMap();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });
        initMarker();
    }

    private void initMarker(){
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.online);
        LatLng point = new LatLng(30.5171, 114.4392);
        MarkerOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmapDescriptor);
        //在地图上添加Marker，并显示
        mMarker = (Marker) mBaiduMap.addOverlay(option);
    }

    private void moveMapToCenter(LatLng point){
        MapStatus mapStatus = new MapStatus.Builder().target(point).zoom(18).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }

    public void setPresenter(MapContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void changeGPSPoint(LatLng point) {
        if (null == mBaiduMap){
            mBaiduMap = mBinding.mapView.getMap();
        }
        if (null == mMarker){
            initMarker();
        }

        mMarker.setPosition(point);
        moveMapToCenter(point);
    }

    @Override
    public void changePlaceInfo(String placeInfo) {
        mBinding.txtCarPlace.setText(placeInfo);
    }

    @Override
    public void changeDateInfo(String timeDate) {
        mBinding.txtLocationTime.setText(timeDate);
    }

    @Override
    public void gotoMapList(){
        Intent intent = new Intent(MapActivity.this,MapListActivity.class);
        startActivity(intent);
    }

    @Override
    public void changeMapType(LayoutConstant.MapType mapType) {
        switch (mapType){
            case MAP_TYPE_NORMAL:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                MapStatus mapStatusPlain = new MapStatus.Builder(mBaiduMap.getMapStatus()).overlook(0).build();
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatusPlain));
                break;
            case MAP_TYPE_SATELLITE:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case MAP_TYPE_3D:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                MapStatus mapStatus3D = new MapStatus.Builder(mBaiduMap.getMapStatus()).overlook(-45).build();
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatus3D));
        }
    }
}
