package com.example.geoguss;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryService {
    @GET("v2/name/{country}")
    Call<Country[]> getCountryInfo(@Path("country") String country);
}
