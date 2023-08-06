package com.example.geoguss;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {
    @SerializedName("name")
    private String name;


    public String getName() {
        return name;
    }

    public List<Location> getLocations() {
        return null;
    }

    // Add getters and setters for other fields
}

