package com.example.geoguss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ResultActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView userGuessTextView;
    private TextView distanceTextView;
    private MapView mapView;

    private TextView nextLocationButton;
    private double distance;

    private String selectedCountry;
    private String userGuess;
    private double userGuessLatitude;
    private double userGuessLongitude;
    private double actualLocationLatitude;
    private double actualLocationLongitude;
    private TextView homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        userGuessTextView = findViewById(R.id.userGuessTextView);
        distanceTextView = findViewById(R.id.distanceTextView);
        mapView = findViewById(R.id.mapView);
        nextLocationButton = findViewById(R.id.nextLocationButton);
        homeButton = findViewById(R.id.homeButton);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Get the data passed from the LocationGuessActivity
        Intent intent = getIntent();
        selectedCountry = intent.getStringExtra("SELECTED_COUNTRY");
        userGuess = intent.getStringExtra("USER_GUESS");
        userGuessLatitude = intent.getDoubleExtra("LATITUDE", 0.0);
        userGuessLongitude = intent.getDoubleExtra("LONGITUDE", 0.0);
        distance = intent.getDoubleExtra("DISTANCE", 0.0);
        actualLocationLatitude = intent.getDoubleExtra("ACTUAL" , 0.0);
        actualLocationLongitude = intent.getDoubleExtra("ACTUAL LONG" , 0.0);

        // Update the TextViews to display the user's guess and distance
        userGuessTextView.setText("Your Guess: " + userGuess);
        distanceTextView.setText("Distance Form Actual Location: " + String.format("%.2f", distance) + " km");


        nextLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LocationGuessActivity again to generate the next random location
                Intent intent = new Intent(ResultActivity.this, MapActivity.class);
                intent.putExtra("SELECTED_COUNTRY", selectedCountry);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the HomeActivity
                Intent intent = new Intent(ResultActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker for the user's guessed location
        LatLng userGuessLatLng = new LatLng(userGuessLatitude, userGuessLongitude);
        googleMap.addMarker(new MarkerOptions().position(userGuessLatLng).title("Your Guess"));

        // Add a marker for the actual location (You need to implement a method to get the actual location)
        LatLng actualLocationLatLng = new LatLng(actualLocationLatitude, actualLocationLongitude);
        googleMap.addMarker(new MarkerOptions().position(actualLocationLatLng).title("Actual Location"));

        // Move the camera to the user's guessed location
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(userGuessLatLng)
                .zoom(12) // Set an appropriate zoom level
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    // Override the lifecycle methods for MapView
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

