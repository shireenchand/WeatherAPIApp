package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class InfoPage extends AppCompatActivity {

    private TextView tv_mintemp;
    private TextView tv_maxtemp;
    private TextView tv_currenttemp;
    private TextView tv_windspeed;
    private TextView tv_airpressure;
    private TextView tv_humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        tv_mintemp = findViewById(R.id.mintemp);
        tv_maxtemp = findViewById(R.id.maxtemp);
        tv_currenttemp = findViewById(R.id.currenttemp);
        tv_windspeed = findViewById(R.id.windspeed);
        tv_airpressure = findViewById(R.id.airpressure);
        tv_humidity = findViewById(R.id.humidity);


        Intent i = getIntent();
        String s = i.getStringExtra("MinTemp");
        tv_mintemp.setText(s);
        s = i.getStringExtra("MaxTemp");
        tv_maxtemp.setText(s);
        s = i.getStringExtra("CurrentTemp");
        tv_currenttemp.setText(s);
        s = i.getStringExtra("WindSpeed");
        tv_windspeed.setText(s);
        s = i.getStringExtra("AirPressure");
        tv_airpressure.setText(s);
        s = i.getStringExtra("Humidity");
        tv_humidity.setText(s);

    }
}