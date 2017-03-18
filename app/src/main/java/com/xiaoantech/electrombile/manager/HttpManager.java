package com.xiaoantech.electrombile.manager;

import android.os.Bundle;

import com.xiaoantech.electrombile.constant.HttpConstant;
import com.xiaoantech.electrombile.event.http.HttpGetEvent;
import com.xiaoantech.electrombile.event.http.HttpPostEvent;
import com.xiaoantech.electrombile.event.http.HttpPutEvent;
import com.xiaoantech.electrombile.event.http.HttpDeleteEvent;
import com.xiaoantech.electrombile.utils.StreamToStringUtil;
import com.xiaoantech.electrombile.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yangxu on 2016/11/7.
 */

public class HttpManager {
    public enum getType{
        GET_TYPE_WEATHER,
        GET_TYPE_TODAYITINERARY,
        GET_TYPE_ROUTES,
        GET_TYPE_GPS_POINTS,
        GET_TYPE_ALARMPHONE
    }
    public enum postType{
        POST_TYPE_WEATHER,
        POST_TYPE_TODAYITINERARY,
        POST_TYPE_ROUTES,
        POST_TYPE_GPS_POINTS,
        POST_TYPE_ALARMPHONE,
        POST_TYPE_DEVICE
    }
    public enum putType{
        PUT_TYPE_WEATHER,
        PUT_TYPE_TODAYITINERARY,
        PUT_TYPE_ROUTES,
        PUT_TYPE_GPS_POINTS,
        PUT_TYPE_ALARMPHONE
    }
    public enum deleteType{
        DELETE_TYPE_WEATHER,
        DELETE_TYPE_TODAYITINERARY,
        DELETE_TYPE_ROUTES,
        DELETE_TYPE_GPS_POINTS,
        DELETE_TYPE_ALARMPHONE
    }
    public static void getHttpResult(final String url, final getType gettype){
        new Thread(new Runnable(){
            @Override
            public void run() {
                HttpURLConnection connection;
                try {
                    URL getURL = new URL(url);
                    connection = (HttpURLConnection) getURL.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    String result = StreamToStringUtil.StreamToString(connection.getInputStream());
                    EventBus.getDefault().post(new HttpGetEvent(gettype,StringUtil.decodeUnicode(result),true));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void putHttpResult(final String url, final putType puttype, final String body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection;
                byte[] bytes = body.getBytes();
                try {
                    URL getURL = new URL(url);
                    connection = (HttpURLConnection) getURL.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("PUT");
                    connection.setConnectTimeout(5000);
                    connection.setUseCaches(false);        //设置不用缓存
                    connection.setInstanceFollowRedirects(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setRequestProperty("Content-Length", String.valueOf(bytes));
                    connection.connect();
                    OutputStream out = connection.getOutputStream();
                    out.write(bytes);
                    out.flush();
                    out.close();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String lines;
                    StringBuffer sb = new StringBuffer("");
                    while ((lines = reader.readLine()) != null){
                        lines = new String(lines.getBytes(), "utf-8");
                        sb.append(lines);
                    }
                    if (connection.getResponseCode() == 200){
                        String result = StreamToStringUtil.StreamToString(connection.getInputStream());
                        EventBus.getDefault().post(new HttpPutEvent(puttype,StringUtil.decodeUnicode(result.toString()),true));
                    }else {
                        connection.disconnect();
                    }
                    /*OutputStream out = connection.getOutputStream();
                    out.write(bytes);
                    out.flush();
                    out.close();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String lines;
                    StringBuffer sb = new StringBuffer("");
                    while ((lines = reader.readLine()) != null){
                        lines = new String(lines.getBytes(), "utf-8");
                        sb.append(lines);
                    }*/
                    /*
                    if (connection.getResponseCode() == 200){
                        String result = StreamToStringUtil.StreamToString(connection.getInputStream());
                        EventBus.getDefault().post(new HttpPutEvent(puttype,StringUtil.decodeUnicode(result),true));
                    }

                    System.out.println(sb);
                    reader.close();
                    connection.disconnect();*/
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void deleteHttpResult(final String url, final deleteType deletetype, final String body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection;
                byte[] bytes = body.getBytes();
                try {
                    URL getURL = new URL(url);
                    connection = (HttpURLConnection) getURL.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("DELETE");
                    connection.setConnectTimeout(5000);
                    connection.setUseCaches(false);        //设置不进行缓存
                    connection.setInstanceFollowRedirects(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setRequestProperty("Content-Length", String.valueOf(bytes.length));

                    int response = connection.getResponseCode();
                    if (response == HttpURLConnection.HTTP_OK){
                        String result = StreamToStringUtil.StreamToString(connection.getInputStream());
                        EventBus.getDefault().post(new HttpDeleteEvent(deletetype,StringUtil.decodeUnicode(result),true));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void postHttpResult(final String url, final postType postType , final HttpConstant.HttpCmd cmd, final String body){
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = body.getBytes();
                HttpURLConnection connection;
                try{
                    URL postURL = new URL(url);
                    connection = (HttpURLConnection) postURL.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setRequestProperty("Content-Type","application/json");
                    connection.setRequestProperty("Content-Length",String.valueOf(bytes.length));
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(bytes);

                    int response = connection.getResponseCode();
                    if (response == HttpURLConnection.HTTP_OK){
                        String result = StreamToStringUtil.StreamToString(connection.getInputStream());
                        EventBus.getDefault().post(new HttpPostEvent(postType,StringUtil.decodeUnicode(result),true,cmd));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
