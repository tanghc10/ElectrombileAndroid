package com.xiaoantech.electrombile.ui.main.Unkown.Record;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoantech.electrombile.constant.HandlerConstant;
import com.xiaoantech.electrombile.constant.HttpConstant;
import com.xiaoantech.electrombile.event.http.HttpPostEvent;
import com.xiaoantech.electrombile.event.notify.RecordEvent;
import com.xiaoantech.electrombile.manager.BasicDataManager;
import com.xiaoantech.electrombile.manager.HttpManager;
import com.xiaoantech.electrombile.manager.LocalDataManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yangxu on 2017/3/2.
 */

public class RecordPresenter implements RecordContract.Presenter{
    private final static String TAG = "RecordPresenter";
    private RecordContract.View mRecordView;
    private RecordStatus        recordStatus = RecordStatus.RecordStatus_Start;
    private Timer               timer;
    private int                 secondLeft;

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == HandlerConstant.TimerWhat0){
                secondLeft --;
                if (secondLeft <= 0){
                    if (timer != null){
                        timer.cancel();
                    }
                    mRecordView.stopRecord();
                }else {
                    mRecordView.changeCutDownText(secondLeft + "");
                }
            }
            return false;
        }
    });

    enum RecordStatus{
        RecordStatus_Start,
        RecordStatus_Record,
        RecordStatus_Play,
        RecordStatus_Pause,
        RecordStatus_End
    }

    public RecordPresenter(RecordContract.View recordView){
        this.mRecordView = recordView;
        recordView.setPresenter(this);
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
    public void onPlay() {
        if (recordStatus == RecordStatus.RecordStatus_Start){
            mRecordView.showWaitingDialog("开启录音中");
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+ LocalDataManager.getInstance().getHTTPPort() + "/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_DEVICE, HttpConstant.HttpCmd.HTTP_CMD_START_RECORD,getPostBody(8));
        }
    }

    @Override
    public void onStop() {
        if (recordStatus == RecordStatus.RecordStatus_Record){
            mRecordView.showWaitingDialog("停止录音中");
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_DEVICE, HttpConstant.HttpCmd.HTTP_CMD_STOP_RECORD,getPostBody(9));

        }
    }

    public String getPostBody(int code){
        try {
            JSONObject cmd = new JSONObject();
            cmd.put("c",code);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", BasicDataManager.getInstance().getBindIMEI());
            jsonObject.put("cmd",cmd);
            return jsonObject.toString();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return "";
    }

    public void dealWithErrorCode(int code){
        String errStr = "";
        switch (code) {
            case 100:
                errStr = "服务器内部错误";
                break;
            case 101:
                errStr = "请求设备号错误";
                break;
            case 102:
                errStr = "无请求内容";
                break;
            case 103:
                errStr = "请求内容错误";
                break;
            case 104:
                errStr = "请求URL错误";
                break;
            case 105:
                errStr = "请求范围过大";
                break;
            case 106:
                errStr = "服务器无响应";
                break;
            case 107:
                errStr = "服务器不在线";
                break;
            case 108:
                errStr = "设备无响应";
                break;
            case 109:
                errStr = "设备不在线";
                break;
            case 110:
                errStr = "设备不在线";
                break;
            default:
                break;
        }
        mRecordView.showToast(errStr);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHttpPostEvent(HttpPostEvent event){
        if (event.getRequestType() == HttpManager.postType.POST_TYPE_DEVICE && event.getCmdType() == HttpConstant.HttpCmd.HTTP_CMD_START_RECORD){
            try {
                JSONObject jsonObject = new JSONObject(event.getResult());
                if (jsonObject.has("code")) {
                    int code = jsonObject.getInt("code");
                    if (code == 0){
                        recordStatus = RecordStatus.RecordStatus_Record;
                        mRecordView.startRecord();
                        secondLeft = 60;
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                mHandler.sendEmptyMessage(HandlerConstant.TimerWhat0);
                            }
                        }, 1000, 1000);
                    }else {
                        dealWithErrorCode(code);
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecordEvent(RecordEvent event){
        JSONObject jsonObject = event.getJsonObject();
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data.has("fileName")){
                String file = data.getString("fileName");
                String fileName = file.split("\\.")[0];

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void downloadFile(String fileName){
//        String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/record?name=" +fileName;
//        HttpHandler<File> httpHandler = new HttpUtils().download(HttpRequest.HttpMethod.GET,url, APK_dir + fileName, null, new RequestCallBack<File>() {
//            @Override
//            public void onSuccess(ResponseInfo<File> responseInfo) {
//                Message message = new Message();
//                Bundle bundle = new Bundle();
//                bundle.putString("filePath",APK_dir+fileName);
//                message.setData(bundle);
//                message.what = 11;
//                mHander.sendMessage(message);
//            }
//            @Override
//            public void onFailure(HttpException error, String msg) {
//                mHander.sendEmptyMessage(110);
//                Log.d("Failure",msg);
//            }
//        });
    }
}
