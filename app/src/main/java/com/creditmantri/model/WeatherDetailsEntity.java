package com.creditmantri.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 1/24/2018.
 */

public class WeatherDetailsEntity implements Serializable {

    private long dt=0;
    private MainDetailsEntity main=new MainDetailsEntity();
    private ArrayList<WeatherEntity>weather=new ArrayList<>();
    private CloudsEntity clouds=new CloudsEntity();
    private WindEntity wind=new WindEntity();
    private String dt_txt="";

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public MainDetailsEntity getMain() {
        return main;
    }

    public void setMain(MainDetailsEntity main) {
        this.main = main;
    }

    public ArrayList<WeatherEntity> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherEntity> weather) {
        this.weather = weather;
    }

    public CloudsEntity getClouds() {
        return clouds;
    }

    public void setClouds(CloudsEntity clouds) {
        this.clouds = clouds;
    }

    public WindEntity getWind() {
        return wind;
    }

    public void setWind(WindEntity wind) {
        this.wind = wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }


}
