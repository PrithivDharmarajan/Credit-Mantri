package com.creditmantri.model;

import java.io.Serializable;

public class CityEntity implements Serializable {

    private int id=0;
    private String name="";
    private CoordinatorEntity coord=new CoordinatorEntity();
    private String country="";
    private int population=0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordinatorEntity getCoord() {
        return coord;
    }

    public void setCoord(CoordinatorEntity coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}
