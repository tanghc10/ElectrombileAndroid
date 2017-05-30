package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.CarManager.CarInfoRevise;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.databinding.ActivityCarinfoReviseBinding;
import com.xiaoantech.electrombile.http.HttpPublishManager;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.manager.LocalDataManager;
import com.xiaoantech.electrombile.model.CarInfoModel;
import com.xiaoantech.electrombile.ui.AddDevice.InputIMEI.InputIMEIActivity;
import com.xiaoantech.electrombile.widget.Dialog.CertainDialog;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yangxu on 2017/1/3.
 */

public class CarInfoReviseActivity extends BaseAcitivity implements CarInfoReviseContract.View{
    private ActivityCarinfoReviseBinding mBinding;
    private CarInfoReviseContract.Presenter mPresenter;
    private CarInfoModel mCarInfoModel;
    private int carIndex;
    private List<Map<String,Object>> batteryTypeList;
    private static View         selectedView;
    private static int          selectedIndex;


    @Override
    protected void initBefore() {
        Intent intent = getIntent();
        carIndex = intent.getIntExtra("index",0);
        mCarInfoModel = BasicDataManager.getInstance().getCarInfoList().get(carIndex);
    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_carinfo_revise);
    }

    @Override
    protected void initView() {
        mPresenter = new CarInfoRevisePresenter(this);
        mBinding.setPresenter(mPresenter);
        mBinding.setCarInfo(mCarInfoModel);
        ((TextView)mBinding.navigation.findViewById(R.id.navigation_title)).setText("车辆信息修改");
        ((RelativeLayout)mBinding.navigation.findViewById(R.id.navigation_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarInfoReviseActivity.this.finish();
            }
        });
        setCarInform();
    }

    private void setCarInform(){
        mBinding.txtCarName.setText(mCarInfoModel.getName());
        mBinding.txtPlateNum.setText(mCarInfoModel.getPlateNum());
        mBinding.txtFrameNum.setText(mCarInfoModel.getFrameNum());
        mBinding.txtBatteryVersion.setText(mCarInfoModel.getBatteryType());
        mBinding.txtBrandName.setText(mCarInfoModel.getBrandName());
        mBinding.txtVenderPhone.setText(mCarInfoModel.getVenderPhone());
    }

    @Override
    public void setPresenter(CarInfoReviseContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void chooseBatteryType() {
        CertainDialog.Builder builder = new CertainDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.content_choose_battery,null);
        final ListView listView = (ListView)view.findViewById(R.id.listView_change_battery);
        batteryTypeList = mPresenter.getBatteryList();
        SimpleAdapter adapter = new SimpleAdapter(this,batteryTypeList,R.layout.cell_battery_type,new String[]{"battery","isSelected"},new int[]{R.id.txt_battery_type,R.id.btn_selected});
        listView.setAdapter(adapter);
        selectedIndex = 0;

        selectedView = null;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedView == null){
                    selectedView = parent.getChildAt(0);
                }
                batteryTypeList.get(selectedIndex).remove("isSelected");
                batteryTypeList.get(selectedIndex).put("isSelected",R.drawable.btn_unselected);
                ImageView preImageView = (ImageView)selectedView.findViewById(R.id.btn_selected);
                Drawable drawable = getResources().getDrawable(R.drawable.btn_unselected);
                preImageView.setImageDrawable(drawable);

                selectedIndex = position;
                selectedView = view;
                batteryTypeList.get(selectedIndex).remove("isSelected");
                batteryTypeList.get(selectedIndex).put("isSelected",R.drawable.btn_selected);
                ImageView curImageView = (ImageView)view.findViewById(R.id.btn_selected);
                curImageView.setImageDrawable(getResources().getDrawable(R.drawable.btn_selected));
            }
        });

        builder.setTitle("设置电池类型").setContentView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.changeBatteryType((selectedIndex+1)*12);
                mBinding.txtBatteryVersion.setText((selectedIndex+1)*12 + "");
                dialog.dismiss();
            }
        }).create().show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onStop() {
        mPresenter.unsubscribe();
        super.onStop();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }
}

