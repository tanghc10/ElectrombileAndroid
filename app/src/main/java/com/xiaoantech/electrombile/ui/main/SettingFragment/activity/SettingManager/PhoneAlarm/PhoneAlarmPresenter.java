package com.xiaoantech.electrombile.ui.main.SettingFragment.activity.SettingManager.PhoneAlarm;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoantech.electrombile.event.http.HttpPutEvent;
import com.xiaoantech.electrombile.http.HttpPublishManager;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.http.HttpManager;
import com.xiaoantech.electrombile.manager.LocalDataManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;

/**
 * Created by yangxu on 2017/2/25.
 */

public class PhoneAlarmPresenter implements PhoneAlarmContract.Presenter{
    private final static String TAG = "PhoneAlarmPresenter";
    private PhoneAlarmContract.View mPhoneAlarm;
    private ProgressDialog waitDialog;
    private int secondleft;
    private Button btn_alarmTest;
    private Timer timer;
    private TextView textView_time;

    /*Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                secondleft--;
                if (secondleft <= 0) {
                    timer.cancel();
                    changeButtonState(true);
                    textView_time.setText("60");
                } else {
                    textView_time.setText(secondleft + "");
                }
            } else if (msg.what == 1) {
                waitDialog.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }, 1000, 1000);
                changeButtonState(false);
            } else if (msg.what == 2) {
                waitDialog.cancel();
            }
        }
    };*/

    public void AddCallerIndex(){
        int caller = LocalDataManager.getInstance().getCallerIndex() + 1;
        if (caller >= 10){
            caller = 0;
        }
        LocalDataManager.getInstance().setCallerIndex(caller);
        putAlarmPhoneFormHttp();
    }


    protected PhoneAlarmPresenter(PhoneAlarmContract.View phoneAlarm){
        this.mPhoneAlarm = phoneAlarm;
        mPhoneAlarm.setPresenter(this);
    }

    public void subscribe(){
        EventBus.getDefault().register(this);
    }

    public void unsubscribe() {
        EventBus.getDefault().unregister(this);
    }

    public void putAlarmPhoneFormHttp(){
        String baseUrl = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort();
        String url = baseUrl + "/v1/telephone/"+BasicDataManager.getInstance().getBindIMEI();
        try{
            JSONObject caller = new JSONObject();
            caller.put("caller",LocalDataManager.getInstance().getCallerIndex());
            mPhoneAlarm.showWaitingDialog("正在设置");
            HttpManager.putHttpResult(url, HttpManager.putType.PUT_TYPE_ALARMPHONE, caller.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHttpPutEvent(HttpPutEvent event){
        mPhoneAlarm.showToast("开始测试");
    }

    public void HttpTest(){
        HttpPublishManager.getmInstance().getLockOn();
    }

}
