package com.xiaoantech.electrombile.ui.main.MainFragment.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xiaoantech.electrombile.event.http.HttpEvent;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.manager.HttpManager;
import com.xiaoantech.electrombile.mqtt.MqttManager;
import com.xiaoantech.electrombile.mqtt.MqttPublishManager;
import com.xiaoantech.electrombile.utils.JSONUtil;
import com.xiaoantech.electrombile.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangxu on 2016/11/6.
 */

public class MainFragmentPresenter implements MainFragmentConstract.Presenter{
    private final static String TAG = "MainFragmentPresenter";
    private MainFragmentConstract.View  mMainFragmentView;
    private boolean fenceStatus;
    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private final int weatherMessage = ;

    protected MainFragmentPresenter(MainFragmentConstract.View mainFragmentView){
        subscribe();
        this.mMainFragmentView = mainFragmentView;
        mainFragmentView.setPresenter(this);
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
    public void getWeatherInfo() {
        String city = StringUtil.encode("武汉市");
        String urlStr = "http://wthrcdn.etouch.cn/weather_mini?city=%E6%AD%A6%E6%B1%89";
        HttpManager.getHttpResult(urlStr, HttpManager.getType.GET_TYPE_WEATHER);
    }

    @Override
    public void changeFenceStatus() {
        if (fenceStatus){
            MqttPublishManager.getInstance().fenceOff(BasicDataManager.getInstance().getIMEI());
        }else {
            MqttPublishManager.getInstance().fenceOn(BasicDataManager.getInstance().getIMEI());
        }
    }



    @Subscribe
    public void onHttpEvent(HttpEvent event){
        if (event.getRequestType() == HttpManager.getType.GET_TYPE_WEATHER){
            didReceiveWeather(event.getResult());
        }
    }

    private void didReceiveWeather(String weatherStr){
        try{
            String desc = JSONUtil.ParseJSON(weatherStr,"desc");
            if (null != desc && desc.equals("OK")){
                String data = JSONUtil.ParseJSON(weatherStr,"data");
                String temperature = JSONUtil.ParseJSON(data,"wendu");
                String forecast = JSONUtil.ParseJSON(data,"forecast");
                JSONArray jsonArray = new JSONArray(forecast);
                String type = "";
                if (jsonArray.length()>0){
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    type = jsonObject.optString("type");

                }
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("temperature",Integer.parseInt(temperature));
                bundle.putString("weather",type);
                message.setData(bundle);
                message.what = 1;
                myHandler.sendMessage(message);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
