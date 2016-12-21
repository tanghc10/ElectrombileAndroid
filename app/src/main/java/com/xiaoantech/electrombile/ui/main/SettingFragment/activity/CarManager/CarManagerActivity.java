package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.CarManager;

import android.databinding.DataBindingUtil;
import android.widget.SimpleAdapter;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityCarmanagerBinding;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.model.CarInfoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxu on 2016/12/14.
 */

public class CarManagerActivity extends BaseAcitivity implements  CarManagerContract.View{
    private ActivityCarmanagerBinding mBinding;
    private CarManagerContract.Presenter mPresenter;

    private List<Map<String,Object>> otherCarList;

    private static final String CarImage = "carImage";
    private static final String CarName = "carName";

    private SimpleAdapter mAdapter;

    @Override
    protected void initBefore() {
        getOtherList();
    }

    private void getOtherList(){
        otherCarList = new ArrayList<>();
        HashMap<String,Object> map;
        List<CarInfoModel> carInfoModelList = BasicDataManager.getInstance().getCarInfoList();
        for (int i = 1; i < carInfoModelList.size(); i++){
            map = new HashMap<>();
            map.put(CarName,carInfoModelList.get(i).getName());
            map.put(CarImage, R.drawable.othercar);
            otherCarList.add(map);
        }
    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_carmanager);
    }

    @Override
    protected void initView() {
        mPresenter = new CarManagerPresenter(this);
        mBinding.setPresenter(mPresenter);

        //已绑定设备显示
        mBinding.imgBindCar.setImageResource(R.drawable.othercar);
        mBinding.txtBindCarName.setText(BasicDataManager.getInstance().getBindCarInfo().getName());

        //非绑定列表设备显示
        mAdapter = new SimpleAdapter(this,otherCarList,R.layout.item_othercar_list,new String[]{CarImage,CarName},new int[]{R.id.img_car,R.id.txt_car_name});
        mBinding.listView.setAdapter(mAdapter);
    }

    @Override
    public void setPresenter(CarManagerContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void addDevice() {
//        new CommonDialog(this,"")
    }
}
