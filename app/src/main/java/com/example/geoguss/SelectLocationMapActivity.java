package com.example.geoguss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SelectLocationMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView submitButton;
    private double actualLatitude, actualLongitude;
    private double userSelectedLatitude, userSelectedLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location_map);

        submitButton = findViewById(R.id.submitButton);

        // Receive the actual latitude and longitude from the MapActivity
        actualLatitude = getIntent().getDoubleExtra("ACTUAL_LATITUDE", 0);
        actualLongitude = getIntent().getDoubleExtra("ACTUAL_LONGITUDE", 0);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userSelectedLatitude != 0 && userSelectedLongitude != 0) {
                    // Calculate the distance between the actual location and the user's selected location
                    double distance = DistanceCalculator.calculateDistance(actualLatitude, actualLongitude, userSelectedLatitude, userSelectedLongitude);

                    // Start the ResultActivity and pass the distance and other relevant information
                    Intent intent = new Intent(SelectLocationMapActivity.this, ResultActivity.class);
                    intent.putExtra("DISTANCE", distance);
                    intent.putExtra("ACTUAL_LATITUDE", actualLatitude);
                    intent.putExtra("ACTUAL_LONGITUDE", actualLongitude);
                    intent.putExtra("USER_SELECTED_LATITUDE", userSelectedLatitude);
                    intent.putExtra("USER_SELECTED_LONGITUDE", userSelectedLongitude);
                    startActivity(intent);
                } else {
                    Toast.makeText(SelectLocationMapActivity.this, "Please select a location on the map.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker for the actual location and move the camera
        LatLng actualLocation = new LatLng(actualLatitude, actualLongitude);
        mMap.addMarker(new MarkerOptions().position(actualLocation).title("Actual Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(actualLocation, 10));

        // Set a click listener for the map to allow the user to select a location
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Clear any previous markers
                mMap.clear();

                // Add a marker for the user's selected location
                mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Location"));

                // Update the userSelectedLatitude and userSelectedLongitude
                userSelectedLatitude = latLng.latitude;
                userSelectedLongitude = latLng.longitude;
            }
        });
    }
}

