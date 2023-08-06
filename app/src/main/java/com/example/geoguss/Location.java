package com.example.geoguss;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
