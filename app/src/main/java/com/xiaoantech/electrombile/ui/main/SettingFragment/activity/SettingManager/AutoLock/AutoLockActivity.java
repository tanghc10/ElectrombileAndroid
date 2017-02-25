package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager.AutoLock;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.constant.EventBusConstant;
import com.xiaoantech.electrombile.databinding.ActivityAutolockBinding;
import com.xiaoantech.electrombile.event.cmd.AutoLockEvent;
import com.xiaoantech.electrombile.event.cmd.AutoPeriodEvent;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.manager.LocalDataManager;
import com.xiaoantech.electrombile.mqtt.MqttPublishManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

/**
 * Created by yangxu on 2017/2/23.
 */

public class AutoLockActivity extends BaseAcitivity implements AutoLockContract.View  {
    private ActivityAutolockBinding mBinding;
    private AutoLockContract.Presenter mPresenter;

    @Override
    protected void initBefore() {

    }

    @Override
    protected void bindData() {
        try {
            mBinding = DataBindingUtil.setContentView(this, R.layout.activity_autolock);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        mPresenter = new AutoLockPresenter(this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(AutoLockContract.Presenter Presenter) {
        this.mPresenter = Presenter;
    }

    @Override
    public void changeAutoLockState(boolean isOn) {
        if (isOn){
            mBinding.btnAutolock.setText("已开启");
        }else {
            mBinding.btnAutolock.setText("已关闭");
        }
        showToast("设置成功");
    }

    //    private ImageView imageView_5_minutes;
//    private ImageView imageView_10_minutes;
//    private ImageView imageView_15_minutes;
//    private Button           btn_autolock;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        try {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_autolock);
//
//            imageView_5_minutes = (ImageView) findViewById(R.id.img_5_minutes);
//            imageView_10_minutes = (ImageView) findViewById(R.id.img_10_minutes);
//            imageView_15_minutes = (ImageView) findViewById(R.id.img_10_minutes);
//            btn_autolock = (Button) findViewById(R.id.btn_autolock);
//
//            btn_autolock.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (!LocalDataManager.getInstance().getAutoLock()){
//                        MqttPublishManager.getInstance().autoLockOn(BasicDataManager.getInstance().getBindIMEI());
//                    }else {
//                        MqttPublishManager.getInstance().autoLockOff(BasicDataManager.getInstance().getBindIMEI());
//                    }
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        EventBus.getDefault().unregister(this);
//    }
//
//    public void constraintOnClick(View view){
//        switch (view.getId()){
//            case R.id.constraintLayout_5_minutes:
//                MqttPublishManager.getInstance().setAutoPeriod(BasicDataManager.getInstance().getBindIMEI(),5);
//                break;
//            case R.id.constraintLayout_10_minutes:
//                MqttPublishManager.getInstance().setAutoPeriod(BasicDataManager.getInstance().getBindIMEI(),10);
//                break;
//            case R.id.constraintLayout_15_minutes:
//                MqttPublishManager.getInstance().setAutoPeriod(BasicDataManager.getInstance().getBindIMEI(),15);
//                break;
//        }
//    }


}
