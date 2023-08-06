package com.example.geoguss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

import com.squareup.picasso.Picasso;
import java.io.IOException;
import okhttp3.Response;
import java.util.Locale;



public class MapActivity extends AppCompatActivity {

    private ImageView streetViewImageView;
    private Button selectLocationButton;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        streetViewImageView = findViewById(R.id.streetViewImageView);
        selectLocationButton = findViewById(R.id.selectLocationButton);

        // Receive the random latitude and longitude from the HomeActivity
        latitude = getIntent().getDoubleExtra("LATITUDE", 0);
        longitude = getIntent().getDoubleExtra("LONGITUDE", 0);


        selectLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MapActivity to allow the user to select a location on the map
                Intent intent = new Intent(MapActivity.this, SelectLocationMapActivity.class);
                intent.putExtra("ACTUAL_LATITUDE", latitude);
                intent.putExtra("ACTUAL_LONGITUDE", longitude);
                startActivity(intent);
            }
        });
    }
    public void generateStreet(double lat , double lon){
        bingMapsApi = new BingMapsApi();
        bingMapsApi.getStaticMapImage(latitude, longitude, 17, new BingMapsApi.BingMapsCallback() {
            @Override
            public void onResponse(Response response) throws IOException {
                runOnUiThread(() -> {
                    Picasso.get().load(response.request().url().toString()).into(streetViewImageView);
                });
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

}