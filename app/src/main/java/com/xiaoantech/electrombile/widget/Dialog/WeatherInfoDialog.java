package com.xiaoantech.electrombile.widget.Dialog;

import android.app.Dialog;
import android.app.WallpaperInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoantech.electrombile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangxu on 2017/2/5.
 */

public class WeatherInfoDialog extends Dialog {
    public WeatherInfoDialog(Context context) {
        super(context);
    }

    public WeatherInfoDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {

        private SimpleDialog simpleDialog;
        private Context context;
        private JSONObject weatherInfo;
        private String  location;

        public Builder(Context context) {
            this.context = context;
        }

        public WeatherInfoDialog.Builder setMessage(String location) {
            this.location = location;
            return this;
        }

        public WeatherInfoDialog.Builder setWeatherInfo(JSONObject weatherInfo){
            this.weatherInfo = weatherInfo;
            return this;
        }


        public SimpleDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            simpleDialog = new SimpleDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.content_weather, null);
            simpleDialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            // set the dialog title
            if (location != null){
                ((TextView)layout.findViewById(R.id.txt_location)).setText(location);
            }

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd,HH:mm");
            String dateToday = dateFormat.format(date);
            ((TextView)layout.findViewById(R.id.txt_date_today)).setText(dateToday);

            if (weatherInfo != null){
                try {
                    JSONObject data = weatherInfo.getJSONObject("data");
                    JSONArray forecast = data.getJSONArray("forecast");

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }



            simpleDialog.setContentView(layout);
            return simpleDialog;
        }

        private void setImageView(ImageView imageView,String weather){
            if (weather.contains("晴")){

            }else if (weather.contains("云")){
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.img_weather_cloudy));
            }
        }


        public SimpleDialog getSimpleDialog(){
            return simpleDialog;
        }
    }
}
