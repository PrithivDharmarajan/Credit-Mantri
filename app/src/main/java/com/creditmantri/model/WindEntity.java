package com.creditmantri.model;

import java.io.Serializable;

public class WindEntity implements Serializable {

    private double speed=0;
    private double deg=0;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

}
