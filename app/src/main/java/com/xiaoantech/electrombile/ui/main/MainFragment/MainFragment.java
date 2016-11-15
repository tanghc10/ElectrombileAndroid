package com.xiaoantech.electrombile.ui.main.MainFragment;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.application.App;
import com.xiaoantech.electrombile.base.BaseFragment;
import com.xiaoantech.electrombile.databinding.FragmentMainBinding;

/**
 * Created by yangxu on 2016/11/3.
 */

public class MainFragment extends BaseFragment implements MainFragmentConstract.View {
    private FragmentMainBinding mBinding;
    private MainFragmentConstract.Presenter mPresenter;
    private ProgressDialog      mProgressDialog;
    private BaiduMap            mBaiduMap;
    private Marker              mMarker;
    private GeoCoder            mSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        initView();
        return mBinding.getRoot();
    }

    @Override
    public void initView() {
        mPresenter = new MainFragmentPresenter(this);
        mBinding.setPresenter(mPresenter);
        mProgressDialog = new ProgressDialog(App.getContext());
        mBaiduMap = mBinding.mapview.getMap();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(final Marker marker) {
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

    @Override
    public void setPresenter(MainFragmentConstract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void showWaitingDialog(String dialogString) {
        try {
            mProgressDialog.setMessage(dialogString);
            mProgressDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        mBinding.mapview.onDestroy();
    }
    @Override
    public void onResume(){
        super.onResume();
        mBinding.mapview.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
        mBinding.mapview.onPause();
    }
    @Override
    public void hideWaitingDialog() {
        mProgressDialog.cancel();
    }

    @Override
    public void showToast(String errorMeg) {
        Toast.makeText(mContext,errorMeg,Toast.LENGTH_SHORT).show();
        mProgressDialog.cancel();
    }

    @Override
    public void showWeather(int temperature, String weather) {
            mBinding.weatherImage.setText(weather);
            mBinding.weatherTemperature.setText(temperature+"");

    }

    @Override( )
    public void changeFenceStatus(Boolean isOn, boolean isGet) {
        if (isGet){
            //TODO:查询成功
        }else {
            if (isOn){
               showToast("小安宝开启成功！");
                mBinding.btnFencne.setText("开");
            }else {
                showToast("小安宝关闭成功！");
                mBinding.btnFencne.setText("关");
            }
        }

    }

    @Override
    public void changeBattery(int battery) {
        showToast("电量查询成功！");
        mBinding.btnBattery.setText(battery+"");
    }

    @Override
    public void changeItinerary(int itinerary) {
        showToast("里程查询成功！");
        mBinding.btnItinerary.setText(itinerary+"");
    }

    @Override
    public void changeGPSPoint(LatLng point) {
        if (null == mBaiduMap){
            mBaiduMap = mBinding.mapview.getMap();
        }
        if (null == mMarker){
            initMarker();
        }

        mMarker.setPosition(point);
        moveMapToCenter(point);
    }

    @Override
    public void changePlaceInfo(String placeInfo) {
        mBinding.textView.setText(placeInfo);
    }
}
