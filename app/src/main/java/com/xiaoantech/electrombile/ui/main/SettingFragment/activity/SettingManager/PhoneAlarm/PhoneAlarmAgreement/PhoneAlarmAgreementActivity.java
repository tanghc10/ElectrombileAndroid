package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager.PhoneAlarm.PhoneAlarmAgreement;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.base.BaseAcitivity;
import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.databinding.ActivityPhoneAlarmAgreementBinding;
import com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager.PhoneAlarm.PhoneAlarmActivity;
import com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager.PhoneAlarm.PhoneAlarmAgreement.PhoneAlarmAgreementContract;
import com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager.PhoneAlarm.PhoneAlarmContract;
import com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager.PhoneAlarm.PhoneAlarmPresenter;

/**
 * Created by 73843 on 2017/3/18.
 */

public class PhoneAlarmAgreementActivity extends BaseAcitivity implements PhoneAlarmAgreementContract.View {
    private ActivityPhoneAlarmAgreementBinding mBinding;
    private PhoneAlarmAgreementContract.Presenter mPresenter;

    @Override
    protected void initBefore() {

    }

    @Override
    protected void bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_phone_alarm_agreement);
    }

    @Override
    protected void initView() {
        mPresenter = new PhoneAlarmAgreementPresenter(this);
        mBinding.setPresenter(mPresenter);
        ((TextView)mBinding.navigation.findViewById(R.id.navigation_title)).setText("电话报警通知协议");
        ((RelativeLayout)mBinding.navigation.findViewById(R.id.navigation_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAlarmAgreementActivity.this.finish();
            }
        });
        setBtnAgree();
    }

    @Override
    public void setPresenter(PhoneAlarmAgreementContract.Presenter Presenter) {
        this.mPresenter = Presenter;
    }

    public void setBtnAgree(){
        mBinding.btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setPhoneAlarmPhone();
            }
        });
    }

    public void toPhoneAlarmActivity(boolean isTo){
        if (true == isTo){
            Intent intent = new Intent();
            intent.setClass(PhoneAlarmAgreementActivity.this, PhoneAlarmActivity.class);
            startActivity(intent);
        }else {
            showToast("电话报警设置失败");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }
}
