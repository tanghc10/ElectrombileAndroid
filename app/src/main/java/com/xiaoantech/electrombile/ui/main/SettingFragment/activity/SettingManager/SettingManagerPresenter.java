package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager;

import android.net.Uri;
import android.widget.TextView;

import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.manager.HttpManager;
import com.xiaoantech.electrombile.manager.LocalDataManager;
import com.xiaoantech.electrombile.event.http.HttpGetEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangxu on 2016/12/14.
 */

public class SettingManagerPresenter implements SettingManagerContract.Presenter{
    private  SettingManagerContract.View mSettingManagerView;

    protected SettingManagerPresenter(SettingManagerContract.View settingManagerView){
        this.mSettingManagerView = settingManagerView;
        mSettingManagerView.setPresenter(this);
    }

    public void subscribe(){
        EventBus.getDefault().register(this);
    }

    public void unsubscribe() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void gotoAutoLock() {
        mSettingManagerView.gotoAutoLock();
    }

    @Override
    public void gotoChangePass() {
        mSettingManagerView.gotoChangePass();
    }

    @Override
    public void gotoPhoneAlarm() {
        mSettingManagerView.gotoPhoneAlarm();
    }

    @Override
    public void gotoRecord() {
        mSettingManagerView.gotoRecord();
    }

    @Override
    public void logout() {
        mSettingManagerView.logout();
    }

    public void isPhoneAlarmOpen(){
        String baseUrl = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort();
        String url = baseUrl + "/v1/telephone/"+ BasicDataManager.getInstance().getBindIMEI();
        HttpManager.getHttpResult(url, HttpManager.getType.GET_TYPE_PHONENUM);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHttpGetEvent(HttpGetEvent event){

        if (event.getResult().indexOf("code") != -1) {
            mSettingManagerView.PhoneAlarmOpen(false);
        }else{
            mSettingManagerView.PhoneAlarmOpen(true);
        }
    }
}
