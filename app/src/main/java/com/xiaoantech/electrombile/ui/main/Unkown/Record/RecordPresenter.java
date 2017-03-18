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
import com.xiaoantech.electrombile.utils.APKDirUtil;

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

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case HandlerConstant.TimerWhat0:
                    secondLeft --;
                    if (secondLeft <= 0){
                        if (timer != null){
                            timer.cancel();
                        }
                        mRecordView.stopRecord();
                    }else {
                        mRecordView.changeCutDownText(secondLeft + "");
                    }
                    break;
                case HandlerConstant.FileDownLoadSuccess:
                    Bundle bundle = msg.getData();
                    String filePath = bundle.getString("filePath");
                    mRecordView.startPlay(filePath);
                    recordStatus = RecordStatus.RecordStatus_Play;
                    break;
                case HandlerConstant.FileDownLoadFail:
                    mRecordView.showToast("下载失败");
                    break;
            }
            return false;
        }
    });

    private enum RecordStatus{
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
        }else if (recordStatus == RecordStatus.RecordStatus_Play){
            mRecordView.changePlayStatus();
        }
    }

    @Override
    public void onStop() {
        if (recordStatus == RecordStatus.RecordStatus_Record){
            mRecordView.showWaitingDialog("停止录音中");
            String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/device";
            HttpManager.postHttpResult(url, HttpManager.postType.POST_TYPE_DEVICE, HttpConstant.HttpCmd.HTTP_CMD_STOP_RECORD,getPostBody(9));
        }else if (recordStatus == RecordStatus.RecordStatus_Play){
            recordStatus = RecordStatus.RecordStatus_Start;
            mRecordView.resetView();
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

        if (event.getRequestType() == HttpManager.postType.POST_TYPE_DEVICE){
            try {
                Log.w(TAG,event.getResult());
                JSONObject jsonObject = new JSONObject(event.getResult());
                if (jsonObject.has("code")) {
                    int code = jsonObject.getInt("code");
                    if (code == 0){
                        if (event.getCmdType() == HttpConstant.HttpCmd.HTTP_CMD_START_RECORD) {

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
                        }else if (event.getCmdType() == HttpConstant.HttpCmd.HTTP_CMD_STOP_RECORD) {
                            timer.cancel();
                            mRecordView.stopRecord();
                        }


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
            if (data.has("fileName") && !((String)data.get("fileName")).isEmpty()){
                String file = data.getString("fileName");
                String fileName = file.split("\\.")[0];
                downloadFile(fileName);
            }else {
                mRecordView.showToast("文件下载错误");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void downloadFile(String fileName){
        String url = LocalDataManager.getInstance().getHTTPHost()+":"+LocalDataManager.getInstance().getHTTPPort()+"/v1/record?name=" +fileName;
        final String apk_path = APKDirUtil.initAPKDir() + fileName;
        HttpHandler<File> httpHandler = new HttpUtils().download(HttpRequest.HttpMethod.GET,url, apk_path, null, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("filePath",apk_path);
                message.setData(bundle);
                message.what = HandlerConstant.FileDownLoadSuccess;
                mHandler.sendMessage(message);
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                mHandler.sendEmptyMessage(HandlerConstant.FileDownLoadFail);
                Log.d("Failure",msg);
            }
        });
    }
}
