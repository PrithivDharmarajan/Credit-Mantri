package com.creditmantri.model;

import java.io.Serializable;

public class CoordinatorEntity implements Serializable {
    private double lat = 0.0;
    private double lon = 0.0;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

}
