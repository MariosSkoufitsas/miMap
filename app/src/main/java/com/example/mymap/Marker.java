package com.example.mymap;
public class Marker {
    private  double lat;
    private double lot;
    private String name;
    private String color;
    private String sensor;
    public Marker(double lat, double lot, String name, String color, String sensor) {
        this.lat = lat;
        this.lot = lot;
        this.name = name;
        this.color = color;
        this.sensor = sensor;
    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLot() {
        return lot;
    }

    public void setLot(double lot) {
        this.lot = lot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }
}
