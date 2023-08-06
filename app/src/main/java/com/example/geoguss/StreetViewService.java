package com.example.geoguss;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StreetViewService {
    @GET("maps/api/streetview")
    Call<StreetViewResponse> getStreetViewImage(
            @Query("size") String size,
            @Query("location") String location,
            @Query("key") String apiKey
    );
}

