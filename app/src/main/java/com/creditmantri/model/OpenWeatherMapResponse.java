package com.creditmantri.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 1/24/2018.
 */

public class OpenWeatherMapResponse implements Serializable {

    private String cod="";
    private double message=0;
    private int cnt=0;
    private ArrayList<WeatherDetailsEntity> list=new ArrayList<>();
    private CityEntity city=new CityEntity();

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public ArrayList<WeatherDetailsEntity> getList() {
        return list;
    }

    public void setList(ArrayList<WeatherDetailsEntity> list) {
        this.list = list;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }


}
