package com.example.geoguss;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    private TextView generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate random latitude and longitude
                double latitude = generateRandomLatitude();
                double longitude = generateRandomLongitude();

                // Start the MapActivity with the random latitude and longitude
                Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                intent.putExtra("LATITUDE", latitude);
                intent.putExtra("LONGITUDE", longitude);
                startActivity(intent);
            }
        });
    }

    private double generateRandomLatitude() {
        // Generate random latitude within the range of valid latitudes (-90 to 90 degrees)
        return (new Random().nextDouble() * 180) - 90;
    }

    private double generateRandomLongitude() {
        // Generate random longitude within the range of valid longitudes (-180 to 180 degrees)
        return (new Random().nextDouble() * 360) - 180;
    }
}
