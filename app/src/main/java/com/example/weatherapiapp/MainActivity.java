package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button btn_getCityID;
    private Button btn_searchByID;
    private Button btn_searchByName;
    private EditText et_source;
    private TextView tv_mintemp;
    private TextView tv_maxtemp;
    private String cityID;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_getCityID = findViewById(R.id.getCityID);
        btn_searchByID = findViewById(R.id.searchByID);
        btn_searchByName = findViewById(R.id.searchByName);
        et_source = findViewById(R.id.source);
        tv_mintemp = findViewById(R.id.mintemp);
        tv_maxtemp = findViewById(R.id.maxtemp);


        btn_getCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = et_source.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="https://www.metaweather.com/api/location/search/?query="+cityName;

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String cityID = "";
                        //parsing JSON
                        try {
                            JSONObject cityinfo = response.getJSONObject(0);
                            cityID = cityinfo.getString("woeid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(MainActivity.this,"City ID = "+cityID,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Didn't Work",Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request);
            }
        });

        btn_searchByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://www.metaweather.com/api/location/"+cityID+"/";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String id = "";
                        String minTemp = "";
                        String maxTemp = "";
                        String currentTemp = "";
                        String windSpeed = "";
                        String airPressure = "";
                        String humidity = "";
                        try {
                            JSONArray cityInfo = response.getJSONArray("consolidated_weather");
                            JSONObject info = cityInfo.getJSONObject(0);
                            minTemp = info.getString("min_temp");
                            maxTemp = info.getString("max_temp");
                            currentTemp = info.getString("the_temp");
                            windSpeed = info.getString("wind_speed");
                            airPressure = info.getString("air_pressure");
                            humidity = info.getString("humidity");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //launching new activity
                        Intent i = new Intent(MainActivity.this,InfoPage.class);
                        i.putExtra("MinTemp",minTemp);
                        i.putExtra("MaxTemp",maxTemp);
                        i.putExtra("CurrentTemp",currentTemp);
                        i.putExtra("WindSpeed",windSpeed);
                        i.putExtra("AirPressure",airPressure);
                        i.putExtra("Humidity",humidity);
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Didn't Work",Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request);

            }
        });

        btn_searchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = et_source.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="https://www.metaweather.com/api/location/search/?query="+cityName;

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        cityID = "";
                        //parsing JSON
                        try {
                            JSONObject cityinfo = response.getJSONObject(0);
                            cityID = cityinfo.getString("woeid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(MainActivity.this,"City ID = "+cityID,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this,"Didn't Work",Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request);

                RequestQueue queue2 = Volley.newRequestQueue(MainActivity.this);
                String url2 = "https://www.metaweather.com/api/location/"+cityID+"/";

                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url2,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String id = "";
                        String minTemp = "";
                        String maxTemp = "";
                        String currentTemp = "";
                        String windSpeed = "";
                        String airPressure = "";
                        String humidity = "";
                        try {
                            JSONArray cityInfo = response.getJSONArray("consolidated_weather");
                            JSONObject info = cityInfo.getJSONObject(0);
                            minTemp = info.getString("min_temp");
                            maxTemp = info.getString("max_temp");
                            currentTemp = info.getString("the_temp");
                            windSpeed = info.getString("wind_speed");
                            airPressure = info.getString("air_pressure");
                            humidity = info.getString("humidity");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //launching new activity
                        Intent i = new Intent(MainActivity.this,InfoPage.class);
                        Log.d("check","done1");
                        i.putExtra("MinTemp",minTemp);
                        i.putExtra("MaxTemp",maxTemp);
                        i.putExtra("CurrentTemp",currentTemp);
                        i.putExtra("WindSpeed",windSpeed);
                        i.putExtra("AirPressure",airPressure);
                        i.putExtra("Humidity",humidity);
                        Log.d("check","done2");
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Didn't Work2",Toast.LENGTH_LONG).show();
                    }
                });
                queue2.add(request2);


            }
        });
    }

}