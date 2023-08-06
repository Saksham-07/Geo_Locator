package com.example.geoguss;

import com.google.gson.annotations.SerializedName;

public class StreetViewResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("pano_id")
    private String panoramaId;

    public String getStatus() {
        return status;
    }

    public String getPanoramaId() {
        return panoramaId;
    }
}

